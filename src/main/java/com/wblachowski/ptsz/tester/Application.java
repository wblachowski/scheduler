package com.wblachowski.ptsz.tester;

import com.wblachowski.ptsz.data.Instance;
import com.wblachowski.ptsz.tester.data.TesterInputArguments;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException, InterruptedException {
        TesterInputArguments arguments = new TesterInputArguments(args);
        Instance instance = new Instance(arguments);
        ProgramRunner runner = new ProgramRunner(arguments);
        Tester tester = new Tester().setInstance(instance).setOrder(runner.getOrder()).setProgramResult(runner.getResult());
        tester.test();
        System.out.println(tester.isCorrect() + "\n" + tester.getRealResult() + "\n" + runner.getExecutionTimeMillis());
    }
}
