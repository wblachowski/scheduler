package com.wblachowski.ptsz.tester;

import com.wblachowski.ptsz.checker.ResultReader;
import com.wblachowski.ptsz.data.Instance;
import com.wblachowski.ptsz.tester.data.TesterInputArguments;

import java.io.File;
import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException, InterruptedException {
        ResultReader resultReader = new ResultReader();

        File[] files = new File(".").listFiles();
        for (File file : files) {
            if(!file.getName().matches("^\\d{6}\\.bat$"))continue;
            System.out.println(file.getName().substring(0,6));
            int[] nVals = new int[]{10, 20, 50, 100, 200, 500, 1000};
            int i = 0;
            double errorSum=0f;
            for (int h = 2; h <= 8; h += 2) {
                for (int n : nVals) {

                    int k = i % 10 + 1;
                    int[] results = resultReader.getResults(n,k);
                    int optimalResult = results[h/2 - 1];

//                    System.out.print(n+","+k+","+h);
                    try {
                        TesterInputArguments arguments = new TesterInputArguments(new String[]{String.valueOf(n), String.valueOf(k), String.valueOf((double) h / 10), file.getName()});
                        Instance instance = new Instance(arguments);
                        ProgramRunner runner = new ProgramRunner(arguments);
                        Tester tester = new Tester().setInstance(instance).setOrder(runner.getOrder()).setProgramResult(runner.getResult());
                        tester.test();
//                        System.out.print(","+runner.getResult()+","+tester.getRealResult());
//                        System.out.print("," + (tester.isCorrect() ? "1" : "0"));
//                        System.out.println(","+runner.getExecutionTime());
                        errorSum+=(double)(tester.getRealResult()-optimalResult)/(double)optimalResult;
                    }catch(Exception ex){
                        System.out.println(",ERROR");
                    }
                    i++;
                }
            }
            System.out.println(errorSum/(double)i);
        }
    }
}
