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

    private static final int MILLIS_FILE_SAVE_DURATION = 50;

    public static void main(String[] args) {
        Scheduler scheduler = new Scheduler(args, "genetic");
        scheduler.start();
        System.out.println(scheduler.getResult());
        for (Job job : scheduler.getJobs()) System.out.print(job.getIndex() + " ");
    }

    private List<Job> jobs;
    private int result;
    private final String[] args;
    private long millisStart;
    private String sorterType;
    private InputFileArguments arguments;

    public Scheduler(String[] args) {
        this.args = args;
        this.millisStart = System.currentTimeMillis();
    }

    public Scheduler(String[] args, String sorterType) {
        this(args);
        this.sorterType = sorterType;
    }

    public void start() {
        arguments = new InputFileArguments(args);
        try {
            Instance instance = new Instance(arguments);
            Sorter sorter = "genetic".equals(sorterType) ? new GeneticSorter(instance) : new AdvancedHalvingSorter(instance);
            long millisLimit = calcMillisLimit(instance);
            sorter.sort(millisLimit);
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
        long start = System.currentTimeMillis();
        String filename = "sch_127259_" + arguments.getN() + "_" + arguments.getK() + "_" + (int) (10 * arguments.getH()) + ".out";
        File outputfile = new File(filename);
        outputfile.createNewFile();
        try (PrintStream out = new PrintStream(new FileOutputStream(filename))) {
            out.print(getResult() + "\n");
            jobs.forEach(job -> out.print(job.getIndex() + " "));
        }
        //System.out.printf("SAVING FILE TOOK: %d\n", System.currentTimeMillis()-start);
    }

    private long calcMillisLimit(Instance instance) {
        int n = instance.getJobs().size();
        long totalTime = n*100;
        long timePassed = System.currentTimeMillis() - millisStart;
        long saveTime = n>=500 ? 5*MILLIS_FILE_SAVE_DURATION : MILLIS_FILE_SAVE_DURATION;
        return totalTime - timePassed - saveTime;
    }
}
