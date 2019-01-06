package com.wblachowski.ptsz.scheduler.sorter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.wblachowski.ptsz.data.Instance;
import com.wblachowski.ptsz.data.Job;

public abstract class Sorter {

    private final int d;

    private List<Job> jobs;

    public Sorter(Instance instance) {
        this.d = instance.getD();
        this.jobs = new ArrayList<>(instance.getJobs());
    }

    public List<Job> getJobs() {
        return jobs;
    }

    protected int getD() {
        return d;
    }

    public int getResult() {
        int sum = 0;
        int time = 0;
        for (Job job : jobs) {
            time += job.getP();
            int diff = d - time;
            if (diff > 0) {
                sum += job.getA() * diff;
            } else {
                sum += job.getB() * diff * -1;
            }
        }
        return sum;
    }

    public void sort(long millisLimit){
        sort();
    }

    public abstract void sort();

    protected void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    protected void doBubbleSwapping(List<Job> jobs) {
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
