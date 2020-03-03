package by.epam.project.command.impl;

import by.epam.project.command.Command;
import by.epam.project.command.PathForJsp;
import by.epam.project.entity.Ticket;
import by.epam.project.entity.User;
import by.epam.project.entity.UserComment;
import by.epam.project.exception.CommandException;
import by.epam.project.exception.ServiceException;
import by.epam.project.service.TicketService;
import by.epam.project.service.UserCommentService;
import by.epam.project.service.impl.TicketServiceImpl;
import by.epam.project.service.impl.UserCommentServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;

/**
 * Этот класс используется, чтобы показать детали заказа пользователю
 *
 * @author Shpakova A.
 */

public class ShowOrderCommand implements Command {
    private static final String USER = "User";
    private static final String TICKET_DATE = "ticketDate";
    private static final String EXHIBITION = "exhibition";
    private static final String CONCERT = "concert";
    private static final String THEATRE = "theatre";
    private static final String OPERA = "opera";
    private static final String BALLET = "ballet";
    private static final String CINEMA = "cinema";
    private static final String SPORT = "sport";
//    private static final String EXHIBITION_ORDER = "football";
//    private static final String CONCERT_ORDER = "concertOrder";
//    private static final String THEATRE_ORDER = "theatreOrder";
//    private static final String OPERA_ORDER = "operaOrder";
//    private static final String BALLET_ORDER = "balletOrder";
//    private static final String CINEMA_ORDER = "cinemaOrder";
//    private static final String SPORT_ORDER = "sportOrder";
    private static final String ORDER="order";
    private static final String USER_COMMENT_LIST = "userCommentList";
    private static final String ERROR = "error";
    private static final String STATUS_CODE = "statusCode";
    private static final String SHOW_ORDER = "show_order";
    private static final String TOTAL_ACTIVITIES = "totalEvents";
    private static final String TOTAL_PRICE = "totalPrice";

    @Override
    public String execute(HttpServletRequest request) {

        if (request.getParameter(TICKET_DATE) != null) {
            String ticketDate = request.getParameter(TICKET_DATE);
            if (ticketDate == null) {
                ticketDate = (String) request.getAttribute(TICKET_DATE);
            }

            User user = (User) request.getSession().getAttribute(USER);
            int userId = user.getUserId();
try{
            TicketService ticketService = new TicketServiceImpl();
            List<Ticket> concertOrder = ticketService.findTicketByUserIdAndTicketDateAndEventType(userId, ticketDate, CONCERT);
            List<Ticket> exhibitionOrder = ticketService.findTicketByUserIdAndTicketDateAndEventType(userId, ticketDate, EXHIBITION);
            List<Ticket> theatreOrder = ticketService.findTicketByUserIdAndTicketDateAndEventType(userId, ticketDate, THEATRE);
            List<Ticket> operaOrder = ticketService.findTicketByUserIdAndTicketDateAndEventType(userId, ticketDate, OPERA);
            List<Ticket> balletOrder = ticketService.findTicketByUserIdAndTicketDateAndEventType(userId, ticketDate, BALLET);
            List<Ticket> cinemaOrder = ticketService.findTicketByUserIdAndTicketDateAndEventType(userId, ticketDate, CINEMA);
            List<Ticket> sportOrder = ticketService.findTicketByUserIdAndTicketDateAndEventType(userId, ticketDate, SPORT);

            if (!concertOrder.isEmpty()) {
                request.setAttribute(CONCERT_ORDER, concertOrder);
            }
            if (!exhibitionOrder.isEmpty()) {
                request.setAttribute(EXHIBITION_ORDER, exhibitionOrder);
            }
            if (!theatreOrder.isEmpty()) {
                request.setAttribute(THEATRE_ORDER, theatreOrder);
            }
            if (!operaOrder.isEmpty()) {
                request.setAttribute(OPERA_ORDER, operaOrder);
            }
            if (!balletOrder.isEmpty()) {
                request.setAttribute(BALLET_ORDER, balletOrder);
            }
            if (!cinemaOrder.isEmpty()) {
                request.setAttribute(CINEMA_ORDER, cinemaOrder);
            }
            if (!sportOrder.isEmpty()) {
                request.setAttribute(SPORT_ORDER, sportOrder);
            }

            int totalPrice = 0;
            try {
                totalPrice = ticketService.totalPriceByUserIdAndTicketDate(userId,ticketDate);
            } catch (ServiceException e) {
                e.printStackTrace();
            }

            UserCommentService userCommentService = new UserCommentServiceImpl();
            List<UserComment> userCommentList = null;

                userCommentList = userCommentService.findComments(userId, ticketDate);

            Collections.reverse(userCommentList);

            request.setAttribute(USER_COMMENT_LIST, userCommentList);
            request.setAttribute(SHOW_ORDER, true);
            request.setAttribute(TICKET_DATE, ticketDate);
            request.setAttribute(TOTAL_PRICE, totalPrice);
            request.setAttribute(TOTAL_ACTIVITIES, concertOrder.size() + operaOrder.size() + sportOrder.size() + theatreOrder.size() + cinemaOrder.size() + balletOrder.size() + exhibitionOrder.size());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
            return new OrderCommand().execute(request);
        } else {
            request.setAttribute(ERROR, "Error request");
            request.setAttribute(STATUS_CODE, 404);
            return PathForJsp.ERROR.getUrl();
        }
    }
}
