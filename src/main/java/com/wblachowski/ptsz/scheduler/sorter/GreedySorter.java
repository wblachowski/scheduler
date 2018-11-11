package com.wblachowski.ptsz.scheduler.sorter;

import com.wblachowski.ptsz.data.Instance;
import com.wblachowski.ptsz.data.Job;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GreedySorter extends Sorter {

    public GreedySorter(Instance instance) {
        super(instance);
    }

    @Override
    public void sort() {
        List<Job> orderedJobs = new ArrayList<>();
        int time = 0;
        Set<Job> unusedJobs = new HashSet<>(getJobs());
        while (!unusedJobs.isEmpty()) {
            int minCost = Integer.MAX_VALUE;
            Job minCostJob = null;
            for (Job job : unusedJobs) {
                int cost = Integer.MAX_VALUE;
                int endtime = time + job.getP();
                int diff = Math.abs(endtime - getD());
                if (endtime <= getD()) {
                    cost = job.getA() * diff;
                } else if (endtime > getD()) {
                    cost = job.getB() * diff;
                }

                if (cost < minCost) {
                    minCost = cost;
                    minCostJob = job;
                }
            }
            if (minCostJob != null) {
                unusedJobs.remove(minCostJob);
                orderedJobs.add(minCostJob);
                time += minCostJob.getP();
            }
        }
        setJobs(orderedJobs);
    }
}
