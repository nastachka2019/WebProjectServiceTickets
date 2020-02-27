package by.epam.project.servlet;

import by.epam.project.command.*;
import by.epam.project.connection.ConnectionPool;
import by.epam.project.exception.CommandException;

import by.epam.project.exception.ConnectionPoolException;
import by.epam.project.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * controller получает запрос, анализирует его и вызывает соответствующий метод
 *
 * @author Shpakova A.
 */


@WebServlet(urlPatterns = {"/registration", "/sign_in", "/sign_out", "/settings", "/search",  //команды в commandtype
        "/add_order","/event_list", "/delete_ticket","/update_quantity_in_order", "/order","/show_order","/visit_event_list",
        "/translate","/show_information_about_event","/show_user_details","/user_list",
        "/delete_user","/delete_comment","/add_ticket", "/comment","/show_event_details","/add_comment","/show-event_list","/change_user_role"})
public class Controller extends HttpServlet {

    private static final Logger logger= LogManager.getLogger();

    private static final String COMMAND = "command";
    private static final String ERROR = "error";
    private static final String STATUS_CODE = "statusCode";
    private static final String RESPONSE = "response";

    @Override
    public void init() throws ServletException {
       ConnectionPool connectionPool= ConnectionPool.INSTANCE;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (CommandException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (CommandException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }


    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws CommandException, ServiceException, IOException, ServletException {
        String page;
      CommandMap commandMap=CommandMap.getInstance();

        if (request.getParameter(COMMAND) != null) {
            String commandStr = request.getParameter(COMMAND);
            logger.info("Request. Parameter = " + commandStr);

            Command command = commandMap.receiveCommand(commandStr);
            page = command.execute(request);

        }  else {
          logger.error("Command not received");
            request.setAttribute(ERROR, "Command not received");
            request.setAttribute(STATUS_CODE, 404);
            page = PathForJsp.ERROR.getUrl();
        }
        if (page != null) {
            if (request.getAttribute(RESPONSE) != null && (boolean) request.getAttribute(RESPONSE)) {
                response.sendRedirect(page);
            } else {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
                dispatcher.forward(request, response);
            }
        }

    }

    @Override
    public void destroy() {
        try {
            ConnectionPool.INSTANCE.destroyPool();
        } catch (InterruptedException e) {
          logger.error(e.getMessage(), e);
        }
    }
}
