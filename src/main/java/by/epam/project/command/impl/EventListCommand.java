package by.epam.project.command.impl;

import by.epam.project.command.Command;
import by.epam.project.command.PathForJsp;
import by.epam.project.entity.Activity;
import by.epam.project.exception.ServiceException;
import by.epam.project.service.ActivityService;
import by.epam.project.service.impl.ActivityServiceImpl;
import by.epam.project.validator.EventValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *  This class is used to show all events.
 *
 * @author Shpakova A.
 */

public class EventListCommand implements Command {
    private static final String SEARCH_ERROR = "searchError";
    private static final String SEARCH = "search";
    private static final String EVENTS = "events";
    private static final int NUMBER_EVENTS_PER_PAGE = 8;
    private static final String ACTIVITY_ADDRESS = "eventAddress";
    private static final String ACTIVITY_NAME = "eventName";
    private static final String EVENTS_PER_PAGE = "eventsPerPage";
    private static final String INDEX_OF_PAGE = "indexOfPage";
    private static final String EVENT_LIST_SIZE = "eventListSize";
    private static final String EVENT_LIST_COMMAND = "event_list";
    private static final String COMMAND_VALUE = "commandValue";
    private static final String NAME_OR_WORD_IN_NAME = "nameOrWordInName";
    private static final String MAX_PRICE = "maxPrice";
    private static final String MIN_PRICE = "minPrice";
    private static final String REGEX_INDEX = "^[1-9]\\d{0,5}$";
    private static final String ERROR = "error";
    private static final String STATUS_CODE = "statusCode";

    @Override
    public String execute(HttpServletRequest request) {

        int indexOfPage;
        if (request.getParameter(INDEX_OF_PAGE) != null) {
            Pattern pattern = Pattern.compile(REGEX_INDEX);
            Matcher matcher = pattern.matcher(request.getParameter(INDEX_OF_PAGE));

            if(matcher.matches()){
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

        List<Activity> events = null;  //?
        if (request.getAttribute(SEARCH) != null) {     //if we search events by header, SEARCH - found products
            events = (List<Activity>) request.getAttribute(SEARCH);
            if (!events.isEmpty()) {

                attributeSettingWithDataFromDb(request, eventService);

            } else {
                request.setAttribute(NAME_OR_WORD_IN_NAME, request.getParameter(NAME_OR_WORD_IN_NAME));

                attributeSettingWithDataFromDb(request, eventService);

            }
        } else {
            if (request.getParameter(NAME_OR_WORD_IN_NAME) != null
                    && request.getParameter(ACTIVITY_ADDRESS) != null
                    && request.getParameter(ACTIVITY_NAME) != null
                    && request.getParameter(MIN_PRICE) != null
                    && request.getParameter(MAX_PRICE) != null) {
                String nameOrWordInName = request.getParameter(NAME_OR_WORD_IN_NAME);
                String strAddress = request.getParameter(ACTIVITY_ADDRESS);
               String strName = request.getParameter(ACTIVITY_NAME);
                String strMaxPrice = request.getParameter(MAX_PRICE);
                String strMinPrice = request.getParameter(MIN_PRICE);
                EventValidator eventValidator = new EventValidator();

                if (eventValidator.validateData(nameOrWordInName, strMaxPrice, strMinPrice)) {
                    int minPrice = 0;

                    try {
                        minPrice = eventService.checkPrice(Integer.parseInt(strMinPrice));


                        int maxPrice = eventService.checkPrice(Integer.parseInt(strMaxPrice));
                        String eventName = strName;
                       String eventAddress = strAddress;


                        if (minPrice > maxPrice) {
                            minPrice = maxPrice;
                        }

                        events = eventService.findEventsByFilterWithLimit(nameOrWordInName, minPrice, maxPrice, (indexOfPage - 1) * NUMBER_EVENTS_PER_PAGE,
                                indexOfPage * NUMBER_EVENTS_PER_PAGE);

                        request.setAttribute(COMMAND_VALUE, EVENT_LIST_COMMAND);
                        request.setAttribute(EVENT_LIST_SIZE, eventService.findEventsByFilter(nameOrWordInName, maxPrice, minPrice).size());
                        request.setAttribute(NAME_OR_WORD_IN_NAME, nameOrWordInName);
                        request.setAttribute(MIN_PRICE, minPrice);
                        request.setAttribute(MAX_PRICE, maxPrice);
                       request.setAttribute(ACTIVITY_NAME, eventName);
                        request.setAttribute(ACTIVITY_ADDRESS, eventAddress);
                    } catch (ServiceException e) {
                        e.printStackTrace();
                    }

                } else {
                    request.setAttribute(ERROR, "Error request");
                    request.setAttribute(STATUS_CODE, 404);
                    return PathForJsp.ERROR.getUrl();
                }

            } else {
                request.setAttribute(ERROR, "Error request");
                request.setAttribute(STATUS_CODE, 404);
                return PathForJsp.ERROR.getUrl();
            }
        }

        if (events.isEmpty()) {
            request.setAttribute(SEARCH_ERROR, "Events not found");
        } else {
            request.setAttribute(EVENTS, events);
        }

        request.setAttribute(INDEX_OF_PAGE, indexOfPage);
        request.setAttribute(EVENTS_PER_PAGE, NUMBER_EVENTS_PER_PAGE);

        return PathForJsp.EVENT_LIST.getUrl();
    }

    private void attributeSettingWithDataFromDb(HttpServletRequest request, ActivityService eventService) {
        Activity activity = new Activity();
        try {
            request.setAttribute(MIN_PRICE, eventService.findMinPrice());

            request.setAttribute(MAX_PRICE, eventService.findMaxPrice());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
       request.setAttribute(ACTIVITY_NAME, activity.getName());
        request.setAttribute(ACTIVITY_ADDRESS, activity.getAddress());
    }
}

