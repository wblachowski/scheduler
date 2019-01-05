package com.wblachowski.ptsz.scheduler.sorter.genetic;

import com.wblachowski.ptsz.data.Job;

import java.util.*;

class Population {

    private Set<Solution> solutions = new HashSet<>();

    Population(int size, List<Job> jobs) {
        List<Job> allJobs = new ArrayList<>(jobs);
        for (int i = 0; i < size; i++) {
            Collections.shuffle(allJobs);
            solutions.add(new Solution(allJobs));
        }
    }

    public Set<Solution> getSolutions() {
        return solutions;
    }
}
