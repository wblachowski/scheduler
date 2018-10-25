package com.wblachowski.ptsz.scheduler;

import java.util.ArrayList;
import java.util.List;
import com.google.common.collect.Lists;

public class Sorter {

    private final int d;

    private List<Job> jobs;

    public Sorter(Instance instance) {
        this.d = instance.getD();
        this.jobs = new ArrayList<>(instance.getJobs());
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void sort() {
        ArrayList<Job> jobsByA = new ArrayList<>(jobs);
        jobsByA.sort((j1,j2) -> (Integer.compare(j1.getA(),j2.getA())));

        ArrayList<Job> jobsByB = new ArrayList<>(jobs);
        jobsByB.sort((j1,j2) -> (Integer.compare(j2.getB(),j1.getB())));

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
}
