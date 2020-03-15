package by.epam.project.command.impl;

import by.epam.project.command.Command;
import by.epam.project.command.PathForJsp;
import by.epam.project.entity.Activity;
import by.epam.project.exception.ServiceException;
import by.epam.project.service.ActivityService;
import by.epam.project.service.impl.ActivityServiceImpl;

import javax.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *  This class is used to visit the page with all events.
 *
 * @author Shpakova A.
 */

public class VisitEventListCommand implements Command {
    private static final String EVENTS = "events";
    private static final int NUMBER_EVENTS_PER_PAGE = 8;
    private static final String EVENTS_PER_PAGE = "eventsPerPage";
    private static final String INDEX_OF_PAGE = "indexOfPage";
    private static final String EVENT_LIST_SIZE = "eventListSize";
    private static final String VISIT_EVENT_LIST_COMMAND = "visit_event_list";
    private static final String COMMAND_VALUE = "commandValue";
    private static final String ACTIVITY_ADDRESS = "eventAddress";
    private static final String ACTIVITY_NAME = "eventName";
    private static final String REGEX_INDEX = "^[1-9]\\d{0,5}$";
    private static final String ERROR = "error";
    private static final String STATUS_CODE = "statusCode";
    private static final String MIN_PRICE = "minPrice";
    private static final String MAX_PRICE = "maxPrice";

    @Override
    public String execute(HttpServletRequest request) {

        if (request.getSession().getAttribute(ERROR) != null) {
            request.setAttribute(ERROR, request.getSession().getAttribute(ERROR));
            request.getSession().setAttribute(ERROR, null);
        }

        int indexOfPage;
        if (request.getParameter(INDEX_OF_PAGE) != null) {
            Pattern pattern = Pattern.compile(REGEX_INDEX);
            Matcher matcher = pattern.matcher(request.getParameter(INDEX_OF_PAGE));

            if (matcher.matches()) {
                indexOfPage = Integer.parseInt(request.getParameter(INDEX_OF_PAGE));
            } else {
                request.setAttribute(ERROR, "Error request");
                request.setAttribute(STATUS_CODE, 404);
                return PathForJsp.ERROR.getUrl();
            }
        } else {
            indexOfPage = 1;
        }

        ActivityService eventService = new ActivityServiceImpl();

        Activity activity = new Activity();
        List<Activity> events = null;
        try {
            events = eventService.findEventByLimit(
                    (indexOfPage - 1) * NUMBER_EVENTS_PER_PAGE,
                    indexOfPage * NUMBER_EVENTS_PER_PAGE
            );
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        if (!events.isEmpty()) {
            request.setAttribute(ACTIVITY_ADDRESS, activity.getAddress());
            request.setAttribute(ACTIVITY_NAME, activity.getName());
            try {
                request.setAttribute(MIN_PRICE, eventService.findMinPrice());

                request.setAttribute(MAX_PRICE, eventService.findMaxPrice());
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }

        request.setAttribute(EVENTS, events);
        request.setAttribute(INDEX_OF_PAGE, indexOfPage);
        try {
            request.setAttribute(EVENT_LIST_SIZE, eventService.takeAllEvents().size());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        request.setAttribute(COMMAND_VALUE, VISIT_EVENT_LIST_COMMAND);
        request.setAttribute(EVENTS_PER_PAGE, NUMBER_EVENTS_PER_PAGE);

        return PathForJsp.EVENT_LIST.getUrl();
    }
}
