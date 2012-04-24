package ru.kirsanov.mca.other.dao;

/**
 * User: akirsanov
 * Date: 23.04.12 23:40
 */
public class MetricEntity {
    private int id;
    private String name;

    public MetricEntity(String name) {
        this.name = name;
    }

    public MetricEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MetricEntity that = (MetricEntity) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
