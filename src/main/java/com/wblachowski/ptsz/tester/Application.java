package com.wblachowski.ptsz.tester;

import com.wblachowski.ptsz.data.Instance;
import com.wblachowski.ptsz.tester.data.TesterInputArguments;

import java.io.IOException;

public class Application {
    private static final int[] ns = {10, 20, 50, 100, 200, 500, 1000};

    public static void main(String[] args) throws IOException, InterruptedException {
        String program = args[0];
        for (int n : ns) {
            for (int k = 4; k <= 9; k += 5) {
                for (int h = 4; h <= 6; h += 2) {
                    String[] testerArgsRaw = new String[]{String.valueOf(n), String.valueOf(k), String.valueOf((double) h / 10), program};
                    TesterInputArguments arguments = new TesterInputArguments(testerArgsRaw);

                    Instance instance = new Instance(arguments);
                    ProgramRunner runner = new ProgramRunner(arguments);
                    Tester tester = new Tester().setInstance(instance).setOrder(runner.getOrder()).setProgramResult(runner.getResult());
                    tester.test();
                    System.out.println("========================");
                    System.out.println(arguments);
                    System.out.println(tester.isCorrect() + "\n" + tester.getRealResult() + "\n" + runner.getExecutionTime());
                }
            }
        }
    }
}