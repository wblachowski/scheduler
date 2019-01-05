package com.wblachowski.ptsz.scheduler.sorter.genetic;

import com.wblachowski.ptsz.data.Job;

import java.util.ArrayList;
import java.util.List;

class Solution {
    private List<Job> jobs;

    Solution(List<Job> jobs) {
        this.jobs = new ArrayList<>(jobs);
    }
}
