package by.epam.project.command.impl;

import by.epam.project.command.Command;
import by.epam.project.command.PathForJsp;
import by.epam.project.exception.ServiceException;
import by.epam.project.service.TicketService;
import by.epam.project.service.impl.TicketServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Этот класс используется для заказа билетов
 *
 * @author Shpakova A.
 */

public class UpdateQuantityInOrderCommand implements Command {
    private static final String QUANTITY = "quantity";
    private static final String TICKET_ID = "ticketId";
    private static final String RESPONSE = "response";
    private static final String REGEX_QUANTITY = "^[1-9]\\d{0,2}$";
    private static final String ERROR = "error";
    private static final String STATUS_CODE = "statusCode";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        Pattern pattern = Pattern.compile(REGEX_QUANTITY);
        Matcher matcher = pattern.matcher(request.getParameter(QUANTITY));

        if(matcher.matches()){
            int quantity = Integer.parseInt(request.getParameter(QUANTITY));
            int ticketId= Integer.parseInt(request.getParameter(TICKET_ID));
            TicketService ticketService = new TicketServiceImpl();
            try{
            ticketService.updateQuantity(ticketId, quantity);
            } catch (ServiceException e) {
                e.printStackTrace();
            }
            request.setAttribute(RESPONSE, true);
            return request.getHeader("Referer");

        } else {
            request.setAttribute(ERROR, "Error request");
            request.setAttribute(STATUS_CODE, 404);
            return PathForJsp.ERROR.getUrl();
        }
    }
}
