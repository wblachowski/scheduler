package com.wblachowski.ptsz.scheduler.sorter;

import java.util.ArrayList;
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

    public abstract void sort();

    void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }
}
