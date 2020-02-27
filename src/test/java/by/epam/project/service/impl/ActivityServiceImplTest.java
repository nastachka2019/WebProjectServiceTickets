package by.epam.project.service.impl;

import by.epam.project.connection.ConnectionPool;
import by.epam.project.dao.impl.ActivityDaoImpl;
import by.epam.project.entity.Activity;
import by.epam.project.exception.ConnectionPoolException;
import by.epam.project.exception.DaoException;
import by.epam.project.exception.ServiceException;
import by.epam.project.service.ActivityService;

import org.testng.Assert;
import org.testng.annotations.*;


import java.util.List;

import static org.testng.Assert.assertEquals;

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
    @DataProvider(name="findByEventIdTest")
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

    @DataProvider(name="findEventByLimitTest")
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
    @DataProvider(name="findEventsByFilterTest")
    public Object[][] findEventsByFilterTestt() {
        return new Object[][]{
                {1, "concert",10,250},
        };
    }

    @Test (dataProvider = "findEventsByFilterTest")
    public void findEventsByFilterTest(int idEvent, String nameOrWordIname, int minPrice,int maxPrice ) throws ServiceException {
        ConnectionPool.INSTANCE.getConnection();
        ActivityService eventService=new ActivityServiceImpl();
        List <Activity> event= eventService.findEventsByFilter(nameOrWordIname,minPrice,maxPrice);
        Assert.assertEquals(event.get(1),idEvent);
         //      Expected :1
        //      Actual   :Event {eventId= 7, eventName= Concert, eventDescription= Tickets on popular concerts., eventAddress=Minsk, Lenina st., eventDate= 2020-02-02, price=100.00}
    }
    @DataProvider(name="findEventsByFilterWithoutSearchParamTest")
    public Object[][] findEventsByFilterWithoutSearchParamTest() {
        return new Object[][]{
                {1, 10,50},
        };
    }

    @Test (dataProvider = "findEventsByFilterWithoutSearchParamTest")    //positive
    public void findEventsByFilterWithoutSearchParamTest(int idEvent, int minPrice,int maxPrice ) throws ServiceException, ConnectionPoolException, DaoException {
        ConnectionPool.INSTANCE.getConnection();
        ActivityDaoImpl activityDao  =new ActivityDaoImpl();
        List <Activity> event= activityDao.findEventsByFilterWithoutSearchParam(minPrice,maxPrice);
        Assert.assertEquals(event.get(1),idEvent);
        //      Expected :1
        //      Actual   :Event {eventId= 6, eventName= Cinema, eventDescription= Tickets to the cinema.  Only in our cinema are the newest and most popular films, eventAddress=Minsk, Lenina st., eventDate= 2020-01-01, price=15.00}
    }

    private ConnectionPool pool;
    ActivityDaoImpl activityDao;

    @BeforeMethod
    public void setUp() {
        pool = ConnectionPool.INSTANCE;
    }

    @AfterMethod
    public void tearDown() {
        pool = null;
        activityDao=null;
    }
    @Test
    public void findMaxPriceTest() throws ConnectionPoolException, DaoException {
        int expected = 200;
        int actual = activityDao.findMinPrice();
        assertEquals(actual, expected);
    }
}