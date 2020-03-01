//package by.epam.project.servlet;
//
//import by.epam.project.command.Command;
//import by.epam.project.command.CommandManager;
//import by.epam.project.exception.CommandException;
//import by.epam.project.exception.ServiceException;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//@WebServlet(urlPatterns = {"/registration", "/sign_in", "/sign_out", "/settings", "/search",  //команды в commandtype
//        "/add_order","/event_list", "/delete_ticket","/update_quantity_in_order", "/order","/show_order","/visit_event_list",
//        "/translate","/show_information_about_event","/show_user_details","/user_list",
//        "/delete_user","/delete_comment","/add_ticket", "/comment","/show_event_details","/add_comment","/show-event_list","/change_user_role"})
//public class Servlet extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
//        try {
//            process(request, response);
//        } catch (CommandException e) {
//            e.printStackTrace();
//        } catch (ServiceException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
//        try {
//            process(request, response);
//        } catch (CommandException e) {
//            e.printStackTrace();
//        } catch (ServiceException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void process(HttpServletRequest request, HttpServletResponse response) throws CommandException, ServiceException {
//        String commandName = request.getParameter("command");
//
//        if (commandName != null) {
//            Command command = CommandManager.getCommand(commandName);
//
//            String page = command.execute(request,response);
//
//            RequestDispatcher dispatcher = request.getRequestDispatcher(page);
//
//            try {
//                dispatcher.forward(request, response);
//            } catch (ServletException | IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}
