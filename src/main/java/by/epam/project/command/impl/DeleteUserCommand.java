package by.epam.project.command.impl;

import by.epam.project.command.Command;
import by.epam.project.entity.UserRole;
import by.epam.project.exception.ServiceException;
import by.epam.project.service.TicketService;
import by.epam.project.service.UserCommentService;
import by.epam.project.service.UserService;
import by.epam.project.service.impl.TicketServiceImpl;
import by.epam.project.service.impl.UserCommentServiceImpl;
import by.epam.project.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;


/**
 *  This class is used to delete users by the administrator.
 *
 * @author Shpakova A.
 */

public class DeleteUserCommand implements Command {
    private static final String USER_ID = "userId";
    private static final String RESPONSE = "response";
    private static final String USER_LIST_PATH = "/user_list";

    @Override
    public String execute(HttpServletRequest request) {

        int userId = Integer.parseInt(request.getParameter(USER_ID));

        UserService userService = new UserServiceImpl();
try{
        UserRole userRole = userService.takeUserRole(userId);
        TicketService ticketService = new TicketServiceImpl();
        ticketService.deleteTicketByUserId(userId);
        UserCommentService userCommentService = new UserCommentServiceImpl();
        userCommentService.deleteCommentsForUser(userId);
        userService.deleteUser(userId);
} catch (ServiceException e) {
    e.printStackTrace();
}
        request.setAttribute(RESPONSE, true);
        return request.getContextPath() + USER_LIST_PATH;
    }
}
