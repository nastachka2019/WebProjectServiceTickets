package by.epam.project.servlet;

import by.epam.project.command.*;
import by.epam.project.connection.ConnectionPool;

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
        "/add_order","/event_list", "/delete_ticket","/update_quantity_in_ticket", "/order","/show_order","/visit_event_list",
        "/translate","/show_information_about_event","/show_user_details","/user_list",
        "/delete_user","/delete_comment","/add_ticket", "/comment","/show_event_details","/add_comment","/show-event_list","/change_user_role"})
public class Controller extends HttpServlet {

    private static final Logger logger= LogManager.getLogger();

    private static final String COMMAND = "command";
    private static final String ERROR = "error";
    private static final String STATUS_CODE = "statusCode";
    private static final String RESPONSE = "response";

    @Override
    public void init() {
       ConnectionPool connectionPool= ConnectionPool.INSTANCE;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String page;
String commandName=request.getParameter(COMMAND);
        CommandMap commandMap = CommandMap.getInstance();
        try {
            if (commandName != null) {
              logger.info("Request. Parameter = " + commandName);
                Command command = commandMap.receiveCommand(commandName); //определение команды, пришедшей из jsp
                page = command.execute(request);  //вызов реализ-го метода execute  и передача пар-ров классу обработчику конкретн. команды

            } else if (request.getAttribute(COMMAND) != null) {
             logger.info("Request through filter. Attribute = " + request.getAttribute(COMMAND));
                Command command = commandMap.receiveCommand((String) request.getAttribute(COMMAND));
                page = command.execute(request);

            } else {
              logger.error("Command not received");
                request.setAttribute(ERROR, "Command not received");
                request.setAttribute(STATUS_CODE, 404);
                page = PathForJsp.ERROR.getUrl();
            }
        } catch (CommandException e) {
         logger.error(e.getMessage(), e);
            request.setAttribute(ERROR, e.getMessage());
            request.setAttribute(STATUS_CODE, 500);
            page =PathForJsp.ERROR.getUrl();
        }

        if (page != null) {     //м-д возвр-ет стр. ответа
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
        ConnectionPool.INSTANCE.destroyPool();
    }
}
