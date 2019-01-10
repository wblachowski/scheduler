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
        long start = System.currentTimeMillis();
        String executionPath = arguments.getProgram()+" "+arguments.getN()+" "+arguments.getK()+" "+arguments.getH();
        int p = Runtime.getRuntime().exec(executionPath).waitFor();
        executionTime =(double)( System.currentTimeMillis() - start)/1000f;
        String index = arguments.getProgram().split("\\.")[0];
        String filename = "sch_" + index + "_" + arguments.getN() + "_" + arguments.getK() + "_" + (int)(10*arguments.getH()) + ".out";

        BufferedReader bri = new BufferedReader
                (new FileReader(filename));
        result = Integer.parseInt(bri.readLine().trim());
        order = Arrays.stream(bri.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        bri.close();
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
