package ru.kirsanov.mca.analitics.rules;

import ru.kirsanov.mca.analitics.expertmarks.MetricsMarkRange;
import ru.kirsanov.mca.analitics.rules.entities.Conclusion;
import ru.kirsanov.mca.analitics.rules.entities.Condition;
import ru.kirsanov.mca.analitics.rules.fuzzyset.FuzzySet;
import ru.kirsanov.mca.other.dao.MetricDAO;
import ru.kirsanov.mca.other.dao.MetricEntity;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class RulesGenerator {
    private MetricsMarkRange metricsMarkRange = new MetricsMarkRange();

    private Map<String, Rule> rules = new HashMap<String, Rule>();

    public void generateRules() throws SQLException {
        MetricDAO metricDAO = new MetricDAO();
        List<MetricEntity> metrics = metricDAO.getAllRecords();
        for (MetricEntity metric : metrics) {
            double excellent = metricsMarkRange.getMedianForExcellentMarks(metric.getName());
            double good = metricsMarkRange.getMedianForGoodMarks(metric.getName());
            double weight = metricsMarkRange.getMedianForWeight(metric.getName());
            double bad = metricsMarkRange.getMedianForBadMarks(metric.getName());
            Condition excellentCondition = new Condition();
            FuzzySet excellentTerm = new FuzzySet(good, excellent);
            excellentCondition.setTerm(excellentTerm);

            Condition badCondition = new Condition();
            FuzzySet badTerm = new FuzzySet(bad, good);
            badCondition.setTerm(badTerm);

            Conclusion excellentConclusion = new Conclusion();
            excellentConclusion.setTerm(excellentTerm);
            excellentConclusion.setWeight(weight);

            Conclusion badConclusion = new Conclusion();
            badConclusion.setTerm(badTerm);
            badConclusion.setWeight(weight);
            List<Conclusion> conclusions = new LinkedList<Conclusion>();
            List<Condition> conditions = new LinkedList<Condition>();
            conclusions.add(excellentConclusion);
            conclusions.add(badConclusion);
            conditions.add(excellentCondition);
            conditions.add(badCondition);
            Rule rule = new Rule(conclusions, conditions);
            rules.put(metric.getName(), rule);
        }
    }

    public Rule getRule(String metric) {
        return rules.get(metric);
    }

}

