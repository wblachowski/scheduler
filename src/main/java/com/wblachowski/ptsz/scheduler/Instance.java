package main.java.com.wblachowski.ptsz.scheduler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Instance {

    private static final String FILE_FORMAT = "sch%d.txt";

    private ArrayList<Job> jobs;

    Instance(InputArguments arg) throws IOException {
        String filename = String.format(FILE_FORMAT, arg.getN());
        List<String> lines = Files.readAllLines(Paths.get(filename));
        jobs = retrieveJobs(lines);
    }

    private ArrayList<Job> retrieveJobs(List<String> lines){
        ArrayList<Job> result = new ArrayList<>();
        return result;
    }
}
