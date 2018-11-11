package com.wblachowski.ptsz.tester;

import com.wblachowski.ptsz.data.Instance;
import com.wblachowski.ptsz.tester.data.TesterInputArguments;

import java.io.IOException;
import java.util.Arrays;

public class Application {
    public static void main(String[] args) throws IOException, InterruptedException {
        TesterInputArguments arguments = new TesterInputArguments(args);
        Instance instance = new Instance(arguments);
        ProgramRunner runner = new ProgramRunner(arguments.getProgram());
        System.out.println(Arrays.toString(runner.getOrder()));
        System.out.println(runner.getResult());
    }
}
