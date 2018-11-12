package com.wblachowski.ptsz.checker;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ResultReader {

    private static final String FILE_FORMAT = "res%d.txt";

    public int[] getResults(int n, int k) throws IOException {
        String filename = String.format(FILE_FORMAT, n);
        String line = StringUtils.EMPTY;
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            for (int j = 0; j < k; j++) {
                line = reader.readLine();
            }
        }
        line = line.trim().replace("*", "").replace(",","");
        String[] stringResults = line.split("\\s+");
        int[] result = new int[stringResults.length];
        for (int i = 0; i < stringResults.length; i++) {
            result[i] = Integer.parseInt(stringResults[i]);
        }
        return result;
    }
}
