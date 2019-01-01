package com.wblachowski.ptsz.scheduler.sorter.genetic;

import com.wblachowski.ptsz.data.Instance;
import com.wblachowski.ptsz.data.Job;
import com.wblachowski.ptsz.scheduler.sorter.Sorter;

import java.util.*;

public class GeneticSorter extends Sorter {
    private static final int POPULATION_SIZE = 200;
    private List<List<Job>> population;


    public GeneticSorter(Instance instance) {
        super(instance);
    }

    @Override
    public void sort() {
        population = generateInitialPopulation();
    }

    private List<List<Job>> generateInitialPopulation() {
        List<List<Job>> population = new ArrayList<>();
        List<Job> allJobs = new ArrayList<>(getJobs());
        for (int i = 0; i < POPULATION_SIZE; i++) {
            Collections.shuffle(allJobs);
            population.add(new ArrayList<>(allJobs));
        }
        return population;
    }
}
