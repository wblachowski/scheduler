package main.java.com.wblachowski.ptsz.scheduler;

import java.util.ArrayList;
import java.util.List;

public class Sorter {

    private final int d;

    private final List<Job> jobs;

    public Sorter(Instance instance) {
        this.d = instance.getD();
        this.jobs = new ArrayList<>(instance.getJobs());
    }

    public List<Job> getJobs(){
        return jobs;
    }

    public void sort(){

    }

}
