package com.wblachowski.ptsz.data;

import java.util.Objects;

public class Job {

    private final int i;
    private final int p;
    private final int a;
    private final int b;

    Job(int index, String line) {
        this.i = index;
        String[] elements = line.trim().split("\\s+");
        p = Integer.parseInt(elements[0]);
        a = Integer.parseInt(elements[1]);
        b = Integer.parseInt(elements[2]);
    }

    public int getP() {
        return p;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public int getIndex() {
        return i;
    }

    @Override
    public String toString() {
        return "Job{" +
                "i=" + i +
                ", p=" + p +
                ", a=" + a +
                ", b=" + b +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Job job = (Job) o;
        return i == job.i &&
                p == job.p &&
                a == job.a &&
                b == job.b;
    }

    @Override
    public int hashCode() {
        return Objects.hash(i, p, a, b);
    }
}
