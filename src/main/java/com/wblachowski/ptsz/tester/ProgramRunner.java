package com.wblachowski.ptsz.tester;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class ProgramRunner {

    private final int[] order;
    private final int result;
    private final long executionTimeMillis;

    ProgramRunner(String program) throws IOException, InterruptedException {
        long start = System.currentTimeMillis();
        Process p = Runtime.getRuntime().exec(program);
        executionTimeMillis = System.currentTimeMillis() - start;
        BufferedReader bri = new BufferedReader
                (new InputStreamReader(p.getInputStream()));
        order = Arrays.stream(bri.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        result = Integer.parseInt(bri.readLine().trim());
        bri.close();
        p.waitFor();
    }

    int[] getOrder() {
        return order;
    }

    int getResult() {
        return result;
    }

    long getExecutionTimeMillis() {
        return executionTimeMillis;
    }

}
