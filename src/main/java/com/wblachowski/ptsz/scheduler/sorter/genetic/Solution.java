package com.wblachowski.ptsz.scheduler.sorter.genetic;

import com.wblachowski.ptsz.data.Job;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
    private static final double[] CROSSOVER_PERCENTAGES = {0.4, 0.7};
    private List<Integer> crossoverPoints;
    private List<Job> jobs;
    private int dueDate;

    Solution(List<Job> jobs, int dueDate) {
        this.jobs = new ArrayList<>(jobs);
        this.dueDate = dueDate;
        this.crossoverPoints = Arrays.stream(CROSSOVER_PERCENTAGES).boxed().map(p -> (int) (p * jobs.size())).collect(Collectors.toList());
    }

    List<Job> getJobs() {
        return jobs;
    }

    int getFitness() {
        int sum = 0;
        int time = 0;
        for (Job job : jobs) {
            time += job.getP();
            int diff = dueDate - time;
            sum += diff > 0 ? job.getA() * diff : job.getB() * diff * -1;
        }
        return sum;
    }

    Solution getChild(Solution secondParent) {
        List<Job> childJobs = new ArrayList<>();
        Solution source = this;
        for (int i = 0; i < jobs.size(); i++) {
            if (crossoverPoints.contains(i)) {
                source = source == this ? secondParent : this;
            }
            childJobs.add(source.getJobs().get(i));
        }
        return new Solution(childJobs, dueDate);
    }
}
