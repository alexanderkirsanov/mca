package ru.kirsanov.mca.analitics;

import org.w3c.dom.NodeList;
import ru.kirsanov.mca.analitics.metricreader.IMetricReader;
import ru.kirsanov.mca.analitics.metricreader.PerTypeMetricReader;

/**
 * User: akirsanov
 * Date: 22.04.12 14:52
 */
public class MetricsFactory {
    public static IMetricReader createPerTypeMetricReader(String name, NodeList nodeList){
        return new PerTypeMetricReader(name, nodeList);
    }
}
