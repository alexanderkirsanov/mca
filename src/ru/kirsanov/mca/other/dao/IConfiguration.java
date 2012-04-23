package ru.kirsanov.mca.other.dao;

/**
 * User: akirsanov
 * Date: 23.04.12 23:35
 */

public interface IConfiguration {

    void setJDBCString(String jdbcString);

    String getJDBCString();

    void setDbClassString(String dbClass);

    String getDbClassString();

    void setDataFormat(String dataFormat);

    String getUserName();

    void setUserName(String userName);

    String getUserPass();

    void setUserPass(String pass);

    String getDateFormat();

    void setEmailServer(String server);

    String getEmailServer();

    void setEmailPort(String port);

    String getEmailPort();
}