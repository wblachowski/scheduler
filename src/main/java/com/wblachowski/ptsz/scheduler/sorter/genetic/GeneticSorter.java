package com.wblachowski.ptsz.scheduler.sorter.genetic;

import com.wblachowski.ptsz.data.Instance;
import com.wblachowski.ptsz.scheduler.sorter.AdvancedHalvingSorter;
import com.wblachowski.ptsz.scheduler.sorter.Sorter;

import java.util.*;

public class GeneticSorter extends Sorter {
    private static final int POPULATION_SIZE = 200;
    private static final double MUTATION_PROBABILITY = 0.1;
    private Random random = new Random();

    AdvancedHalvingSorter sorter;

    public GeneticSorter(Instance instance) {
        super(instance);
        sorter = new AdvancedHalvingSorter(instance);
    }

    @Override
    public void sort() {

        //INPUT FROM GREEDY SORTER
        sorter.sort();
        Population parentPopulation = new Population(new ArrayList<>(Collections.nCopies(POPULATION_SIZE, new Solution(sorter.getJobs(), getD()))));

        //OR RANDOM INPUT
        //Population parentPopulation = new Population(POPULATION_SIZE, getJobs(), getD());
        for (int j = 0; j < 200; j++) {
            List<Solution> breedingSolutions = parentPopulation.getSolutionsForBreeding();
            List<Solution> childrenSolutions = new ArrayList<>();
            for (int i = 0; i < POPULATION_SIZE; i++) {
                Solution firstParent = breedingSolutions.get(random.nextInt(breedingSolutions.size()));
                Solution secondParent = breedingSolutions.get(random.nextInt(breedingSolutions.size()));
                Solution child = firstParent.getChild(secondParent);
                if (random.nextDouble() <= MUTATION_PROBABILITY) {
                    child.mutate();
                }
                childrenSolutions.add(child);
            }
            Population childrenPopulation = new Population(childrenSolutions);
            //System.out.printf("Avg: %.2f Best: %d\n", childrenSolutions.stream().mapToInt(Solution::getFitness).average().orElse(Double.MAX_VALUE), childrenSolutions.stream().mapToInt(Solution::getFitness).min().orElse(Integer.MAX_VALUE));
            parentPopulation = childrenPopulation;
        }
        setJobs(parentPopulation.getSolutions().stream().min(Comparator.comparingInt(Solution::getFitness)).get().getJobs());
    }
}
