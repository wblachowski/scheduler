package com.wblachowski.ptsz.scheduler;

import com.wblachowski.ptsz.data.InputFileArguments;
import com.wblachowski.ptsz.data.Instance;
import com.wblachowski.ptsz.data.Job;
import com.wblachowski.ptsz.scheduler.sorter.AdvancedHalvingSorter;
import com.wblachowski.ptsz.scheduler.sorter.Sorter;
import com.wblachowski.ptsz.scheduler.sorter.genetic.GeneticSorter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

public class Scheduler {
    public static void main(String[] args) {
        Scheduler scheduler = new Scheduler(args, "greedy");
        scheduler.start();
        System.out.println("Greedy:");
        System.out.println(scheduler.getResult());
        for (Job job : scheduler.getJobs()) System.out.print(job.getIndex() + " ");

        scheduler = new Scheduler(args, "genetic");
        scheduler.start();
        System.out.println("\nGenetic:");
        System.out.println(scheduler.getResult());
        for (Job job : scheduler.getJobs()) System.out.print(job.getIndex() + " ");
    }

    private List<Job> jobs;
    private int result;
    private final String[] args;
    private String sorterType;
    private InputFileArguments arguments;

    public Scheduler(String[] args) {
        this.args = args;
    }

    public Scheduler(String[] args, String sorterType) {
        this.args = args;
        this.sorterType = sorterType;
    }

    public void start() {
        arguments = new InputFileArguments(args);
        try {
            Instance instance = new Instance(arguments);

            Sorter sorter = "genetic".equals(sorterType) ? new GeneticSorter(instance) : new AdvancedHalvingSorter(instance);
            sorter.sort();
            jobs = sorter.getJobs();
            result = sorter.getResult();
            saveToFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    List<Job> getJobs() {
        return jobs;
    }

    public int getResult() {
        return result;
    }

    private void saveToFile() throws IOException {
        String filename = "sch_127259_" + arguments.getN() + "_" + arguments.getK() + "_" + (int) (10 * arguments.getH()) + ".out";
        File outputfile = new File(filename);
        outputfile.createNewFile();
        try (PrintStream out = new PrintStream(new FileOutputStream(filename))) {
            out.print(getResult() + "\n");
            jobs.forEach(job -> out.print(job.getIndex() + " "));
        }
    }
}
