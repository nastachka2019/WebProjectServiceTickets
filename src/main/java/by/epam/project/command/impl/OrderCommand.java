package by.epam.project.command.impl;

import by.epam.project.command.Command;
import by.epam.project.command.PathForJsp;
import by.epam.project.entity.User;
import by.epam.project.exception.ServiceException;
import by.epam.project.service.TicketService;
import by.epam.project.service.impl.TicketServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

/**
 * Этот класс используется, чтобы показать даты заказа пользователю
 *
 * @author Shpakova A.
 */

public class OrderCommand implements Command {
    private final static String USER = "User";
    private final static String TICKET_DATES = "ticketDates";
    private final static String TICKET_DATE = "ticketDate";
    private final static String SHOW_ORDER = "show_order";
    private final static String SELECTED_DATE = "selected_date";
    private final static String NO_ORDER = "noOrder";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        User user = (User) request.getSession().getAttribute(USER);
        int userId = user.getUserId();

        TicketService ticketService=new TicketServiceImpl();
        Set<String> dates = null;
        try {
            dates = ticketService.findDatesByUserId(userId);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        if (!dates.isEmpty()) {
            request.setAttribute(TICKET_DATES, dates);

            if (request.getAttribute(SHOW_ORDER) != null && (boolean) request.getAttribute(SHOW_ORDER)) {
                request.setAttribute(SELECTED_DATE, request.getParameter(TICKET_DATE));
            }
        } else {
            request.setAttribute(NO_ORDER, "You have not added any  to your order");
        }

        return PathForJsp.ORDER.getUrl();
    }
}
