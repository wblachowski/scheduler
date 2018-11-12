package com.wblachowski.ptsz.scheduler.sorter;

import java.util.ArrayList;
import java.util.List;

import com.wblachowski.ptsz.data.Instance;
import com.wblachowski.ptsz.data.Job;

public abstract class Sorter {

    private final int d;

    private List<Job> jobs;

    Sorter(Instance instance) {
        this.d = instance.getD();
        this.jobs = new ArrayList<>(instance.getJobs());
    }

    public List<Job> getJobs() {
        return jobs;
    }

    int getD() {
        return d;
    }

    public int getResult() {
        int sum = 0;
        int time = 0;
        for (Job job : jobs) {
            int endtime = time + job.getP();
            int diff = d - endtime;
            if (diff > 0) {
                sum += job.getA() * diff;
            } else {
                sum += job.getB() * diff * -1;
            }
            time += job.getP();
        }
        return sum;
    }

    public abstract void sort();

    void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }
}
