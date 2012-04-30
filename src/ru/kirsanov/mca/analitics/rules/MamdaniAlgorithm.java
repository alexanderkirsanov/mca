package ru.kirsanov.mca.analitics.rules;

import ru.kirsanov.mca.analitics.rules.entities.Conclusion;
import ru.kirsanov.mca.analitics.rules.entities.Condition;
import ru.kirsanov.mca.analitics.rules.fuzzyset.ActivatedFuzzySet;
import ru.kirsanov.mca.analitics.rules.fuzzyset.FuzzySet;

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

    private double avg(Collection<Double> list) {
        if (list.size() == 0)
            return 0;

        double sum = 0;
        for (double number : list)
            sum += number;

        return sum / list.size();
    }
}
