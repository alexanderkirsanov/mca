package ru.kirsanov.mca.analitics.metricreader;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.HashMap;
import java.util.Map;

/**
 * User: akirsanov
 * Date: 22.04.12 14:55
 */
public class PerTypeMetricReader implements IMetricReader {
    private NodeList nodeList;
    private Map<String, Double> metricsForType = new HashMap<String, Double>();
    private Map<String, Double> metricsForPackage = new HashMap<String, Double>();


    private String name;

    public PerTypeMetricReader(String name, NodeList nodeList) {
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
                        Double value = Double.parseDouble(element.getAttributes().getNamedItem("value").getNodeValue());
                        metricsForType.put(element.getAttributes().getNamedItem("name").getNodeValue(), value);
                        String packageName = element.getAttributes().getNamedItem("package").getNodeValue();
                        Double oldResult = metricsForPackage.get(packageName);
                        if (oldResult == null) oldResult = 0d;
                        double result = ( oldResult + value) / 2;
                        metricsForPackage.put(packageName, result);
                    }
                }
            }
        }
    }
}
