package com.wblachowski.ptsz.tester.data;

import org.apache.commons.lang3.StringUtils;
import com.wblachowski.ptsz.data.InputFileArguments;

import java.util.Arrays;

public class TesterInputArguments extends InputFileArguments {

    private int result;

    public TesterInputArguments(String[] args){
        String joinedArgs = StringUtils.join(Arrays.asList(args)," ");
        String[] splitArgs = joinedArgs.split("\\s*[,|\\s]\\s*");
        n = Integer.parseInt(splitArgs[0]);
        k = Integer.parseInt(splitArgs[1]);
        h = Double.parseDouble(splitArgs[2]);
        result = Integer.parseInt(splitArgs[3]);
    }

    public int getResult(){
        return result;
    }
}
