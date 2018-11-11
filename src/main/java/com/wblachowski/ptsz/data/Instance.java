package com.wblachowski.ptsz.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Instance {

    private static final String FILE_FORMAT = "sch%d.txt";

    private final InputFileArguments args;

    private final ArrayList<Job> jobs;

    private final int d;

    public Instance(InputFileArguments args) throws IOException {
        this.args = args;
        String filename = String.format(FILE_FORMAT, args.getN());
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            jobs = retrieveJobs(reader);
        }
        d = retrieveD();
    }

    public ArrayList<Job> getJobs() {
        return jobs;
    }

    public int getD() {
        return d;
    }

    private ArrayList<Job> retrieveJobs(BufferedReader reader) throws IOException {
        ArrayList<Job> result = new ArrayList<>();
        int jobsCount = jumpToExactJob(reader);
        for (int i = 0; i < jobsCount; i++) {
            Job job = new Job(i + 1, reader.readLine());
            result.add(job);
        }
        return result;
    }

    private int jumpToExactJob(BufferedReader reader) throws IOException {
        int problemsCount = Integer.parseInt(reader.readLine().trim());
        int currentJob = 0;
        int ourJob = args.getK();
        for (int i = 0; i < problemsCount; i++) {
            currentJob++;
            int jobsCount = Integer.parseInt(reader.readLine().trim());
            if (currentJob == ourJob) {
                return jobsCount;
            }
            for (int j = 0; j < jobsCount; j++) {
                reader.readLine();
            }
        }
        return 0;
    }

    private int retrieveD() {
        int sumP = jobs.stream().mapToInt(Job::getP).sum();
        return (int) Math.floor(sumP * args.getH());
    }
}
