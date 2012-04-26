package ru.kirsanov.mca.other.dao;

/**
 * User: akirsanov
 * Date: 23.04.12 23:40
 */
public class MarkEntity {
    private double excelentMark;
    private double goodMark;
    private double badMark;
    private int metricId;
    private int expertId;
    private int id;
    private double weight;

    public MarkEntity() {
    }

    public MarkEntity(double excelentMark, double goodMark, double badMark, int metricId, int expertId, double weight) {
        this.excelentMark = excelentMark;
        this.goodMark = goodMark;
        this.badMark = badMark;
        this.metricId = metricId;
        this.expertId = expertId;
    }

    public double getExcelentMark() {
        return excelentMark;
    }

    public void setExcelentMark(double excelentMark) {
        this.excelentMark = excelentMark;
    }

    public double getGoodMark() {
        return goodMark;
    }

    public void setGoodMark(double goodMark) {
        this.goodMark = goodMark;
    }

    public double getBadMark() {
        return badMark;
    }

    public void setBadMark(double badMark) {
        this.badMark = badMark;
    }

    public int getMetricId() {
        return metricId;
    }

    public void setMetricId(int metricId) {
        this.metricId = metricId;
    }

    public int getExpertId() {
        return expertId;
    }

    public void setExpertId(int expertId) {
        this.expertId = expertId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MarkEntity that = (MarkEntity) o;

        if (Double.compare(that.badMark, badMark) != 0) return false;
        if (Double.compare(that.excelentMark, excelentMark) != 0) return false;
        if (expertId != that.expertId) return false;
        if (Double.compare(that.goodMark, goodMark) != 0) return false;
        if (metricId != that.metricId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = excelentMark != +0.0d ? Double.doubleToLongBits(excelentMark) : 0L;
        result = (int) (temp ^ (temp >>> 32));
        temp = goodMark != +0.0d ? Double.doubleToLongBits(goodMark) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = badMark != +0.0d ? Double.doubleToLongBits(badMark) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + metricId;
        result = 31 * result + expertId;
        return result;
    }


}
