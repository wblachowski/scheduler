package com.wblachowski.ptsz.scheduler.sorter.genetic;

import com.wblachowski.ptsz.data.Job;

import java.util.ArrayList;
import java.util.List;

class Solution {
    private List<Job> jobs;
    private int dueDate;

    Solution(List<Job> jobs, int dueDate) {
        this.jobs = new ArrayList<>(jobs);
        this.dueDate = dueDate;
    }

    public int getFitness() {
        int sum = 0;
        int time = 0;
        for (Job job : jobs) {
            time += job.getP();
            int diff = dueDate - time;
            sum += diff > 0 ? job.getA() * diff : job.getB() * diff * -1;
        }
        return sum;
    }
}
