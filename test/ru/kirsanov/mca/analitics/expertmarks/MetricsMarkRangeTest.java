package ru.kirsanov.mca.analitics.expertmarks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.kirsanov.mca.other.dao.MarkDAO;
import ru.kirsanov.mca.other.dao.MarkEntity;
import ru.kirsanov.mca.other.dao.MetricDAO;
import ru.kirsanov.mca.other.dao.MetricEntity;

import static junit.framework.Assert.assertEquals;

/**
 * User: akirsanov
 * Date: 24.04.12 18:49
 */
public class MetricsMarkRangeTest {

    private MarkDAO markDAO;
    private MetricDAO metricDAO;
    private MetricsMarkRange metricsMarkRange;

    @Before
    public void setUp() throws Exception {
        metricDAO = new MetricDAO();

        metricDAO.insert(new MetricEntity("NORM"));
        metricDAO.insert(new MetricEntity("NRM"));
        MetricEntity normMetricEntity = metricDAO.getRecords("NORM").get(0);
        MetricEntity nrmMetricEntity = metricDAO.getRecords("NRM").get(0);
        markDAO = new MarkDAO();
        MarkEntity markEntity = new MarkEntity(3d,2d,1d, normMetricEntity.getId(),1, 1d);
        MarkEntity markEntity2 = new MarkEntity(4d,3d,1d,normMetricEntity.getId(),2, 0.5d);
        MarkEntity markEntity3 = new MarkEntity(5d,3d,1d,normMetricEntity.getId(),3, 0.8d);
        MarkEntity nmarkEntity = new MarkEntity(12d,6d,1d,nrmMetricEntity.getId(),1, 0.7d);
        MarkEntity nmarkEntity2 = new MarkEntity(12d,5d,1d,nrmMetricEntity.getId(),2, 0.5d);
        MarkEntity nmarkEntity3 = new MarkEntity(1d,3d,1d,nrmMetricEntity.getId(),3, 0.9d);
        markDAO.insert(markEntity);
        markDAO.insert(markEntity2);
        markDAO.insert(markEntity3);
        markDAO.insert(nmarkEntity);
        markDAO.insert(nmarkEntity2);
        markDAO.insert(nmarkEntity3);
        metricsMarkRange = new MetricsMarkRange();

    }

    @Test
    public void testGetMedianForBadMarks() throws Exception {
      assertEquals(1d,metricsMarkRange.getMedianForBadMarks("NORM"));
        assertEquals(1d,metricsMarkRange.getMedianForBadMarks("NRM"));
    }

    @Test
    public void testGetMedianForGoodMarks() throws Exception {
        assertEquals(3d,metricsMarkRange.getMedianForGoodMarks("NORM"));
        assertEquals(5d,metricsMarkRange.getMedianForGoodMarks("NRM"));
    }

    @Test
    public void testGetMedianForExcellentMarks() throws Exception {
        assertEquals(4d,metricsMarkRange.getMedianForExcellentMarks("NORM"));
        assertEquals(12d,metricsMarkRange.getMedianForExcellentMarks("NRM"));
    }

    @Test
    public void testGetMedianForWeights() throws Exception {
        assertEquals(0.8d,metricsMarkRange.getMedianForWeight("NORM"));
        assertEquals(0.7d,metricsMarkRange.getMedianForWeight("NRM"));
    }
    @After
    public void tearDown(){
        markDAO.clearTable();
        metricDAO.clearTable();
    }
}
