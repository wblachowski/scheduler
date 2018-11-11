package com.wblachowski.ptsz.scheduler.sorter;

import com.wblachowski.ptsz.data.Instance;
import com.wblachowski.ptsz.data.Job;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class HalvingSorter extends Sorter {
    public HalvingSorter(Instance instance) {
        super(instance);
    }

    @Override
    public void sort() {
        Set<Job> unusedJobs = new HashSet<>(getJobs());
        List<Job> jobsSmallerA = getJobs().stream().filter(job->job.getA()<=job.getB()).collect(Collectors.toList());
        List<Job> jobsSmallerB = getJobs().stream().filter(job->job.getA()>job.getB()).collect(Collectors.toList());
        /*
        We wont fit all smallerAs before D -> we should transfer some of them to second list...
        */
        jobsSmallerA.sort(Comparator.comparingInt(Job::getA));
        jobsSmallerB.sort(Comparator.comparingInt(Job::getB).reversed());
        jobsSmallerA.addAll(jobsSmallerB);
        setJobs(jobsSmallerA);
    }
}
