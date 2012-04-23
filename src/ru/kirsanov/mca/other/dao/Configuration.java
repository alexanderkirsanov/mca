package ru.kirsanov.mca.other.dao;



import java.util.Properties;

public class Configuration implements IConfiguration {
    private static IConfiguration configuration = null;
    private String userName;
    private Properties configProperties;
    private String pass;
    private String jdbcString;
    private String dbClass;
    private String dataFormat;
    private String server;
    private String port;

    private static final String CODEPAGE = "UTF8";
    private static final String USER_NAME = "userName";
    private static final String USER_PASS = "userPass";
    private static final String JDBC_STRING = "jdbcString";
    private static final String DB_CLASS = "dbClass";
    private static final String DATA_FORMAT = "dataFormat";
    private static final String SERVER = "server";
    private static final String PORT = "port";
    private static ConfigurationManager configurationManager = null;

    public static IConfiguration getInstance() {
        if (configuration == null) {
            configurationManager = new ConfigurationManager();
            configuration = new Configuration();
        }
        return configuration;
    }

    private Configuration() {
        configProperties = configurationManager.loadProperties();
        this.pass = configProperties.getProperty(USER_PASS, "4f3v6");
        this.userName = configProperties.getProperty(USER_NAME, "lqip32");
        this.jdbcString = configProperties.getProperty(JDBC_STRING,
                "jdbc:mysql://localhost:3307/metric");
        this.dbClass = configProperties
                .getProperty(DB_CLASS, "com.mysql.jdbc.Driver");
        this.dataFormat = configProperties.getProperty(DATA_FORMAT, "dd.MM.YYYY");
        this.port = configProperties.getProperty(PORT, "465");
        this.server = configProperties.getProperty(SERVER, "smtp.google.com");
    }

    private void saveParametr(String parametrName, String value) {
        configProperties.put(parametrName, value);
        configurationManager.saveProperties(configProperties);
    }

    @Override
    public void setUserName(String userName) {
        this.userName = userName;
        saveParametr(USER_NAME, this.userName);
    }

    @Override
    public String getUserName() {
        return this.userName;
    }

    @Override
    public String getUserPass() {
        return this.pass;
    }

    @Override
    public void setUserPass(String pass) {
        this.pass = pass;
        saveParametr(USER_PASS, this.pass);
    }

    @Override
    public void setJDBCString(String jdbcString) {
        this.jdbcString = jdbcString;
        saveParametr(JDBC_STRING, this.jdbcString);
    }

    @Override
    public String getJDBCString() {
        return this.jdbcString;
    }

    @Override
    public void setDbClassString(String dbClass) {
        this.dbClass = dbClass;
        saveParametr(DB_CLASS, this.dbClass);
    }

    @Override
    public String getDbClassString() {
        return this.dbClass;
    }

    @Override
    public void setDataFormat(String dataFormat) {
        this.dataFormat = dataFormat;
        saveParametr(DATA_FORMAT, this.dataFormat);

    }

    @Override
    public String getDateFormat() {
        return this.dataFormat;
    }

    @Override
    public void setEmailServer(String server) {
        this.server = server;
        saveParametr(SERVER, this.server);
    }

    @Override
    public String getEmailServer() {
        return this.server;
    }

    @Override
    public void setEmailPort(String port) {
        this.port = port;
        saveParametr(PORT, this.port);
    }

    @Override
    public String getEmailPort() {
        return this.port;
    }

}
