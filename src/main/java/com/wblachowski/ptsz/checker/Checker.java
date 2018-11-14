package com.wblachowski.ptsz.checker;

import com.wblachowski.ptsz.scheduler.Scheduler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Checker {

    private static final int[] filesNumbers = {10, 20, 50, 100, 200, 500, 1000};

    public static void main(String[] args) throws IOException {
        double sum = 0;
        int problemsCount = 0;
        ResultReader resultReader = new ResultReader();
        Scheduler schedulerWarmup = new Scheduler(new String[]{"20","2","2"});
        schedulerWarmup.start();
        double minError=Double.MAX_VALUE;
        double maxError=Double.MIN_VALUE;
        long executionTimeSum=0;
        List<Double> errorsList = new ArrayList<Double>();
        for (int fileNumber : filesNumbers) {
            for (int k = 4; k <= 9; k+=5) {
                int[] results = resultReader.getResults(fileNumber, k);
                for (int h = 4; h <= 6; h +=2) {
                    problemsCount++;
                    String[] schedulerArgs = new String[]{String.valueOf(fileNumber), String.valueOf(k), String.valueOf(h)};
                    Scheduler scheduler = new Scheduler(schedulerArgs);
                    long startmillis = System.nanoTime();
                    scheduler.start();
                    long executingTime = System.nanoTime() - startmillis;
                    executionTimeSum+=executingTime;
                    int optimalResult = results[h/2 - 1];
                    double error = 100*(double)(scheduler.getResult()-optimalResult)/(double)optimalResult;
                    errorsList.add(error);
                    if(error>maxError)maxError=error;
                    if(error<minError)minError=error;
//                    System.out.print(String.format("\"%d %d %.1f\": ", fileNumber, k, (double)h/10));
                    System.out.println(String.format("%d & %d & %d & %.1f & %d & %d & %.2f\\%% & %.3f \\\\\n\\hline", problemsCount, fileNumber, k, (double)h/10, optimalResult, scheduler.getResult(),error,(double)executingTime/1000000f));
                    sum += scheduler.getResult() / (double) optimalResult;
                }
            }
        }
        System.out.println(String.format("Min: %.2f",minError));
        System.out.println(String.format("Max: %.2f",maxError));
        double avg=sum / (double) problemsCount;
        System.out.println("Avg: " + avg);
        double stddev=0;
        for(Double error : errorsList){
            stddev+=Math.pow((error-avg),2);
        }
        stddev=Math.sqrt(stddev/errorsList.size());
        System.out.println(String.format("Srddev: %.2f",stddev));
        System.out.println(String.format("Avgtime: %.2f",(double)executionTimeSum/1000000/problemsCount));

    }
}
