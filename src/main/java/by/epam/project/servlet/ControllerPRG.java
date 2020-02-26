//package by.epam.project.servlet;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
///**
// * Этот сервлет предотвращает повторную отправку формы (посылает сообщение и перенаправляет на нужную стр jsp( защита от F5))
// *
// * @author Shpakova A.
// */
//@WebServlet(urlPatterns = {"/registration", "/sign_in", "/sign_out", "/settings", "/search",  //команды в commandtype
//        "/add_order","/event_list", "/delete_ticket","/update_quantity_in_order", "/order","/show_order","/visit_event_list",
//        "/translate","/show_information_about_event","/show_user_details","/user_list",
//        "/delete_user","/delete_comment", "/comment","/show_event_details","/add_comment","/show-event_list"})
//public class ControllerPRG extends HttpServlet {
//    public ControllerPRG() {
//        super();
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//      int success=Integer.parseInt(req.getParameter("s"));
//      if (success==1)
//          req.setAttribute("result", "Successfully inserted");
//      else
//            req.setAttribute("result", "Not inserted" + req.getAttribute("error"));
//        RequestDispatcher view = req.getRequestDispatcher("/jsp/registration.jsp");
//        view.forward(req,resp);
//      }
//    }
//
