package com.wblachowski.ptsz.data;

public class InputFileArguments {

    private final int n;
    private final int k;
    private final double h;

    public InputFileArguments(String[] args) {
        n = Integer.parseInt(args[0]);
        k = Integer.parseInt(args[1]);
        h = Double.parseDouble(args[2]);
    }

    int getN() {
        return n;
    }

    int getK() {
        return k;
    }

    double getH() {
        return h;
    }
}
