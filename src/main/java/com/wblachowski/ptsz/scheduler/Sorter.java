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

    public List<Job> getJobs() {
        return jobs;
    }

    public void sort() {

    }

    public int getResult() {
        int sum = 0;
        int time = 0;
        for (Job job : jobs) {
            int endtime = time + job.getP();
            int diff = Math.abs(endtime - d);
            if (endtime < d) {
                sum += job.getA() * diff;
            } else if (endtime > d) {
                sum += job.getB() * diff;
            }
            time += job.getP();
        }
        return sum;
    }

}
