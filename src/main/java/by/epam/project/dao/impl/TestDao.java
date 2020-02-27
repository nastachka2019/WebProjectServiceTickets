package by.epam.project.dao.impl;

import by.epam.project.dao.ActivityDao;
import by.epam.project.dao.UserCommentDao;
import by.epam.project.entity.*;
import by.epam.project.exception.ConnectionPoolException;
import by.epam.project.exception.DaoException;
import by.epam.project.exception.ServiceException;
import by.epam.project.service.ActivityService;
import by.epam.project.service.EventTypeService;
import by.epam.project.service.TicketService;
import by.epam.project.service.UserCommentService;
import by.epam.project.service.impl.ActivityServiceImpl;
import by.epam.project.service.impl.EventTypeServiceImpl;
import by.epam.project.service.impl.TicketServiceImpl;
import by.epam.project.service.impl.UserCommentServiceImpl;

import java.time.LocalDate;

@Deprecated
public class TestDao {
    public static void main(String[] args) throws DaoException, ConnectionPoolException, ServiceException {
//        UserDaoImpl userDao=new UserDaoImpl();
//        User user=new User(2,UserRole.USER,"Kate","Smith", "kate123@gmail.com", "Katyaaa", "Katya12345", "375331111111", LocalDate.of(2000,01,01));
// //       userDao.insert(user);
////        System.out.println("jr");
//      ActivityDaoImpl eventDao=new ActivityDaoImpl();
//      Activity activity= new Activity(1, "Queen's concert", null, "Concert of the legendary Queen","Minsk, Pobeditelej str., Prime Hall",LocalDate.of(2020,03,21),"2s-100s" );
////        System.out.println(eventDao.findById(2));
//        System.out.println(eventDao.findById(2));
//        UserDaoImpl userDao=new UserDaoImpl();
//        System.out.println(userDao.findById(2));
//        TicketDaoImpl ticketDao=new TicketDaoImpl();
//        System.out.println(ticketDao.findDatesByUserId(2));
  //      ActivityService activityService=new ActivityServiceImpl();
   //     System.out.println(activityService.takeAllEvents());
//        EventTypeService eventTypeService=new EventTypeServiceImpl();
//      EventType eventType=new EventType(1, "Concert");
////        System.out.println(eventTypeService.findIdByEventType("sport"));
//        TicketService ticketService=new TicketServiceImpl();
//        Ticket ticket=new Ticket(11,activity,eventType, user,2,LocalDate.of(2020, 04, 20));
//
//        ticketService.insertTicket(ticket);
//        UserCommentService userCommentService=new UserCommentServiceImpl();
//        UserCommentDao userCommentDao=new UserCommentDaoImpl();
//        System.out.println(userCommentDao.findComments(2,"2020-03-25"));
    // System.out.println(userCommentService.findComments(2,"2020-03-25 00:00:00"));
        ActivityDaoImpl activityDao= new ActivityDaoImpl();
        System.out.println(activityDao.findMinPrice());



    }
}
