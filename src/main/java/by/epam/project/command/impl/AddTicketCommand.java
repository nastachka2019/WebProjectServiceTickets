package by.epam.project.command.impl;

import by.epam.project.command.Command;
import by.epam.project.command.PathForJsp;
import by.epam.project.entity.Activity;
import by.epam.project.entity.EventType;
import by.epam.project.entity.Ticket;
import by.epam.project.entity.User;
import by.epam.project.exception.ServiceException;
import by.epam.project.service.ActivityService;
import by.epam.project.service.EventTypeService;
import by.epam.project.service.TicketService;
import by.epam.project.service.impl.ActivityServiceImpl;
import by.epam.project.service.impl.EventTypeServiceImpl;
import by.epam.project.service.impl.TicketServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Этот класс используется для заказа билета на мероприятие
 *
 * @author Shpakova A.
 */

public class AddTicketCommand implements Command {
    private static final String USER = "User";
    private static final String ACTIVITY_ID = "eventId";
    private static final String TICKET_DATE = "ticketDate";
    private static final String EVENT_TYPE="eventType";
    private static final String QUANTITY = "quantity";
    private static final String ERROR = "error";
    private static final String STATUS_CODE = "statusCode";
    private static final String REGEX_QUANTITY = "^[1-9]\\d{0,2}$";
    private static final String RESPONSE = "response";
    private static final String OK = "ok";

    @Override
    public String execute(HttpServletRequest request) {

        User user = (User) request.getSession().getAttribute(USER);
        int eventId = Integer.parseInt(request.getParameter(ACTIVITY_ID));
        String ticketDate = request.getParameter(TICKET_DATE);
        String stringEventType = request.getParameter(EVENT_TYPE);

        Pattern pattern = Pattern.compile(REGEX_QUANTITY);
        Matcher matcher = pattern.matcher(request.getParameter(QUANTITY));

        if(matcher.matches()){
            int quantity = Integer.parseInt(request.getParameter(QUANTITY));

            ActivityService eventService=new ActivityServiceImpl();
            try {
                Activity event = eventService.findByEventId(eventId);
                TicketService ticketService= new TicketServiceImpl();
                EventTypeService eventTypeService=new EventTypeServiceImpl();

                int idEventType = eventTypeService.findIdByEventType(stringEventType);
                Ticket ticket=new Ticket.Builder()
                        .setUser(user)
                        .setEvent(event)
                        .setDate(LocalDate.parse(ticketDate))
                        .setEventType(new EventType.Builder()
                                .setEventTypeId(idEventType)
                                .setEventTypeValue(stringEventType)
                                .build())
                        .setQuantity(quantity)
                        .build();
                ticketService.insertTicket(ticket);

                request.getSession().setAttribute(OK, "Added successfully");

            } catch (ServiceException e) {
                request.setAttribute(ERROR, e.getMessage());
                request.setAttribute(STATUS_CODE, 500);
                return PathForJsp.ERROR.getUrl();
            }
        }else{
            request.setAttribute(ERROR, "Error quantity");
            request.setAttribute(STATUS_CODE, 400);
            return PathForJsp.ERROR.getUrl();
        }
        request.setAttribute(RESPONSE, true);
        return request.getHeader("Referer");
    }
}
