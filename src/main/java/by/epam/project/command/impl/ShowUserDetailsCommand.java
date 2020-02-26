package by.epam.project.command.impl;

import by.epam.project.command.Command;
import by.epam.project.command.PathForJsp;
import by.epam.project.entity.User;
import by.epam.project.exception.ServiceException;
import by.epam.project.service.UserService;
import by.epam.project.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Этот класс используется что бы показать детали пользователей администратору
 *
 * @author Shpakova A.
 */
public class ShowUserDetailsCommand implements Command {
    private static final String USER_ID = "userId";
    private static final String ERROR = "error";
    private static final String STATUS_CODE = "statusCode";
    private static final String USER = "user";
    private static final String GREATER_THAN_ONE_ADMIN = "greaterThanOneAdmin";
    private static final String REGEX_ID = "^[1-9]\\d*$";

    @Override
    public String execute(HttpServletRequest request) {

        if(request.getParameter(USER_ID) != null) {

            Pattern pattern = Pattern.compile(REGEX_ID);
            Matcher matcher = pattern.matcher(request.getParameter(USER_ID));

            if(matcher.matches()) {
                int userId = Integer.parseInt(request.getParameter(USER_ID));
                UserService userService = new UserServiceImpl();
                User currentUser = null;
                try {
                    currentUser = userService.findUserById(userId);
                } catch (ServiceException e) {
                    e.printStackTrace();
                }

                if(currentUser == null){
                    request.setAttribute(ERROR, "User with such id not found");
                    request.setAttribute(STATUS_CODE, 404);
                    return PathForJsp.ERROR.getUrl();
                } else{
                    request.setAttribute(USER, currentUser);
                    List<User> userList = null;
                    try {
                        userList = userService.takeAllUsers();
                    } catch (ServiceException e) {
                        e.printStackTrace();
                    }

                    if (userList.stream()
                            .filter(user -> user.getUserRole().name().equals("ADMINISTRATOR"))
                            .count() > 1) {
                        request.setAttribute(GREATER_THAN_ONE_ADMIN, true);
                    } else {
                        request.setAttribute(GREATER_THAN_ONE_ADMIN, false);
                    }
                }
                return PathForJsp.USER.getUrl();
            } else {
                request.setAttribute(ERROR, "Error request");
                request.setAttribute(STATUS_CODE, 404);
                return PathForJsp.ERROR.getUrl();
            }
        } else {
            request.setAttribute(ERROR, "Error request");
            request.setAttribute(STATUS_CODE, 404);
            return PathForJsp.ERROR.getUrl();
        }

    }
}
