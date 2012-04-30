package ru.kirsanov.mca.analitics.metricreader;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.HashMap;
import java.util.Map;

/**
 * User: akirsanov
 * Date: 22.04.12 14:55
 */
public class PerPackageMetricReader implements IMetricReader {
    private NodeList nodeList;
    private Map<String, Double> metricsForPackage = new HashMap<String, Double>();


    private String name;

    public PerPackageMetricReader(String name, NodeList nodeList) {
        this.nodeList = nodeList;
        this.name = name;

    }

    public String getName() {
        return name;
    }

    @Override
    public void readMetric() {
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeName().equals("Values")) {
                NodeList valueArray = node.getChildNodes();
                for (int z = 0; z < valueArray.getLength(); z++) {
                    Node element = valueArray.item(z);
                    if (element.getNodeName().equals("Value")) {
                        Double result = Double.parseDouble(element.getAttributes().getNamedItem("value").getNodeValue().replace(",","."));
                        String packageName = element.getAttributes().getNamedItem("name").getNodeValue();
                        metricsForPackage.put(packageName, result);
                    }
                }
            }
        }
    }

    @Override
    public Map<String, Double> getMetricsForMethod() {
        return new HashMap<String, Double>();
    }

    @Override
    public Map<String, Double> getMetricsForType() {
        return new HashMap<String, Double>();
    }

    public Map<String, Double> getMetricsForPackage() {
        return metricsForPackage;
    }
}
