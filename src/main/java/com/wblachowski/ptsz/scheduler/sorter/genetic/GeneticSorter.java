package com.wblachowski.ptsz.scheduler.sorter.genetic;

import com.wblachowski.ptsz.data.Instance;
import com.wblachowski.ptsz.data.Job;
import com.wblachowski.ptsz.scheduler.sorter.AdvancedHalvingSorter;
import com.wblachowski.ptsz.scheduler.sorter.FirstHalvingSorter;
import com.wblachowski.ptsz.scheduler.sorter.Sorter;

import java.util.*;

public class GeneticSorter extends Sorter {
    private static final int POPULATION_SIZE = 100;
    private static final double MUTATION_PROBABILITY = 0.1;
    private final AdvancedHalvingSorter sorter;
    private final FirstHalvingSorter secondSorter;
    private Random random = new Random();
    private long millisLimit;
    private long millisStart;

    public GeneticSorter(Instance instance) {
        super(instance);
        sorter = new AdvancedHalvingSorter(instance);
        secondSorter = new FirstHalvingSorter(instance);
    }

    @Override
    public void sort(long millisLimit) {
        this.millisLimit = millisLimit;
        this.millisStart = System.currentTimeMillis();
        sort();
    }

    @Override
    public void sort() {
        long shuffleStart = System.currentTimeMillis();
        //TRY SORTINGS
        sorter.sort();
        secondSorter.sort();

        Sorter betterSorter = sorter.getResult() <= secondSorter.getResult() ? sorter : secondSorter;

        Population parentPopulation = new Population(new ArrayList<>(Collections.nCopies(POPULATION_SIZE, new Solution(betterSorter.getJobs(), getD()))));

        long shuffleTime = System.currentTimeMillis() - shuffleStart;

        int iterations = 0;
        while (System.currentTimeMillis() - millisStart - 2 * shuffleTime < millisLimit) {
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
            int best = childrenSolutions.stream().mapToInt(Solution::getFitness).min().orElse(Integer.MAX_VALUE);
            //System.out.printf("Best: %d\n", best);
            parentPopulation = childrenPopulation;
            iterations++;
        }
        //System.out.printf("Iterations: %d\n", iterations);
        List<Job> jobs = parentPopulation.getSolutions().stream().min(Comparator.comparingInt(Solution::getFitness)).get().getJobs();
        doBubbleSwapping(jobs);
        setJobs(jobs);
    }
}
