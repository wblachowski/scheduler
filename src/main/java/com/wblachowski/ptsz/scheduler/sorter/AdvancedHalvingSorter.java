package com.wblachowski.ptsz.scheduler.sorter;

import com.wblachowski.ptsz.data.Instance;
import com.wblachowski.ptsz.data.Job;

import java.util.*;
import java.util.stream.Collectors;

public class AdvancedHalvingSorter extends Sorter {
    public AdvancedHalvingSorter(Instance instance) {
        super(instance);
    }

    @Override
    public void sort() {
        Set<Job> unusedJobs = new HashSet<>(getJobs());
        List<Job> jobsLeft = getJobs().stream().filter(job -> job.getA() <= job.getB()).collect(Collectors.toList());
        List<Job> jobsRight = getJobs().stream().filter(job -> job.getA() > job.getB()).collect(Collectors.toList());
        /*
        We wont fit all smallerAs before D -> we should transfer some of them to second list...
        */
        int timeSum = jobsLeft.stream().mapToInt(Job::getP).sum();
        int diff = getD() - timeSum;
        int absDiff = Math.abs(diff);
        List<Job> jobsSourceSorted;
        List<Job> jobsDestination;
        List<Job> jobsSource;
        if (diff < 0) {
            jobsDestination = jobsRight;
            jobsSource = jobsLeft;
            jobsSourceSorted = new ArrayList<>(jobsLeft);
            jobsSourceSorted.sort(Comparator.comparingInt(Job::getA).reversed());
        } else {
            jobsDestination = jobsLeft;
            jobsSource = jobsRight;
            jobsSourceSorted = new ArrayList<>(jobsRight);
            jobsSourceSorted.sort(Comparator.comparingInt(Job::getB).reversed());
        }
        while (absDiff > 0) {
            Job job = jobsSourceSorted.get(0);
            jobsDestination.add(job);
            jobsSource.remove(job);
            jobsSourceSorted.remove(job);
            absDiff -= job.getP();
        }

        jobsLeft.sort(Comparator.comparingInt(Job::getA));
        jobsRight.sort(Comparator.comparingInt(Job::getB).reversed());
        jobsLeft.addAll(jobsRight);
        setJobs(jobsLeft);
    }
}
