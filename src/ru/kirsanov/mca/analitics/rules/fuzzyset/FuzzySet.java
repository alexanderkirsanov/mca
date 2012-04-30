package ru.kirsanov.mca.analitics.rules.fuzzyset;

/**
 * User: akirsanov
 * Date: 26.04.12 23:30
 */
public class FuzzySet implements IFuzzySet {

    private double max;
    private double min;

    public FuzzySet(double minValue, double maxValue) {
        this.min = minValue;
        this.max = maxValue;
    }

    @Override
    public double getValue(double val) {
        if (val <= this.min) return 0;
        if (val >= this.max) return 1;
        return (val - this.min) / (this.max - this.min);
    }

    public double getMax() {
        return max;
    }

    public double getMin() {
        return min;
    }
}
