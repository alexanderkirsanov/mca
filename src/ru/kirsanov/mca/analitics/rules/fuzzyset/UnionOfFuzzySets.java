package ru.kirsanov.mca.analitics.rules.fuzzyset;

import java.util.ArrayList;
import java.util.List;

/**
 * User: akirsanov
 * Date: 01.05.12 12:22
 */
public class UnionOfFuzzySets implements IFuzzySet {
    private List<ActivatedFuzzySet> fuzzySets = new ArrayList<ActivatedFuzzySet>();

    public void addFuzzySet(ActivatedFuzzySet fuzzySet) {
        this.fuzzySets.add(fuzzySet);
    }

    private double getMaxValue(double x) {
        double result = 0.0;
        for (FuzzySet fuzzySet : fuzzySets) {
            result = Math.max(result, fuzzySet.getValue(x));
        }
        return result;
    }

    @Override
    public double getValue(double val) {
        return getMaxValue(val);
    }
}
