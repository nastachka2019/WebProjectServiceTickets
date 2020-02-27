package by.epam.project.connection;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ConnectionPoolTest {
    private ConnectionPool pool;

    @BeforeMethod
    public void setUp() {
        pool = ConnectionPool.INSTANCE;
    }

    @AfterMethod
    public void tearDown() {
        pool = null;
    }


    @Test
    public void testCheckPoolSize() {  //positive
        int expected = 32;
        int actual = pool.checkPoolSize();
        assertEquals(actual, expected);
    }
}