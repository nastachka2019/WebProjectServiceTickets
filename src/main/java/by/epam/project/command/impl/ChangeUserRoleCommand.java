package by.epam.project.command.impl;

import by.epam.project.command.Command;
import by.epam.project.entity.UserRole;
import by.epam.project.exception.ServiceException;
import by.epam.project.service.UserService;
import by.epam.project.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Этот класс используется для изменения прав пользователя
 *
 * @author Shpakova A.
 */

public class ChangeUserRoleCommand implements Command {
    private static final String USER_ID = "userId";
    private static final String USER_ROLE = "userRole";
    private static final String RESPONSE = "response";

    @Override
    public String execute(HttpServletRequest request) {
        int userId = Integer.parseInt(request.getParameter(USER_ID));
        String userRole = request.getParameter(USER_ROLE);
        UserService userService = new UserServiceImpl();
try{
        UserRole currentUserRole = userService.takeUserRole(userId);
        if (!currentUserRole.name().equals(userRole)) {


            userService.updateUserRole(userId, userRole);
        }} catch (ServiceException e) {
    e.printStackTrace();
}

        request.setAttribute(RESPONSE, true);
        return request.getHeader("Referer");
    }
}
