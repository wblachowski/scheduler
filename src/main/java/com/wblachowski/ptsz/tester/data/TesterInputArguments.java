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
}
