package ru.kirsanov.mca.analitics.expertmarks;

import ru.kirsanov.mca.other.dao.DaoFactory;
import ru.kirsanov.mca.other.dao.MarkEntity;
import ru.kirsanov.mca.other.dao.MetricEntity;

import java.sql.SQLException;
import java.util.*;

public class MetricsMarkRange {

    private final Map<String, Double> medianBadMarks;
    private final Map<String, Double> medianGoodMarks;
    private final Map<String, Double> medianExcellentMarks;
    private HashMap<String, Double> medianMarkWeights;

    public MetricsMarkRange() {
        medianBadMarks = new HashMap<String, Double>();
        medianGoodMarks = new HashMap<String, Double>();
        medianExcellentMarks = new HashMap<String, Double>();
        medianMarkWeights = new HashMap<String, Double>();
        List<MetricEntity> listOfMetricEntities = DaoFactory.getMetricDAO().getAllRecords();
        Map<String, List<Double>> badMarks = new HashMap<String, List<Double>>();
        Map<String, List<Double>> goodMarks = new HashMap<String, List<Double>>();
        Map<String, List<Double>> excellentMarks = new HashMap<String, List<Double>>();
        Map<String, List<Double>> weights = new HashMap<String, List<Double>>();
        for (MetricEntity metricEntity : listOfMetricEntities) {
            try {
                List<MarkEntity> markEntities = DaoFactory.getMarkDAO().getRecords(metricEntity.getId());
                List<Double> forBad = new ArrayList<Double>();
                List<Double> forGood = new ArrayList<Double>();
                List<Double> forExcellent = new ArrayList<Double>();
                List<Double> forWeight = new ArrayList<Double>();
                for (MarkEntity markEntity : markEntities) {
                    forBad.add(markEntity.getBadMark());
                    forGood.add(markEntity.getGoodMark());
                    forExcellent.add(markEntity.getExcelentMark());
                    forWeight.add(markEntity.getWeight());
                }

                badMarks.put(metricEntity.getName(), forBad);
                goodMarks.put(metricEntity.getName(), forGood);
                excellentMarks.put(metricEntity.getName(), forExcellent);
                weights.put(metricEntity.getName(), forWeight);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        for (String metricName : badMarks.keySet()) {
            List<Double> listOfBadMarks = badMarks.get(metricName);
            Collections.sort(listOfBadMarks);
            List<Double> listOfGoodMarks = goodMarks.get(metricName);
            Collections.sort(listOfGoodMarks);
            List<Double> listOfExcellentMarks = excellentMarks.get(metricName);
            Collections.sort(listOfExcellentMarks);
            List<Double> listOfWeights = weights.get(metricName);
            Collections.sort(listOfWeights);
            if (listOfBadMarks.size() > 0)
                medianBadMarks.put(metricName, listOfBadMarks.get((int) (listOfBadMarks.size() / 2)));
            if (listOfGoodMarks.size() > 0)
                medianGoodMarks.put(metricName, listOfGoodMarks.get((int) (listOfGoodMarks.size() / 2)));
            if (listOfExcellentMarks.size() > 0)
                medianExcellentMarks.put(metricName, listOfExcellentMarks.get((int) (listOfExcellentMarks.size() / 2)));
            if (listOfWeights.size() > 0)
                medianMarkWeights.put(metricName, listOfWeights.get((int) (listOfWeights.size() / 2)));
        }
    }

    public Double getMedianForBadMarks(String metric) {
        return medianBadMarks.get(metric);
    }

    public Double getMedianForGoodMarks(String metric) {
        return medianGoodMarks.get(metric);
    }

    public Double getMedianForExcellentMarks(String metric) {
        return medianExcellentMarks.get(metric);
    }

    public Double getMedianForWeight(String metric) {
        return medianMarkWeights.get(metric);
    }


}
