package com.wblachowski.ptsz.data;

public class InputFileArguments {

    private int n;
    private int k;
    private double h;

    public InputFileArguments(String[] args){
        n = Integer.parseInt(args[0]);
        k = Integer.parseInt(args[1]);
        h = Double.parseDouble(args[2]);
    }

    public int getN(){
        return n;
    }

    public int getK(){
        return k;
    }

    public double getH(){
        return h;
    }
}
