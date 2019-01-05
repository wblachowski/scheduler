package com.wblachowski.ptsz.scheduler.sorter.genetic;

import com.wblachowski.ptsz.data.Instance;
import com.wblachowski.ptsz.scheduler.sorter.Sorter;

public class GeneticSorter extends Sorter {
    private static final int POPULATION_SIZE = 200;
    private Population population;

    public GeneticSorter(Instance instance) {
        super(instance);
    }

    @Override
    public void sort() {
        population = new Population(POPULATION_SIZE, getJobs(), getD());
    }
}
