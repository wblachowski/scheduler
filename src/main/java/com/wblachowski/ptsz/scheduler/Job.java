package main.java.com.wblachowski.ptsz.scheduler;

public class Job {

    private final int p;
    private final int a;
    private final int b;

    Job(String line) {
        String[] elements = line.split("\\s+");
        p = Integer.parseInt(elements[0]);
        a = Integer.parseInt(elements[1]);
        b = Integer.parseInt(elements[2]);
    }
}
