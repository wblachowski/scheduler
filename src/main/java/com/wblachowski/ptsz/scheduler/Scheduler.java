package com.wblachowski.ptsz.scheduler;

import com.wblachowski.ptsz.data.InputFileArguments;
import com.wblachowski.ptsz.data.Instance;
import com.wblachowski.ptsz.data.Job;
import com.wblachowski.ptsz.scheduler.sorter.AdvancedHalvingSorter;
import com.wblachowski.ptsz.scheduler.sorter.Sorter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

public class Scheduler {
    public static void main(String[] args) {
        Scheduler scheduler = new Scheduler(args);
        scheduler.start();
        System.out.println(scheduler.getResult());
        for (Job job : scheduler.getJobs()) System.out.print(job.getIndex() + " ");
    }

    private List<Job> jobs;
    private int result;
    private final String[] args;
    private InputFileArguments arguments;

    public Scheduler(String[] args) {
        this.args = args;
    }

    public void start() {
        arguments = new InputFileArguments(args);
        try {
            Instance instance = new Instance(arguments);
//            System.out.println(instance.getJobs());
//            System.out.println(instance.getD());

            Sorter sorter = new AdvancedHalvingSorter(instance);
            sorter.sort();
            jobs = sorter.getJobs();
            result = sorter.getResult();
            saveToFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public int getResult() {
        return result;
    }

    private void saveToFile() throws IOException {
        String filename="sch_127259_"+arguments.getN()+"_"+arguments.getK()+"_"+arguments.getHinteger()+".out";
        File outputfile = new File(filename);
        outputfile.createNewFile();
        try (PrintStream out = new PrintStream(new FileOutputStream(filename))) {
            out.print(getResult()+"\n");
            jobs.forEach(job->out.print(job.getIndex()+" "));
        }
    }
}
