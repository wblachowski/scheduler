package com.wblachowski.ptsz.checker;

import com.wblachowski.ptsz.scheduler.Scheduler;

import java.io.IOException;

public class Checker {

    private static final int[] filesNumbers = {10, 20, 50, 100, 200, 500, 1000};

    public static void main(String[] args) throws IOException {
        double sum = 0;
        int problemsCount = 0;
        ResultReader resultReader = new ResultReader();
        for (int fileNumber : filesNumbers) {
            for (int k = 1; k <= 10; k++) {
                int[] results = resultReader.getResults(fileNumber, k);
                int j = 0;
                for (double h = 0.2; h <= 0.8; h += 0.2) {
                    String[] schedulerArgs = new String[]{String.valueOf(fileNumber), String.valueOf(k), String.valueOf(h)};
                    Scheduler scheduler = new Scheduler(schedulerArgs);
                    scheduler.start();
                    int optimalResult = results[j];
                    System.out.print(String.format("\"%d %d %.1f\": ", fileNumber, k, h));
                    System.out.println(scheduler.getResult() / (double) optimalResult);
                    j++;
                    problemsCount++;
                    sum += scheduler.getResult() / (double) optimalResult;
                }
            }
            System.out.println("#########");
        }
        System.out.println("Avg: " + sum / (double) problemsCount);
    }
}
