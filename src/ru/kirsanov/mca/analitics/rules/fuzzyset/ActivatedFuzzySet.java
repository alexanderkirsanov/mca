package ru.kirsanov.mca.analitics.rules.fuzzyset;

/**
 * User: akirsanov
 * Date: 01.05.12 0:28
 */
public class ActivatedFuzzySet extends FuzzySet {
    private double truthDegree;

    public ActivatedFuzzySet(double minValue, double maxValue) {
        super(minValue, maxValue);
    }

    public ActivatedFuzzySet(FuzzySet term) {
        super(term.getMin(), term.getMax());
    }

    public void setTruthDegree(double truthDegree) {
        this.truthDegree = truthDegree;
    }

    public double getActivatedValue(double value) {
        return Math.min(super.getValue(value), truthDegree);
    }
}
