package com.wblachowski.ptsz.scheduler;

import com.wblachowski.ptsz.data.InputFileArguments;
import com.wblachowski.ptsz.data.Instance;
import com.wblachowski.ptsz.scheduler.data.SorterInputArguments;
import com.wblachowski.ptsz.scheduler.sorter.AdvancedHalvingSorter;
import com.wblachowski.ptsz.scheduler.sorter.HalvingSorter;
import com.wblachowski.ptsz.scheduler.sorter.GreedySorter;
import com.wblachowski.ptsz.scheduler.sorter.Sorter;

import java.io.IOException;

public class Application {
    public static void main(String[] args) {
        new Application().start(args);
    }

    private void start(String[] args) {
        InputFileArguments arguments = new SorterInputArguments(args);
        try {
            Instance instance = new Instance(arguments);
            System.out.println(instance.getJobs());
            System.out.println(instance.getD());

            Sorter[] sorters = new Sorter[]{new GreedySorter(instance), new HalvingSorter(instance), new AdvancedHalvingSorter(instance)};
            for(Sorter sorter : sorters){
                System.out.println(sorter.getClass());
                sorter.sort();
                System.out.println(sorter.getJobs());
                System.out.println(sorter.getResult()+"\n");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
