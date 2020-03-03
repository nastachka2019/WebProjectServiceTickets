package by.epam.project.servlet.filter;
import by.epam.project.command.Command;
import by.epam.project.command.CommandMap;
import by.epam.project.command.CommandType;
import by.epam.project.entity.User;
import by.epam.project.entity.UserRole;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
@WebFilter(urlPatterns = {"/*"})
public class ServletSecurityFilter implements Filter {

    private static final Logger logger = LogManager.getLogger();

    private static final String USER = "User";
    private static final String COMMAND = "command";
    private static final String ERROR = "error";

    private static final String SIGN_IN_PATH = "/sign_in";
    private static final String EVENT_LIST_PATH = "/visit_event_list";

    public void init(FilterConfig config) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {

            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpServletResponse httpResponse = (HttpServletResponse) response;

            User user = (User) httpRequest.getSession().getAttribute(USER);

            CommandMap commandMap = CommandMap.getInstance();

            if (httpRequest.getAttribute(COMMAND) != null) {
                Command command = commandMap.receiveCommand((String) httpRequest.getAttribute(COMMAND));
                List<UserRole> userRoleList = CommandType
                        .valueOf(((String) httpRequest.getAttribute(COMMAND)).toUpperCase())
                        .getUserRoleList();
                if (user != null) {
                    if (userRoleList.contains(user.getUserRole())) {
                        chain.doFilter(request, response);
                    } else {
                        httpRequest.getSession().setAttribute(ERROR, "Sorry! You have no rights to visit this page.");
                        httpResponse.sendRedirect(httpRequest.getContextPath() + EVENT_LIST_PATH);
                    }
                } else {
                    if (userRoleList.contains(UserRole.ANONYMOUS)) {
                        chain.doFilter(request, response);
                    } else {
                        httpRequest.getSession().setAttribute(ERROR, "Sign in for visiting this page");
                        httpResponse.sendRedirect(httpRequest.getContextPath() + SIGN_IN_PATH);
                    }
                }
            } else {
                chain.doFilter(request, response);
            }


        } else {
            logger.error("You are trying to use HTTP filter for not HTTP request and/or response");
        }
    }

    public void destroy() {
    }
}