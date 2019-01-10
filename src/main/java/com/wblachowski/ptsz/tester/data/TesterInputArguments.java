package com.wblachowski.ptsz.tester.data;

import com.wblachowski.ptsz.data.InputFileArguments;

public class TesterInputArguments extends InputFileArguments {

    private String program;

    public TesterInputArguments(String[] args) {
        super(args);
        program = args[3];
    }

    public String getProgram() {
        return program;
    }

    @Override
    public String toString() {
        return String.format("%d %d %.1f", getN(),getK(),getH());
    }
}
