package by.epam.project.servlet.filter;


import by.epam.project.entity.User;
import by.epam.project.exception.ServiceException;
import by.epam.project.service.UserService;
import by.epam.project.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * This filter is used to check if the type of the current user is changing in the database.
 * If the type has changed, the current user type is replaced by the corresponding
 *
 * @author Shpakova A.
 */

@WebFilter(urlPatterns = {"/*"},
        initParams = {
                @WebInitParam(name = "checkUser", value = "UTF-8", description = "Check type of user")})

public class AuthorizationFilter implements Filter {

    private static final Logger logger = LogManager.getLogger();

    private static final String USER = "User";

    /**
     * Method: initializes the filter
     *
     */
    public void init(FilterConfig filterConfig) {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        try {
            HttpServletRequest httpRequest = (HttpServletRequest) request;

            User currentUser = (User) httpRequest.getSession().getAttribute(USER);

            if (currentUser != null) {
                UserService userService = new UserServiceImpl();
                User currentUserDb = userService.findUserById(currentUser.getUserId());  //currentUserDb - юзер из бд

                if (currentUser == null) {
                    httpRequest.getSession().setAttribute(USER, null);
                } else if (currentUser.getUserRole() != currentUserDb.getUserRole()) {
                    httpRequest.getSession().setAttribute(USER, currentUserDb);
                }
            }

        } catch (ServiceException e) {
            logger.error(e);
        }

        chain.doFilter(request, response);
    }

    public void destroy() {
    }
}

