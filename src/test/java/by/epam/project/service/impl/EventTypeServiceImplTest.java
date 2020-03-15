package by.epam.project.service.impl;

import by.epam.project.connection.ConnectionPool;
import by.epam.project.entity.Activity;
import by.epam.project.entity.EventType;
import by.epam.project.exception.ServiceException;
import by.epam.project.service.ActivityService;
import by.epam.project.service.EventTypeService;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class EventTypeServiceImplTest {

    @DataProvider(name="findEventTypeByIdTest")
    public Object[][] findEventTypeByIdTest() {
        return new Object[][]{
                {1, "Concert"},
                {2, "Ballet"},
                {3,"Cinema"}
        };
    }

    @Test (dataProvider = "findEventTypeByIdTest")
    public void findEventTypeByIdTest(int idEventType, String nameEventType) throws ServiceException {
        ConnectionPool.INSTANCE.getConnection();
        EventTypeService eventTypeService=new EventTypeServiceImpl();
       EventType eventType = eventTypeService.findEventTypeById(idEventType);
        Assert.assertEquals(eventType.getEventTypeValue(), nameEventType);
    }

}
