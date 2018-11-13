package com.wblachowski.ptsz.data;

public class InputFileArguments {

    private int n;
    private int k;
    private int h;

    public InputFileArguments(String[] args){
        n = Integer.parseInt(args[0]);
        k = Integer.parseInt(args[1]);
        h = Integer.parseInt(args[2]);
    }

    int getN(){
        return n;
    }

    int getK(){
        return k;
    }

    double getH(){
        return (double)h/10;
    }
}
