package ru.kirsanov.mca.analitics.rules;

import ru.kirsanov.mca.analitics.rules.entities.Conclusion;
import ru.kirsanov.mca.analitics.rules.entities.Condition;
import ru.kirsanov.mca.analitics.rules.fuzzyset.ActivatedFuzzySet;
import ru.kirsanov.mca.analitics.rules.fuzzyset.FuzzySet;
import ru.kirsanov.mca.analitics.rules.fuzzyset.UnionOfFuzzySets;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * User: akirsanov
 * Date: 30.04.12 18:09
 */
public class MamdaniAlgorithm {
    RulesGenerator rulesGenerator = new RulesGenerator();

    public MamdaniAlgorithm() {
        try {
            rulesGenerator.generateRules();
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public double[] fuzzification(String metric, Collection<Double> list) {
        int i = 0;
        Rule rule = rulesGenerator.getRule(metric);
        double averageValue = avg(list);
        if (rule == null || rule.getConditions() == null || rule.getConditions().size() == 0)
            return new double[1];

        double[] b = new double[rule.getConditions().size()];
        for (Condition condition : rule.getConditions()) {
            FuzzySet term = condition.getTerm();
            b[i] = term.getValue(averageValue);
            i++;
        }
        return b;
    }

    public List<ActivatedFuzzySet> activation(String metric, double[] c) {

        int i = 0;
        Rule rule = rulesGenerator.getRule(metric);
        if (rule == null || rule.getConditions() == null || rule.getConditions().size() == 0)
            return new ArrayList<ActivatedFuzzySet>();

        List<ActivatedFuzzySet> activatedFuzzySets = new ArrayList<ActivatedFuzzySet>();
        double[] d = new double[rule.getConclusions().size()];
        for (Conclusion conclusion : rule.getConclusions()) {
            d[i] = c[i] * conclusion.getWeight();
            ActivatedFuzzySet activatedFuzzySet = new ActivatedFuzzySet(conclusion.getTerm());
            activatedFuzzySet.setTruthDegree(d[i]);
            activatedFuzzySets.add(activatedFuzzySet);
            i++;
        }
        return activatedFuzzySets;
    }

    public List<UnionOfFuzzySets> accumulation(String metric, List<ActivatedFuzzySet> activatedFuzzySets) {
        List<UnionOfFuzzySets> unionsOfFuzzySets =
                new ArrayList<UnionOfFuzzySets>(activatedFuzzySets.size());


        Rule rule = rulesGenerator.getRule(metric);
        if (rule == null || rule.getConditions() == null || rule.getConditions().size() == 0)
            return new ArrayList<UnionOfFuzzySets>();
        int i = 0;
        for (Conclusion conclusion : rule.getConclusions()) {
            unionsOfFuzzySets.add(new UnionOfFuzzySets());
            unionsOfFuzzySets.get(i).addFuzzySet(activatedFuzzySets.get(i));
            i++;
        }
        return unionsOfFuzzySets;
    }

    public double[] defuzzification(String metricName, List<UnionOfFuzzySets> unionsOfFuzzySets, Collection<Double> list) {
        double[] y = new double[unionsOfFuzzySets.size()];

        double averageValue = avg(list);
        for (int i = 0; i < unionsOfFuzzySets.size(); i++) {
            double i1 = integral(unionsOfFuzzySets.get(i), averageValue, true);
            double i2 = integral(unionsOfFuzzySets.get(i), averageValue, false);
            y[i] = i1 / i2;
            if (Double.isNaN(y[i])) y[i] = 0;
        }
        return y;
    }

    private double integral(UnionOfFuzzySets unionOfFuzzySets, double number, boolean b) {
        if (b)
            return Math.pow(unionOfFuzzySets.getValue(number), 2);
        else
            return unionOfFuzzySets.getValue(number);

    }


    private double sum(double start, double stop,
                       double stepSize,
                       Evaluatable evalObj) {
        double sum = 0.0, current = start;
        while (current <= stop) {
            sum += evalObj.evaluate(current);
            current += stepSize;
        }
        return (sum);
    }


    public double integrate(double start, double stop,
                            int numSteps,
                            Evaluatable evalObj) {
        double stepSize = (stop - start) / (double) numSteps;
        double newStart = start + stepSize / 2.0;
        return (stepSize * sum(newStart, stop, stepSize, evalObj));
    }


    private double avg(Collection<Double> list) {
        if (list.size() == 0)
            return 0;

        double sum = 0;
        for (double number : list)
            sum += number;

        return sum / list.size();
    }
}
