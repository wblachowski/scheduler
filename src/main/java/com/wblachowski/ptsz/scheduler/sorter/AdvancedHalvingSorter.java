package com.wblachowski.ptsz.scheduler.sorter;

import com.wblachowski.ptsz.data.Instance;
import com.wblachowski.ptsz.data.Job;

import java.util.*;
import java.util.stream.Collectors;

public class AdvancedHalvingSorter extends Sorter {
    public AdvancedHalvingSorter(Instance instance) {
        super(instance);
    }

    @Override
    public void sort() {
        List<Job> allJobs = getJobs().stream().sorted(Comparator.comparingInt(j->((Job)j).getB()-((Job)j).getA()).reversed()).collect(Collectors.toList());
        int lastJobBeforeDueDate = 0;
        int time = 0;
        while (time <= getD()) {
            time += allJobs.get(lastJobBeforeDueDate++).getP();
        }
        List<Job> jobsLeft = allJobs.subList(0,lastJobBeforeDueDate).stream().sorted(Comparator.comparingInt(j -> (j.getA() - (j.getP() / 2)))).collect(Collectors.toList());
        List<Job> jobsRight = allJobs.subList(lastJobBeforeDueDate,allJobs.size()).stream().sorted(Comparator.comparingInt(j -> (((Job)j).getB() - ((int) ((double) ((Job)j).getP() / 1.5)))).reversed()).collect(Collectors.toList());
        jobsLeft.addAll(jobsRight);

        secondPart(jobsLeft);

        setJobs(jobsLeft);
    }

    private void secondPart(List<Job> jobs) {
        int n = jobs.size();
        int deadline = getD();
        int time;
        int cost1 = 0;
        int cost2 = 0;
        int t = 0;
        boolean was_change = false;
        for (int j = 0; j < n; j++) {
            time = 0;
            was_change = false;

            for (int i = 0; i < n - 1; i++) {
                t = deadline - (time + jobs.get(i).getP());
                if (t >= 0) {
                    cost1 = t * jobs.get(i).getA();
                    t -= jobs.get(i + 1).getP();
                    if (t >= 0)
                        cost1 += t * jobs.get(i + 1).getA();
                    else cost1 += (-t) * jobs.get(i + 1).getB();
                } else {
                    t = -t;
                    cost1 = t * jobs.get(i).getB();
                    t += jobs.get(i + 1).getP();
                    cost1 += t * jobs.get(i + 1).getB();
                }

                Collections.swap(jobs, i, i + 1);

                t = deadline - (time + jobs.get(i).getP());
                if (t >= 0) {
                    cost2 = t * jobs.get(i).getA();
                    t -= jobs.get(i + 1).getP();
                    if (t >= 0)
                        cost2 += t * jobs.get(i + 1).getA();
                    else cost2 += (-t) * jobs.get(i + 1).getB();
                } else {
                    t = -t;
                    cost2 = t * jobs.get(i).getB();
                    t += jobs.get(i + 1).getP();
                    cost2 += t * jobs.get(i + 1).getB();
                }
                if (cost2 >= cost1)
                    Collections.swap(jobs, i, i + 1);
                else {
                    was_change = true;
                }
                time += jobs.get(i).getP();
            }

            if (!was_change)
                break;
        }
    }
}
