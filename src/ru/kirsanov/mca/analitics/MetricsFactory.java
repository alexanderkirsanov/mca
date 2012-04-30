package ru.kirsanov.mca.analitics;

import org.w3c.dom.NodeList;
import ru.kirsanov.mca.analitics.metricreader.IMetricReader;
import ru.kirsanov.mca.analitics.metricreader.PerMethodMetricReader;
import ru.kirsanov.mca.analitics.metricreader.PerPackageMetricReader;
import ru.kirsanov.mca.analitics.metricreader.PerTypeMetricReader;

/**
 * User: akirsanov
 * Date: 22.04.12 14:52
 */
public class MetricsFactory {
    public static IMetricReader createPerTypeMetricReader(String name, NodeList nodeList){
        return new PerTypeMetricReader(name, nodeList);
    }

    public static IMetricReader createPerPackageMetricReader(String name, NodeList nodeList){
        return new PerPackageMetricReader(name, nodeList);
    }

    public static IMetricReader createPerMethodMetricReader(String name, NodeList nodeList){
        return new PerMethodMetricReader(name, nodeList);
    }
}
