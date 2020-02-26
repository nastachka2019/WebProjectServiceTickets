package by.epam.project.service.impl;

import by.epam.project.connection.ConnectionPool;
import by.epam.project.entity.User;
import by.epam.project.exception.ServiceException;
import by.epam.project.service.UserService;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class UserServiceImplTest {
    @DataProvider
    public Object[][] testServiceFindUserById() {
        return new Object[][]{
                {1, "Nastya"},
                {2, "Katyaaa"},
                {3, "William1"},
        };
    }

    @Test(dataProvider = "testServiceFindUserById")             //positive
    public void testFindById(int id, String login) throws ServiceException {
        ConnectionPool.INSTANCE.getConnection();
        UserService userService = new UserServiceImpl();
        User user = userService.findUserById(id);
        Assert.assertEquals(user.getLogin(), login);
    }
    @DataProvider
    public Object[][] findByLoginAndPass() {           //negative
        return new Object[][]{
                {"Anastasiya", "210789"},
                {"werf", "4454t"},
                {"ghyjj", "ererf"}
        };
    }

    @Test(dataProvider = "findByLoginAndPass")
    public void findByUsernameAndPass(final String login, final String password) throws ServiceException {
        ConnectionPool.INSTANCE.getConnection();
        UserService userService = new UserServiceImpl();
        User user = userService.findByLoginAndPass(login, password).get(1); //??????
        Assert.assertEquals(user.getLogin(), login);
    }

    @DataProvider
    public Object[][] testFindUserByEmailAndPassword() {           //positive
        return new Object[][]{
                {"shpakavaa@gmail.com", "Nastya210789"},

        };
    }

    @Test(dataProvider = "testFindUserByEmailAndPassword")
    public void testFindUserByEmailAndPassword(String email, String password) throws ServiceException {
        ConnectionPool.INSTANCE.getConnection();
        UserService userService = new UserServiceImpl();
        User user = userService.findByEmailAndPass(email, password).get(0);
        Assert.assertEquals(user.getEmail(), email );
    }

}