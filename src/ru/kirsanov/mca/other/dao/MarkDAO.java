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
public class MarkDAO extends DAO {
    public MarkDAO() {
        super();
        try {
            statement = connection.createStatement();
            statement
                    .executeUpdate("CREATE TABLE IF NOT EXISTS marks (id INTEGER PRIMARY KEY AUTO_INCREMENT, "
                            + "excelent DOUBLE, good DOUBLE, bad DOUBLE,"
                            + "expertId INT, metricID INT);");
        } catch (Exception e) {

        }
    }

    public boolean insert(MarkEntity record) throws SQLException {
        PreparedStatement insertStatement = connection
                .prepareStatement("INSERT INTO marks VALUES (NULL, ?,?,?,?,?);");
        try {
            insertStatement.setDouble(1, record.getExcelentMark());
            insertStatement.setDouble(2, record.getGoodMark());
            insertStatement.setDouble(3, record.getBadMark());
            insertStatement.setInt(4, record.getExpertId());
            insertStatement.setInt(5, record.getMetricId());
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

    private List<MarkEntity> fillRecord(PreparedStatement statement)
            throws SQLException {
        connection.setAutoCommit(false);
        ResultSet resultSetOfRecord = statement.executeQuery();
        List<MarkEntity> listOfRecord = new LinkedList<MarkEntity>();
        while (resultSetOfRecord.next()) {
            MarkEntity record = new MarkEntity();
            record.setId(resultSetOfRecord.getInt("id"));
            record.setExcelentMark(resultSetOfRecord.getDouble("excelent"));
            record.setGoodMark(resultSetOfRecord.getDouble("good"));
            record.setBadMark(resultSetOfRecord.getDouble("bad"));
            record.setExpertId(resultSetOfRecord.getInt("expertId"));
            record.setMetricId(resultSetOfRecord.getInt("metricID"));
            listOfRecord.add(record);
        }
        connection.setAutoCommit(true);
        return listOfRecord;
    }

    public List<MarkEntity> getAllRecords() {
        try {
            PreparedStatement selectAllRecordsStatement = connection
                    .prepareStatement("SELECT * FROM marks");
            selectAllRecordsStatement.addBatch();
            return this.fillRecord(selectAllRecordsStatement);
        } catch (Exception e) {
            return new LinkedList<MarkEntity>();
        }

    }

    public List<MarkEntity> getRecords(int metricId) throws SQLException {
        PreparedStatement selectRecord = connection
                .prepareStatement("SELECT * FROM marks where metricId=?");
        selectRecord.setInt(1, metricId);
        return this.fillRecord(selectRecord);
    }


    @Override
    public boolean clearTable() {
        try {
            statement.executeUpdate("DROP TABLE IF EXISTS marks;");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS marks (id INTEGER PRIMARY KEY AUTO_INCREMENT, "
                    + "excelent DOUBLE, good DOUBLE, bad DOUBLE,"
                    + "expertId INT, metricID INT);");
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
