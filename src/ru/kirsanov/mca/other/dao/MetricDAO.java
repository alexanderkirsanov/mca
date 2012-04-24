package ru.kirsanov.mca.other.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * User: akirsanov
 * Date: 23.04.12 23:39
 */
public class MetricDAO extends DAO {
    public MetricDAO() {
        super();
        try {
            statement = connection.createStatement();
            statement
                    .executeUpdate("CREATE TABLE IF NOT EXISTS metric (id INTEGER PRIMARY KEY AUTO_INCREMENT, "
                            + "name VARCHAR(60));");
        } catch (Exception e) {

        }
    }

    public boolean insert(MetricEntity record) throws SQLException {
        PreparedStatement insertStatement = connection
                .prepareStatement("INSERT INTO metric VALUES (NULL, ?);");
        try {
            insertStatement.setString(1, record.getName());
            insertStatement.addBatch();
            connection.setAutoCommit(false);
            int[] updateCounts = insertStatement.executeBatch();
            connection.setAutoCommit(true);
            if (checkFailCounts(updateCounts) != 0)
                return false;

            return true;
        } finally {
            insertStatement.close();
        }
    }
    public List<MetricEntity> getRecords(int id) throws SQLException {
        PreparedStatement selectRecord = connection
                .prepareStatement("SELECT * FROM metric where id=?");
        selectRecord.setInt(1, id);
        return this.fillRecord(selectRecord);
    }

    public List<MetricEntity> getRecords(String name) throws SQLException{
           PreparedStatement selectRecord = connection
                .prepareStatement("SELECT * FROM metric where name=?");
        selectRecord.setString(1, name);
        return this.fillRecord(selectRecord);
    }

    private List<MetricEntity> fillRecord(PreparedStatement statement)
            throws SQLException {
        connection.setAutoCommit(false);
        ResultSet resultSetOfRecord = statement.executeQuery();
        List<MetricEntity> listOfRecord = new LinkedList<MetricEntity>();
        while (resultSetOfRecord.next()) {
            MetricEntity record = new MetricEntity();
            record.setId(resultSetOfRecord.getInt("id"));
            record.setName(resultSetOfRecord.getString("name"));
            listOfRecord.add(record);
        }
        connection.setAutoCommit(true);
        return listOfRecord;
    }

    public List<MetricEntity> getAllRecords() {
        try {
            PreparedStatement selectAllRecordsStatement = connection
                    .prepareStatement("SELECT * FROM metric");
            selectAllRecordsStatement.addBatch();
            return this.fillRecord(selectAllRecordsStatement);
        } catch (Exception e) {
            return new LinkedList<MetricEntity>();
        }

    }

    @Override
    public boolean clearTable() {
        try {
            statement.executeUpdate("DROP TABLE IF EXISTS metric;");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS metric (id INTEGER PRIMARY KEY AUTO_INCREMENT, "
                    + "name VARCHAR(60));");
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
