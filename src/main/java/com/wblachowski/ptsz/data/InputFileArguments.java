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

    public int getN(){
        return n;
    }

    public int getK(){
        return k;
    }

    public double getH(){
        return (double)h/10;
    }

    public int getHinteger(){
        return h;
    }
}
