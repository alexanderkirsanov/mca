package ru.kirsanov.mca.analitics.metricreader;

import java.util.Map;

/**
 * User: akirsanov
 * Date: 22.04.12 14:54
 */
public interface IMetricReader {
    void readMetric();
    Map<String, Double> getMetricsForMethod();
    Map<String, Double> getMetricsForType();
    Map<String, Double> getMetricsForPackage();
}
