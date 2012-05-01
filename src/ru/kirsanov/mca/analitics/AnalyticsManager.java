package ru.kirsanov.mca.analitics;

import ru.kirsanov.mca.analitics.metricreader.IMetricReader;
import ru.kirsanov.mca.analitics.rules.MamdaniAlgorithm;
import ru.kirsanov.mca.analitics.rules.fuzzyset.ActivatedFuzzySet;
import ru.kirsanov.mca.analitics.rules.fuzzyset.UnionOfFuzzySets;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: akirsanov
 * Date: 30.04.12 19:52
 */
public class AnalyticsManager {

    private final Map<String,double[]> result;

    public AnalyticsManager(String path) {
        MetricsReader metricsReader = new MetricsReader(path);
        MamdaniAlgorithm mamdaniAlgorithm = new MamdaniAlgorithm();
        metricsReader.loadMetrics();
        Map<String, IMetricReader> maps = metricsReader.getMapOfReaders();
        Map<String, double[]> resultOfFuzzification = new HashMap<String, double[]>();
        Map<String, List<ActivatedFuzzySet>> resultOfActivation = new HashMap<String, List<ActivatedFuzzySet>>();
        Map<String, List<UnionOfFuzzySets>> resultOfAccumulation = new HashMap<String, List<UnionOfFuzzySets>>();
        result = new HashMap<String, double[]>();
        for (String metricName : maps.keySet()) {
            maps.get(metricName).readMetric();
            resultOfFuzzification.put(metricName, mamdaniAlgorithm.fuzzification(metricName, maps.get(metricName).getMetricsForPackage().values()));
            resultOfActivation.put(metricName, mamdaniAlgorithm.activation(metricName, resultOfFuzzification.get(metricName)));
            resultOfAccumulation.put(metricName, mamdaniAlgorithm.accumulation(metricName, resultOfActivation.get(metricName)));
            result.put(metricName, mamdaniAlgorithm.defuzzification(metricName, resultOfAccumulation.get(metricName), maps.get(metricName).getMetricsForPackage().values()));
        }
    }

    public Map<String, double[]> getResult() {
        return result;
    }

    public Double getProjectState(){
        double sum = 0;
        for (double[] res: getResult().values()){
            for (double resInArray:res)
                if (resInArray != Double.NaN)
                    sum +=resInArray;
        }
        return sum;
    }
}
