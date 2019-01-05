package com.wblachowski.ptsz.scheduler.sorter.genetic;

import com.wblachowski.ptsz.data.Instance;
import com.wblachowski.ptsz.scheduler.sorter.Sorter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.function.DoubleSupplier;

public class GeneticSorter extends Sorter {
    private static final int POPULATION_SIZE = 200;
    private Random random = new Random();

    public GeneticSorter(Instance instance) {
        super(instance);
    }

    @Override
    public void sort() {
        Population parentPopulation = new Population(POPULATION_SIZE, getJobs(), getD());
        for(int j=0;j<20;j++) {
            List<Solution> breedingSolutions = parentPopulation.getSolutionsForBreeding();
            List<Solution> childrenSolutions = new ArrayList<>();
            for (int i = 0; i < POPULATION_SIZE; i++) {
                Solution firstParent = breedingSolutions.get(random.nextInt(breedingSolutions.size()));
                Solution secondParent = breedingSolutions.get(random.nextInt(breedingSolutions.size()));
                Solution child = firstParent.getChild(secondParent);
                childrenSolutions.add(child);
            }
            Population childrenPopulation = new Population(childrenSolutions);
            System.out.printf("Average: %f Best %d:\n", childrenSolutions.stream().mapToInt(Solution::getFitness).average().orElse(Double.MAX_VALUE), childrenSolutions.stream().mapToInt(Solution::getFitness).min().orElse(Integer.MAX_VALUE));
            parentPopulation = childrenPopulation;
        }
        setJobs(parentPopulation.getSolutions().stream().min(Comparator.comparingInt(Solution::getFitness)).get().getJobs());
    }
}
