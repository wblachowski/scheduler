package main.java.com.wblachowski.ptsz.scheduler;

import java.io.IOException;

public class Application {
    public static void main(String[] args) {
        new Application().start(args);
    }

    private void start(String[] args) {
        InputArguments arguments = new InputArguments(args);
        try {
            Instance instance = new Instance(arguments);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
