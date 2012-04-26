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
public class MarkDAOTest {
    static Connection connection;
    private MarkDAO markDao;
    private MarkEntity mark;
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
                    .executeUpdate("CREATE TABLE IF NOT EXISTS marks (id INTEGER PRIMARY KEY AUTO_INCREMENT, "
                            + "excelent DOUBLE, good DOUBLE, bad DOUBLE,"
                            + "expertId INT, metricID INT, weight DOUBLE);");
        } finally {
            statement.close();
        }
        markDao = new MarkDAO();
        mark = new MarkEntity();
        mark.setBadMark(1);
        mark.setGoodMark(2);
        mark.setExcelentMark(3);
        mark.setMetricId(1);
        mark.setExpertId(1);
        mark.setWeight(1d);
    }


    @Test
    public void testInsert() throws Exception {
        assertTrue(markDao.insert(mark));
    }

    @Test
    public void testGetAllRecords() throws Exception {
        markDao.insert(mark);
        List<MarkEntity> recordList = markDao.getAllRecords();
        assertEquals(mark, recordList.get(0));
    }

    @Test
    public void testGetRecords() throws Exception {
        markDao.insert(mark);

        List<MarkEntity> recordList = markDao.getRecords(markDao.getAllRecords().get(0).getId());
        assertEquals(mark, recordList.get(0));

    }

    @After
    public void tearDownAfter() throws Exception {
        statement = connection.createStatement();
        try {
            statement.executeUpdate("DROP TABLE marks;");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS marks (id INTEGER PRIMARY KEY AUTO_INCREMENT, "
                    + "excelent DOUBLE, good DOUBLE, bad DOUBLE,"
                    + "expertId INT, metricID INT, weight DOUBLE);");
        } finally {
            statement.close();
        }
    }

    @AfterClass
    public static void tearDownAfterClass() throws SQLException {
        connection.close();
    }
}
