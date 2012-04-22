package ru.kirsanov.mca.analitics;

import org.junit.Test;

/**
 * User: akirsanov
 * Date: 21.04.12 23:52
 */
public class MetricsReaderTest {
    @Test
    public void testLoadMetrics() throws Exception {
        MetricsReader metricsReader = new MetricsReader("C:\\Users\\akirsanov\\workspace\\test\\metrics.xml");
        metricsReader.loadMetrics();
    }
}
