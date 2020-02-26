package by.epam.project.service.impl;

import by.epam.project.connection.ConnectionPool;
import by.epam.project.entity.Activity;
import by.epam.project.exception.ServiceException;
import by.epam.project.service.ActivityService;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import java.util.List;

public class ActivityServiceImplTest {
    @DataProvider(name="findByNameOrWordInNameTest")            //@DataProvider-передает в тест данные любго типа
    public Object[][] findByNameOrWordInNameTest() {
        return new Object[][]{
                {"concert", 1},
                {"oghg", 3},
                {"cinema", 1}
        };
    }

    @Test (dataProvider = "findByNameOrWordInNameTest")
    public void findByNameOrWordInNameTest(final String name, final int size) throws ServiceException {
      ConnectionPool.INSTANCE.getConnection();
        ActivityService eventService=new ActivityServiceImpl();
        List<Activity> events= eventService.findByNameOrWordInNameWithLimit(name, 0, 8);
        Assert.assertEquals(size, events.size());
    }
    @DataProvider(name="findByEventIdTest")            //@DataProvider-передает в тест данные любго типа
    public Object[][] findByEventIdTest() {
        return new Object[][]{
                {1, "Queen's concert"},
                {2, "Hockey championship"},
                {3,"Night Movie"}
        };
    }

    @Test (dataProvider = "findByEventIdTest")    //positive
    public void findByEventIdTest(int idEvent, String nameEvent) throws ServiceException {
        ConnectionPool.INSTANCE.getConnection();
        ActivityService eventService=new ActivityServiceImpl();
        Activity event= eventService.findByEventId(idEvent);
        Assert.assertEquals(event.getName(),nameEvent);
    }

    @DataProvider(name="findEventByLimitTest")            //@DataProvider-передает в тест данные любго типа
    public Object[][] findEventByLimitTest() {
        return new Object[][]{
                {"concert", 2},
                {"oghg", 3},
                {"cinema", 1}
        };
    }

    @Test (dataProvider = "findEventByLimitTest")
    public void findEventByLimitTest(int size) throws ServiceException {
        ConnectionPool.INSTANCE.getConnection();
        ActivityService eventService=new ActivityServiceImpl();
        List<Activity> events= eventService.findEventByLimit(0,1);
        Assert.assertEquals(events.size(), size);
    }
    @DataProvider(name="takeAllEventsTest")            //Expected :Queen's concert
    public Object[][] takeAllEventsTest() {           //Actual   :Event {eventId= 1, eventName= Queen's concert, eventDescription= Concert of the legendary Queen, eventAddress=Minsk, Pobeditelej str., "Prime Hall", eventDate= 2020-03-21, numberSeat=2s-100s}
        return new Object[][]{
                {"Queen's concert"},
                {"Hockey championship"},
                {"Night Movie"},
                {"Opera Rigoletto"},
                {"Ballet Swan lake"},
                {"Cinema"},
                {"Concert"},
                {"Exhibition"},
                {"Football championship"}
        };
    }

    @Test (dataProvider = "takeAllEventsTest")
    public void takeAllEventsTest(String name) throws ServiceException {
        ConnectionPool.INSTANCE.getConnection();
        ActivityService eventService=new ActivityServiceImpl();
        List<Activity> activity= eventService.takeAllEvents();
        Assert.assertEquals(activity.get(0), name);
    }


}