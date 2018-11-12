package com.wblachowski.ptsz.checker;

import com.wblachowski.ptsz.scheduler.Scheduler;

import java.io.IOException;

public class Checker {

    private static final int[] filesNumbers = {20, 50, 100, 200, 500, 1000};

    public static void main(String[] args) throws IOException {
        ResultReader resultReader = new ResultReader();
        for (int fileNumber : filesNumbers) {
            for (int k = 1; k <= 10; k++) {
                int[] results = resultReader.getResults(fileNumber, k);
                for (double h = 0.2; h <= 0.8; h += 0.2) {
                    String[] schedulerArgs = new String[]{String.valueOf(fileNumber), String.valueOf(k), String.valueOf(h)};
                    Scheduler scheduler = new Scheduler(schedulerArgs);
                    scheduler.start();
                    System.out.println(scheduler.getResult());
                }
            }
        }
    }
}
