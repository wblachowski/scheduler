package main.java.com.wblachowski.ptsz.scheduler;

public class InputArguments {

    private final int n;
    private final int k;
    private final double h;

    InputArguments(String[] args) {
        n = Integer.parseInt(args[0]);
        k = Integer.parseInt(args[1]);
        h = Double.parseDouble(args[2]);
    }

    public int getN() {
        return n;
    }

    public int getK() {
        return k;
    }

    public double getH() {
        return h;
    }
}
