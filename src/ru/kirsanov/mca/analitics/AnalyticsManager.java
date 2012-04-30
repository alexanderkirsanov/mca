package ru.kirsanov.mca.analitics;

import ru.kirsanov.mca.analitics.metricreader.IMetricReader;
import ru.kirsanov.mca.analitics.rules.MamdaniAlgorithm;
import ru.kirsanov.mca.analitics.rules.fuzzyset.ActivatedFuzzySet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: akirsanov
 * Date: 30.04.12 19:52
 */
public class AnalyticsManager {
    public AnalyticsManager(String path) {
        MetricsReader metricsReader = new MetricsReader(path);
        MamdaniAlgorithm mamdaniAlgorithm = new MamdaniAlgorithm();
        metricsReader.loadMetrics();
        Map<String, IMetricReader> maps = metricsReader.getMapOfReaders();
        Map<String, double[]> resultOfFuzzification = new HashMap<String, double[]>();
        Map<String, List<ActivatedFuzzySet>> resultOfActivation = new HashMap<String, List<ActivatedFuzzySet>>();
        for (String metricName : maps.keySet()) {
            maps.get(metricName).readMetric();
            resultOfFuzzification.put(metricName, mamdaniAlgorithm.fuzzification(metricName, maps.get(metricName).getMetricsForPackage().values()));
            resultOfActivation.put(metricName, mamdaniAlgorithm.activation(metricName, resultOfFuzzification.get(metricName)));
        }
        System.out.println("finish");

    }
}
