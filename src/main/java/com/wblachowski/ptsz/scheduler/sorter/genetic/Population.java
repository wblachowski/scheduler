package com.wblachowski.ptsz.scheduler.sorter.genetic;

import com.wblachowski.ptsz.data.Job;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Population {

    private static final double SELECTION_FACTOR = 0.5;

    private List<Solution> solutions = new ArrayList<>();

    Population(int size, List<Job> jobs, int dueDate) {
        List<Job> allJobs = new ArrayList<>(jobs);
        for (int i = 0; i < size; i++) {
            Collections.shuffle(allJobs);
            solutions.add(new Solution(allJobs, dueDate));
        }
    }

    Population(List<Solution> solutions) {
        this.solutions = solutions;
    }

    List<Solution> getSolutions() {
        return solutions;
    }

    List<Solution> getSolutionsForBreeding() {
        int breedingSetSize = (int) (solutions.size() * SELECTION_FACTOR);
        return solutions.stream().sorted(Comparator.comparingInt(Solution::getFitness)).limit(breedingSetSize).collect(Collectors.toList());
    }
}
