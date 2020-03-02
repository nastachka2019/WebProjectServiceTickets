package by.epam.project.dao.impl;

import by.epam.project.dao.ActivityDao;
import by.epam.project.dao.UserCommentDao;
import by.epam.project.entity.*;
import by.epam.project.exception.ConnectionPoolException;
import by.epam.project.exception.DaoException;
import by.epam.project.exception.ServiceException;
import by.epam.project.service.*;
import by.epam.project.service.impl.*;

import java.time.LocalDate;

@Deprecated
public class TestDao {
    public static void main(String[] args) throws DaoException, ConnectionPoolException, ServiceException {
//        UserDaoImpl userDao=new UserDaoImpl();
//        User user=new User(2,UserRole.USER,"Kate","Smith", "kate123@gmail.com", "Katyaaa", "Katya12345", "375331111111", LocalDate.of(2000,01,01));
//        userDao.insert(user);
//        System.out.println("jr");
//      ActivityDaoImpl eventDao=new ActivityDaoImpl();
//      Activity activity= new Activity(1, "Queen's concert", null, "Concert of the legendary Queen","Minsk, Pobeditelej str., Prime Hall",LocalDate.of(2020,03,21),"2s-100s" );
ActivityService activityService=new ActivityServiceImpl();
        //System.out.println(activityService.findByNameOrWordInNameWithLimit("s%", 0,8));
        System.out.println(activityService.findByNameOrWordInName("sw%"));
    //    System.out.println(activityService.findByEventId(5));
      //  System.out.println(activityService.findEventByLimit(1,3));
        UserService userService= new UserServiceImpl();
      //  System.out.println(userService.containsLogin("Arkadiy12"));
       //System.out.println(userService.findByLoginAndPass("Katyaaa", "Katya12345"));
        //System.out.println(userService.findUserById(2));
        TicketService ticketService=new TicketServiceImpl();
      //  System.out.println(ticketService.findTicketByUserIdAndTicketDateAndEventType(3,"2020-04-21","Opera"));
   //     System.out.println(ticketService.totalPriceByUserIdAndTicketDate(2,"2020-03-19"));



//        System.out.println(userCommentDao.findComments(2,"2020-03-25"));
        // System.out.println(userCommentService.findComments(2,"2020-03-25 00:00:00"));

    }
}
