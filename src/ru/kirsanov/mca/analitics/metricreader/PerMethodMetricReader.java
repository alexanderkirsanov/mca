package ru.kirsanov.mca.analitics.metricreader;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.HashMap;
import java.util.Map;

/**
 * User: akirsanov
 * Date: 22.04.12 14:55
 */
public class PerMethodMetricReader implements IMetricReader {
    private NodeList nodeList;
    private Map<String, Double> metricsForMethod = new HashMap<String, Double>();
    private Map<String, Double> metricsForType = new HashMap<String, Double>();
    private Map<String, Double> metricsForPackage = new HashMap<String, Double>();


    private String name;

    public PerMethodMetricReader(String name, NodeList nodeList) {
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
                        String methodName = element.getAttributes().getNamedItem("name").getNodeValue();
                        String className = element.getAttributes().getNamedItem("source").getNodeValue();
                        className = className.substring(0, className.length() - 5);
                        metricsForMethod.put(className + "." + methodName, value);
                        Double resultForClass = (metricsForType.get(className) + value) / 2;
                        metricsForType.put(className, resultForClass);
                        String packageName = element.getAttributes().getNamedItem("package").getNodeValue();
                        Double result = (metricsForPackage.get(packageName) + value) / 2;
                        metricsForPackage.put(packageName, result);
                    }
                }
            }
        }
    }
}
