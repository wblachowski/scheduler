package com.wblachowski.ptsz.tester;

import com.wblachowski.ptsz.checker.ResultReader;
import com.wblachowski.ptsz.data.Instance;
import com.wblachowski.ptsz.tester.data.TesterInputArguments;

import java.io.IOException;

public class Application {
    private static final int[] ns = {10, 20, 50, 100, 200, 500, 1000};

    public static void main(String[] args) throws IOException, InterruptedException {
        String program = args[0];
        ResultReader resultReader = new ResultReader();
        double errorSum = 0.0;
        double timeSum = 0.0;
        for (int n : ns) {
            for (int k = 4; k <= 9; k += 5) {
                int[] results = resultReader.getResults(n, k);
                for (int h = 4; h <= 6; h += 2) {
                    int optimalResult = results[h / 2 - 1];
                    String[] testerArgsRaw = new String[]{String.valueOf(n), String.valueOf(k), String.valueOf((double) h / 10), program};
                    TesterInputArguments arguments = new TesterInputArguments(testerArgsRaw);

                    Instance instance = new Instance(arguments);
                    ProgramRunner runner = new ProgramRunner(arguments);
                    Tester tester = new Tester().setInstance(instance).setOrder(runner.getOrder()).setProgramResult(runner.getResult());
                    tester.test();
                    System.out.println("========================");
                    System.out.println(arguments);
                    System.out.println(tester.isCorrect() + "\n" + runner.getResult() + "\n" + tester.getRealResult() + "\n" + optimalResult + "\n" + runner.getExecutionTime());
                    double error = 100 * (double) (runner.getResult() - optimalResult) / (double) optimalResult;
                    errorSum += error;
                    timeSum += runner.getExecutionTime();
                    System.out.printf("Correct: %s\nProgram result: %d\nReal result: %d\nOptimal result: %d\nError: %f\nTime: %f\n", tester.isCorrect(), runner.getResult(), tester.getRealResult(), optimalResult, error, runner.getExecutionTime());
                }
            }
        }

        System.out.printf("Avg error: %f\nAvg time: %f", errorSum / 28.0, timeSum / 28.0);
    }
}