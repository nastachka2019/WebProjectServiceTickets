package by.epam.project.servlet.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebFilter(urlPatterns = { "/*" })
public class CommandFilter implements Filter {

    private static final Logger logger= LogManager.getLogger();

    private static final String COMMAND = "command";

    private static Map<String, String> commands = new HashMap<>();

    static {
        commands.put("/registration", "visit_registration");
        commands.put("/sign_in", "visit_sign_in");
        commands.put("/settings", "visit_settings");
        commands.put("/search", "search");
        commands.put("/event_list", "event_list");
        commands.put("/visit_event_list", "visit_event_list");
        commands.put("/add_ticket", "add_ticket");
        commands.put("/user_list", "user_list");
        commands.put("/show_user_details", "show_user_details");
        commands.put("/order", "order");
        commands.put("/show_order", "show_order");
        commands.put("/show_event_details", "show_event_details");
        commands.put("/comment","comment");
        commands.put("/delete_comment", "delete_comment");
        commands.put("/add_comment", "add_comment");
        commands.put("/add_order", "add_order");
    }

    public void init(FilterConfig config)  {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException {

        if (request instanceof HttpServletRequest) {

            HttpServletRequest httpRequest = (HttpServletRequest) request;

            String contextPath = httpRequest.getContextPath();
            String uri = httpRequest.getRequestURI();

            int beginOfCommand = contextPath.length();
            String commandInURI = uri.substring(beginOfCommand);
            String command = commands.get(commandInURI);

            httpRequest.setAttribute(COMMAND, command);

            try {
                chain.doFilter(request, response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            logger.error("You are trying to use HTTP filter for not HTTP request");
        }
    }

    public void destroy() {
    }

}
