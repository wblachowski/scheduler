package com.wblachowski.ptsz.scheduler.data;

import com.wblachowski.ptsz.data.InputFileArguments;

public class SorterInputArguments extends InputFileArguments {

    public SorterInputArguments(String[] args) {
        n = Integer.parseInt(args[0]);
        k = Integer.parseInt(args[1]);
        h = Double.parseDouble(args[2]);
    }
}
