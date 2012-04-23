package ru.kirsanov.mca.other.dao;

import org.junit.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * User: akirsanov
 * Date: 24.04.12 0:22
 */
public class MetricDAOTest {
    static Connection connection;
    private MetricDAO metricDAO;
    private MetricEntity metricEntity;
    private Statement statement;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        Class.forName(Configuration.getInstance().getDbClassString());
        connection = DriverManager.getConnection(Configuration.getInstance()
                .getJDBCString(), Configuration.getInstance()
                .getUserName(), Configuration.getInstance()
                .getUserPass());
    }

    @Before
    public void setUp() throws Exception {
        Statement statement = connection.createStatement();
        try {
            statement
                    .executeUpdate("CREATE TABLE IF NOT EXISTS metric (id INTEGER PRIMARY KEY AUTO_INCREMENT, "
                            + "name VARCHAR(60));");
        } finally {
            statement.close();
        }
        metricDAO = new MetricDAO();
        metricEntity = new MetricEntity();
        metricEntity.setName("Name");
    }


    @Test
    public void testInsert() throws Exception {
        assertTrue(metricDAO.insert(metricEntity));
    }

    @Test
    public void testGetAllRecords() throws Exception {
        metricDAO.insert(metricEntity);
        List<MetricEntity> recordList = metricDAO.getAllRecords();
        assertEquals(metricEntity, recordList.get(0));
    }

    @Test
    public void testGetRecords() throws Exception {
        metricDAO.insert(metricEntity);

        List<MetricEntity> recordList = metricDAO.getRecords(metricDAO.getAllRecords().get(0).getId());
        assertEquals(metricEntity, recordList.get(0));

    }

    @After
    public void tearDownAfter() throws Exception {
        statement = connection.createStatement();
        try {
            statement.executeUpdate("DROP TABLE metric;");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS metric (id INTEGER PRIMARY KEY AUTO_INCREMENT, "
                    + "name VARCHAR(60));");
        } finally {
            statement.close();
        }
    }

    @AfterClass
    public static void tearDownAfterClass() throws SQLException {
        connection.close();
    }
}
