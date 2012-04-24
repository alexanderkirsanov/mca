package ru.kirsanov.mca.other.dao;

/**
 * User: akirsanov
 * Date: 24.04.12 17:11
 */
public class DaoFactory {
    private static ExpertDAO expertDAO = null;
    private static MarkDAO markDAO = null;
    private static MetricDAO metricDAO = null;

    public static ExpertDAO getExpertDAO() {
        if (expertDAO == null) {
            expertDAO = new ExpertDAO();
        }
        return expertDAO;
    }

    public static MetricDAO getMetricDAO() {
        if (metricDAO == null) {
            metricDAO = new MetricDAO();
        }
        return metricDAO;
    }

    public static MarkDAO getMarkDAO() {
        if (markDAO == null) {
            markDAO = new MarkDAO();
        }
        return markDAO;
    }

}
