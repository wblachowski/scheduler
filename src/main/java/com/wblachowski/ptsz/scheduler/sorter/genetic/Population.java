package com.wblachowski.ptsz.scheduler.sorter.genetic;

import com.wblachowski.ptsz.data.Job;

import java.util.*;
import java.util.stream.Collectors;

class Population {

    private static final double SELECTION_FACTOR=0.5;

    private Set<Solution> solutions = new LinkedHashSet<>();

    Population(int size, List<Job> jobs, int dueDate) {
        List<Job> allJobs = new ArrayList<>(jobs);
        for (int i = 0; i < size; i++) {
            Collections.shuffle(allJobs);
            solutions.add(new Solution(allJobs, dueDate));
        }
    }

    public Set<Solution> getSolutions() {
        return solutions;
    }

    public Set<Solution> getSolutionsForBreeding(){
        int breedingSetSize = (int) (solutions.size()*SELECTION_FACTOR);
        return solutions.stream().sorted(Comparator.comparingInt(Solution::getFitness)).limit(breedingSetSize).collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
