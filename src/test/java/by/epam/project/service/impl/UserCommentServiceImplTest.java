package by.epam.project.service.impl;

import by.epam.project.connection.ConnectionPool;
import by.epam.project.entity.UserComment;
import by.epam.project.exception.ServiceException;
import by.epam.project.service.UserCommentService;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class UserCommentServiceImplTest {
    @DataProvider
    public Object[][] testServiceFindUserCommentsById() {                               //positive
        return new Object[][]{
                {1,"2020-03-25", "Cool"},
        };
    }

    @Test(dataProvider = "testServiceFindUserCommentsById")
    public void testServiceFindUserCommentsById(int userCommentId,String ticketDate, String comment) throws ServiceException {
        ConnectionPool.INSTANCE.getConnection();
        UserCommentService userCommentService=new UserCommentServiceImpl();
        UserComment userComment = userCommentService.findComments(userCommentId, ticketDate).get(2);
        Assert.assertEquals(userComment.getComment(), comment);
    }


}
