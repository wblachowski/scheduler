package com.wblachowski.ptsz.tester;

import com.wblachowski.ptsz.tester.data.TesterInputArguments;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

class ProgramRunner {

    private final int[] order;
    private final int result;
    private final double executionTime;

    ProgramRunner(TesterInputArguments arguments) throws IOException, InterruptedException {
        long start = System.nanoTime();
        String executionPath = arguments.getProgram()+" "+arguments.getN()+" "+arguments.getK()+" "+arguments.getHinteger();
        Process p = Runtime.getRuntime().exec(executionPath);
        executionTime =(double)( System.nanoTime() - start)/1000000f;
        Thread.sleep(1000);
        String index = arguments.getProgram().split("\\.")[0];
        String filename = "sch_" + index + "_" + arguments.getN() + "_" + arguments.getK() + "_" + arguments.getHinteger() + ".out";

        BufferedReader bri = new BufferedReader
                (new FileReader(filename));
        result = Integer.parseInt(bri.readLine().trim());
        order = Arrays.stream(bri.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        bri.close();
        p.waitFor();
    }

    int[] getOrder() {
        return order;
    }

    int getResult() {
        return result;
    }

    double getExecutionTime() {
        return executionTime;
    }



}
