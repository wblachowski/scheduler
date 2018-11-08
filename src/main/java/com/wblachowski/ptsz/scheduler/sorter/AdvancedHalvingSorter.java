package com.wblachowski.ptsz.scheduler.sorter;

import com.wblachowski.ptsz.scheduler.Instance;
import com.wblachowski.ptsz.scheduler.Job;

import java.util.*;
import java.util.stream.Collectors;

public class AdvancedHalvingSorter extends Sorter {
    public AdvancedHalvingSorter(Instance instance) {
        super(instance);
    }

    @Override
    public void sort() {
        Set<Job> unusedJobs = new HashSet<>(getJobs());
        List<Job> jobsSmallerA = getJobs().stream().filter(job -> job.getA() <= job.getB()).collect(Collectors.toList());
        List<Job> jobsSmallerB = getJobs().stream().filter(job -> job.getA() > job.getB()).collect(Collectors.toList());
        /*
        We wont fit all smallerAs before D -> we should transfer some of them to second list...
        */
        int timeSum = jobsSmallerA.stream().mapToInt(Job::getP).sum();
        int diff = timeSum - getD();
        List<Job> jobsSmallerACopy = new ArrayList<>(jobsSmallerA);
        jobsSmallerACopy.sort(Comparator.comparingInt(Job::getB));
        while(diff>0){
            Job job = jobsSmallerACopy.get(0);
            jobsSmallerB.add(job);
            jobsSmallerA.remove(job);
            jobsSmallerACopy.remove(job);
            timeSum = jobsSmallerA.stream().mapToInt(Job::getP).sum();
            diff = timeSum - getD();
        }
        jobsSmallerA.sort(Comparator.comparingInt(Job::getA));
        jobsSmallerB.sort(Comparator.comparingInt(Job::getB).reversed());
        jobsSmallerA.addAll(jobsSmallerB);
        setJobs(jobsSmallerA);
    }
}
