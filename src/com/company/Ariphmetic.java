package com.company;

public class Ariphmetic {
    public double sum(double a, double b) {
        return a + b;
    }

    public double difference(double a, double b) {
        return a - b;
    }

    public double qoutirent(double a, double b) {
        if (b == 0) {
            throw new IllegalArgumentException("Deviding on 0 is black magic");
        }
        return a / b;
    }

    public double product(double a, double b) {
        return a * b;
    }

    public double exponent(double a, double n) { return Math.pow(a,n);}
}
