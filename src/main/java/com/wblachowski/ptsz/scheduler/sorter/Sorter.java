package com.wblachowski.ptsz.scheduler.sorter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.google.common.collect.Lists;
import com.wblachowski.ptsz.scheduler.Instance;
import com.wblachowski.ptsz.scheduler.Job;

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

    public int getD() {
        return d;
    }

    public int getResult() {
        int sum = 0;
        int time = 0;
        for (Job job : jobs) {
            int endtime = time + job.getP();
            int diff = Math.abs(endtime - d);
            if (endtime < d) {
                sum += job.getA() * diff;
            } else if (endtime > d) {
                sum += job.getB() * diff;
            }
            time += job.getP();
        }
        return sum;
    }

    public abstract void sort();

    protected void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }
}
