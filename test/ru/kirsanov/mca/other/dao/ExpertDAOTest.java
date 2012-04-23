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
public class ExpertDAOTest {
    static Connection connection;
    private ExpertDAO expertDAO;
    private ExpertEntity expertEntity;
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
                    .executeUpdate("CREATE TABLE IF NOT EXISTS expert (id INTEGER PRIMARY KEY AUTO_INCREMENT, "
                            + "name VARCHAR(60));");
        } finally {
            statement.close();
        }
        expertDAO = new ExpertDAO();
        expertEntity = new ExpertEntity();
        expertEntity.setName("Name");
    }


    @Test
    public void testInsert() throws Exception {
        assertTrue(expertDAO.insert(expertEntity));
    }

    @Test
    public void testGetAllRecords() throws Exception {
        expertDAO.insert(expertEntity);
        List<ExpertEntity> recordList = expertDAO.getAllRecords();
        assertEquals(expertEntity, recordList.get(0));
    }

    @Test
    public void testGetRecords() throws Exception {
        expertDAO.insert(expertEntity);

        List<ExpertEntity> recordList = expertDAO.getRecords(expertDAO.getAllRecords().get(0).getId());
        assertEquals(expertEntity, recordList.get(0));

    }

    @After
    public void tearDownAfter() throws Exception {
        statement = connection.createStatement();
        try {
            statement.executeUpdate("DROP TABLE expert;");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS expert (id INTEGER PRIMARY KEY AUTO_INCREMENT, "
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
