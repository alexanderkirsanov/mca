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
public class ExpertDAO extends DAO {
    public ExpertDAO() {
        super();
        try {
            statement = connection.createStatement();
            statement
                    .executeUpdate("CREATE TABLE IF NOT EXISTS expert (id INTEGER PRIMARY KEY AUTO_INCREMENT, "
                            + "name VARCHAR(60));");
        } catch (Exception e) {

        }
    }

    public boolean insert(ExpertEntity record) throws SQLException {
        PreparedStatement insertStatement = connection
                .prepareStatement("INSERT INTO expert VALUES (NULL, ?);");
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

    private List<ExpertEntity> fillRecord(PreparedStatement statement)
            throws SQLException {
        connection.setAutoCommit(false);
        ResultSet resultSetOfRecord = statement.executeQuery();
        List<ExpertEntity> listOfRecord = new LinkedList<ExpertEntity>();
        while (resultSetOfRecord.next()) {
            ExpertEntity record = new ExpertEntity();
            record.setId(resultSetOfRecord.getInt("id"));
            record.setName(resultSetOfRecord.getString("name"));
            listOfRecord.add(record);
        }
        connection.setAutoCommit(true);
        return listOfRecord;
    }

    public List<ExpertEntity> getAllRecords() {
        try {
            PreparedStatement selectAllRecordsStatement = connection
                    .prepareStatement("SELECT * FROM expert");
            selectAllRecordsStatement.addBatch();
            return this.fillRecord(selectAllRecordsStatement);
        } catch (Exception e) {
            return new LinkedList<ExpertEntity>();
        }

    }
    public List<ExpertEntity> getRecords(int id) throws SQLException {
        PreparedStatement selectRecord = connection
                .prepareStatement("SELECT * FROM expert where id=?");
        selectRecord.setInt(1, id);
        return this.fillRecord(selectRecord);
    }

    @Override
    public boolean clearTable() {
        try {
            statement.executeUpdate("DROP TABLE IF EXISTS expert;");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS expert (id INTEGER PRIMARY KEY AUTO_INCREMENT , "
                    + "name VARCHAR(60));");
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
