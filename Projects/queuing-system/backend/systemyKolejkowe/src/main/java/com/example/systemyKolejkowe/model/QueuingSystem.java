package com.example.systemyKolejkowe.model;

import java.util.ArrayList;

public class QueuingSystem {
    private float lambda, mu;
    private int m;
    private float rho, probability0, averageV, averageN, averageM0, averageTt, averageTs, averageMnz;
    private ArrayList<Float> probability;
    private String error;

    public QueuingSystem() {
    }

    public QueuingSystem(float lambda, float mu, int m) {
        if (m >= 1) {
            if (lambda < (m * mu)) {
                if (((lambda / mu) / m) < 1) {
                    this.lambda = lambda;
                    this.mu = mu;
                    this.m = m;
                    this.rho = calcRho();
                    this.probability0 = calcProbability(0);
                    probability = new ArrayList<>();
                    for (int i = 1; i <= (m >= 10? 9 : m); ++i) {
                        probability.add(calcProbability(i));
                    }
                    this.averageV = calcAverageV();
                    this.averageTt = calcAverageTt();
                    this.averageN = calcAverageN();
                    this.averageM0 = calcAverageM0();
                    this.averageTs = calcAverageTs();
                    this.averageMnz = calcAverageMnz();
                    this.error = "";
                } else {
                    this.error = "Błąd: ((λ / μ) / m) jest większe niż 1";
                }
            } else {
                this.error = "Błąd: λ jest większa niż m * μ";
            }
        } else {
            this.error = "Błąd: m jest mniejsze niż 1";
        }
    }

    public float getLambda() { return lambda; }

    public float getMu() { return mu; }

    public float getM() { return m; }

    public float getRho() { return rho; }

    public float getProbability0() { return probability0; }

    public ArrayList<Float> getProbability() { return probability; }

    public float getAverageV() { return averageV; }

    public float getAverageN() { return averageN; }

    public float getAverageM0() { return averageM0; }

    public float getAverageTt() { return averageTt; }

    public float getAverageTs() { return averageTs; }

    public float getAverageMnz() { return averageMnz; }

    public String getError() { return error; }

    private int factorial(int n) {
        int j = 1;
        for (int i = 1; i < n; ++i) {
            j *= i;
        }

        return j;
    }

    private float calcRho() { return lambda / mu; }

    public float calcProbability(int j) {
        if (j == 0) {
            float nominal = m - rho;
            float sum = 0;
            for (int i = 0; i <= (m - 1); ++i) {
                sum += (m - j) * Math.pow(rho, j) / factorial(j);
            }

            return nominal / sum;
        } else if (j >= 1 && j <= m) {
            return (float) (Math.pow(rho, j) / factorial(j) * calcProbability(0));
        }
        return 0;
    }

    private float calcAverageV() {
        float numeral = (float) (Math.pow(rho, (m + 1)) / Math.pow((m - rho), 2) * factorial(m - 1));
        float sum = 0;
        for (int i = 0; i <= (m - 1); ++i) {
            sum += Math.pow(rho, i) / factorial(i);
        }
        float nominative = (float) (sum + (Math.pow(rho, m) / (factorial(m - 1) * (m - rho))));

        return numeral / nominative;
    }

    private float calcAverageTt() { return averageV / lambda; }

    private float calcAverageN() { return averageV + rho; }

    private float calcAverageM0() { return rho; }

    private float calcAverageTs() { return averageN / lambda; }

    private float calcAverageMnz() { return m - rho; }
}