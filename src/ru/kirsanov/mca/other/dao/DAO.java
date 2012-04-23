package ru.kirsanov.mca.other.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public abstract class DAO {
    protected Connection connection;
    protected Statement statement;

    public DAO(){
        try {
            IConfiguration configuration = Configuration.getInstance();
            Class.forName(configuration.getDbClassString());
            connection = DriverManager.getConnection(configuration.getJDBCString(), configuration.getUserName(),configuration.getUserPass());
            statement = connection.createStatement();
        } catch (Exception e) {

        }
    }

    protected int checkFailCounts(int... updateCounts) {
        int failCounts = 0;
        for (int currentCounts : updateCounts) {
            if (currentCounts == Statement.EXECUTE_FAILED) {
                failCounts++;
            }
        }
        return failCounts;
    }

    public abstract boolean clearTable();
}
