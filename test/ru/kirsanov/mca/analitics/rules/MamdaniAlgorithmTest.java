package ru.kirsanov.mca.analitics.rules;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.kirsanov.mca.analitics.AnalyticsManager;
import ru.kirsanov.mca.analitics.expertmarks.MetricsMarkRange;
import ru.kirsanov.mca.other.dao.MarkDAO;
import ru.kirsanov.mca.other.dao.MarkEntity;
import ru.kirsanov.mca.other.dao.MetricDAO;
import ru.kirsanov.mca.other.dao.MetricEntity;

import java.sql.SQLException;

/**
 * User: akirsanov
 * Date: 30.04.12 23:58
 */
public class MamdaniAlgorithmTest {
    private MarkDAO markDAO;
    private MetricDAO metricDAO;
    private MetricsMarkRange metricsMarkRange;

    @Before
    public void setUp() throws SQLException {
        metricDAO = new MetricDAO();

        metricDAO.insert(new MetricEntity("NORM"));
        metricDAO.insert(new MetricEntity("NOM"));
        MetricEntity normMetricEntity = metricDAO.getRecords("NORM").get(0);
        MetricEntity nrmMetricEntity = metricDAO.getRecords("NOM").get(0);
        markDAO = new MarkDAO();
        MarkEntity markEntity = new MarkEntity(1d, 0.52d, 0d, normMetricEntity.getId(), 1, 1d);
        MarkEntity markEntity2 = new MarkEntity(2d, 0.25d, 0d, normMetricEntity.getId(), 2, 0.5d);
        MarkEntity markEntity3 = new MarkEntity(0.8d, 0.2d, 0.1d, normMetricEntity.getId(), 3, 0.8d);
        MarkEntity nmarkEntity = new MarkEntity(12d, 6d, 1d, nrmMetricEntity.getId(), 1, 0.7d);
        MarkEntity nmarkEntity2 = new MarkEntity(12d, 5d, 1d, nrmMetricEntity.getId(), 2, 0.5d);
        MarkEntity nmarkEntity3 = new MarkEntity(1d, 3d, 1d, nrmMetricEntity.getId(), 3, 0.9d);
        markDAO.insert(markEntity);
        markDAO.insert(markEntity2);
        markDAO.insert(markEntity3);
        markDAO.insert(nmarkEntity);
        markDAO.insert(nmarkEntity2);
        markDAO.insert(nmarkEntity3);
        metricsMarkRange = new MetricsMarkRange();

    }

    @Test
    public void testFuzzification() throws Exception {
        AnalyticsManager analyticsManager = new AnalyticsManager("C:\\Users\\akirsanov\\workspace\\test\\metrics.xml");

    }

    @After
    public void tearDown(){
        markDAO.clearTable();
        metricDAO.clearTable();
    }

}
