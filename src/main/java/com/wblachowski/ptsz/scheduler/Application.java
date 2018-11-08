package com.wblachowski.ptsz.scheduler;

import com.wblachowski.ptsz.scheduler.sorter.GreedySorter;
import com.wblachowski.ptsz.scheduler.sorter.Sorter;

import java.io.IOException;

public class Application {
    public static void main(String[] args) {
        new Application().start(args);
    }

    private void start(String[] args) {
        InputArguments arguments = new InputArguments(args);
        try {
            Instance instance = new Instance(arguments);
            System.out.println(instance.getJobs());
            Sorter sorter = new GreedySorter(instance);
            sorter.sort();
            System.out.println(instance.getD());
            System.out.println(sorter.getJobs());
            System.out.println(sorter.getResult());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
