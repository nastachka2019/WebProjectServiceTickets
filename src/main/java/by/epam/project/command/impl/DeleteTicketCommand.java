package by.epam.project.command.impl;

import by.epam.project.command.Command;
import by.epam.project.entity.User;
import by.epam.project.exception.ServiceException;
import by.epam.project.service.TicketService;
import by.epam.project.service.UserCommentService;
import by.epam.project.service.impl.TicketServiceImpl;
import by.epam.project.service.impl.UserCommentServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

/**
 *  This class is used to remove a ticket from an order.
 *
 * @author Shpakova A.
 */

public class DeleteTicketCommand implements Command {
    private final static String USER = "User";
    private static final String TICKET_ID = "ticketId";
    private static final String TOTAL_ACTIVIES = "totalEvents";
    private static final String SELECTED_DATE = "selected_date";
    private static final String RESPONSE = "response";
    private static final String ORDER_PATH = "/order";

    @Override
    public String execute(HttpServletRequest request) {

        int ticketId = Integer.parseInt(request.getParameter(TICKET_ID));
        int totalEvents = Integer.parseInt(request.getParameter(TOTAL_ACTIVIES));
        TicketService ticketService=new TicketServiceImpl();
        try {
            ticketService.deleteTicket(ticketId);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        request.setAttribute(RESPONSE, true);
        if(totalEvents - 1 > 0){
            return request.getHeader("Referer");
        }else{
            User user = (User)request.getSession().getAttribute(USER);
            LocalDate selectedDate = LocalDate.parse(request.getParameter(SELECTED_DATE));

            UserCommentService userCommentService = new UserCommentServiceImpl();
            try {
                userCommentService.deleteCommentsForUserByDate(user.getUserId(), selectedDate);
            } catch (ServiceException e) {
                e.printStackTrace();
            }
            return request.getContextPath() + ORDER_PATH;
        }
    }
}
