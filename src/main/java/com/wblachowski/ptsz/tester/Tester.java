package com.wblachowski.ptsz.tester;

import com.wblachowski.ptsz.data.Instance;
import com.wblachowski.ptsz.data.Job;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Tester {

    private Instance instance;
    private int[] order;
    private int result;

    Tester setInstance(Instance instance) {
        this.instance = instance;
        return this;
    }

    Tester setOrder(int[] order) {
        this.order = order;
        return this;
    }

    Tester setResult(int result) {
        this.result = result;
        return this;
    }

    boolean isCorrect() {
        int[] positions = getPositions();
        List<Job> jobs = new ArrayList<>(instance.getJobs());
        jobs.sort(Comparator.comparingInt(job -> positions[job.getIndex()]));
        int d = instance.getD();
        int calculatedResult = 0;
        int time = 0;
        for (Job job : jobs) {
            time = time + job.getP();
            int diff = d - time;
            if (diff >= 0) {
                calculatedResult += diff * job.getA();
            } else {
                calculatedResult += -1 * diff * job.getB();
            }
        }
        return calculatedResult == result;
    }

    private int[] getPositions() {
        int[] positions = new int[order.length + 1];
        for (int i = 0; i < order.length; i++) {
            positions[order[i]] = i + 1;
        }
        return positions;
    }
}
