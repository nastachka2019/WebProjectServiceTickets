package by.epam.project.command.impl;

import by.epam.project.command.Command;
import by.epam.project.command.PathForJsp;
import by.epam.project.entity.User;
import by.epam.project.exception.CommandException;
import by.epam.project.exception.ServiceException;
import by.epam.project.service.UserService;
import by.epam.project.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Этот класс используется для команды sign_in
 *
 * @author Shpakova A.
 */

public class SignInCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    private static final String EMAIL_OR_USERNAME = "emailOrUsername";
    private static final String PASSWORD = "password";
    private static final String USER = "User";
    private static final String ERROR_INPUT_DATA = "errorInputData";
    private static final String RESPONSE = "response";
    private static final String EVENT_LIST_PATH = "/visit_event_list";
    private static final String ERROR = "error";
    private static final String STATUS_CODE = "statusCode";
    private UserService userService = new UserServiceImpl();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page;
        String emailOrUsername = request.getParameter(EMAIL_OR_USERNAME);
        String password = request.getParameter(PASSWORD);

        if(emailOrUsername!=null && password!=null){
            User user = null;
            try {
            if (emailOrUsername.contains("@")) {

                    if (!userService.findByEmailAndPass(emailOrUsername, password).isEmpty()) {
                        user = userService.findByEmailAndPass(emailOrUsername, password).get(0);
                    }

            } else {
                if (!userService.findByLoginAndPass(emailOrUsername, password).isEmpty()) {
                    user = userService.findByLoginAndPass(emailOrUsername, password).get(0);
                }
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
            if(user!=null){
               logger.info("User " + user.getLogin() + " signed in.");
               request.getSession().setAttribute(USER, user);
                request.setAttribute(RESPONSE, true);
                page = request.getContextPath() + EVENT_LIST_PATH;
            }else{
                logger.error("You entered incorrect login or email or password");
                request.setAttribute(ERROR_INPUT_DATA, "Incorrect login or email or password");
                request.setAttribute(EMAIL_OR_USERNAME, emailOrUsername);
                page = PathForJsp.SIGN_IN.getUrl();
            }
        } else {
            logger.error("Error request");
            request.setAttribute(ERROR, "Error request");
            request.setAttribute(STATUS_CODE, 404);
            page = PathForJsp.ERROR.getUrl();
        }
        return page;

    }

}

