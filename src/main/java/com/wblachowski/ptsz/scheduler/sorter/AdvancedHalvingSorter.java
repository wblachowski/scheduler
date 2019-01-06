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
        List<Job> allJobs = getJobs().stream().sorted(Comparator.comparingInt(j -> ((Job) j).getB() - ((Job) j).getA()).reversed()).collect(Collectors.toList());
        int lastJobBeforeDueDate = 0;
        int time = 0;
        while (time <= getD()) {
            time += allJobs.get(lastJobBeforeDueDate++).getP();
        }
        List<Job> jobsLeft = allJobs.subList(0, lastJobBeforeDueDate).stream().sorted(Comparator.comparingInt(j -> (j.getA() - (j.getP() / 2)))).collect(Collectors.toList());
        List<Job> jobsRight = allJobs.subList(lastJobBeforeDueDate, allJobs.size()).stream().sorted(Comparator.comparingInt(j -> (((Job) j).getB() - ((int) ((double) ((Job) j).getP() / 1.5)))).reversed()).collect(Collectors.toList());
        jobsLeft.addAll(jobsRight);

        doBubbleSwapping(jobsLeft);

        setJobs(jobsLeft);
    }
}
