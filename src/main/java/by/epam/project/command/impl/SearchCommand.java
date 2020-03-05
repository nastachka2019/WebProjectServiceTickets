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
 * Этот класс используется для поиска мероприятий
 *
 * @author Shpakova A.
 */

public class SearchCommand implements Command {
    private static final int NUMBER_ACTIVITIES_PER_PAGE = 9;
    private static final String NAME_OR_WORD_IN_NAME = "nameOrWordInName";
    private static final String SEARCH = "search";
    private static final String ERROR = "error";
    private static final String STATUS_CODE = "statusCode";
    private static final String COMMAND_VALUE = "commandValue";
    private static final String SEARCH_COMMAND = "search";
    private static final String INDEX_OF_PAGE = "indexOfPage";
    private static final String EVENT_LIST_SIZE = "eventListSize";

    private static final String REGEX_INDEX = "^[1-9]\\d{0,5}$";


    @Override
    public String execute(HttpServletRequest request) {
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

        String nameOrWordInName = request.getParameter(NAME_OR_WORD_IN_NAME);

        if (nameOrWordInName != null) {
            nameOrWordInName = nameOrWordInName.trim();
            boolean dataIsCorrect = new EventValidator().validateData(nameOrWordInName);

            if (!dataIsCorrect) {
                request.setAttribute(ERROR, "Length of search query is too big");
                request.setAttribute(STATUS_CODE, 404);
                return PathForJsp.ERROR.getUrl();

            } else {
                ActivityService eventService = new ActivityServiceImpl();

                List<Activity> events = null;
                if (nameOrWordInName.isEmpty()) {
                    try {
                        events = eventService.findEventByLimit(
                                (indexOfPage - 1) * NUMBER_ACTIVITIES_PER_PAGE,
                                indexOfPage * NUMBER_ACTIVITIES_PER_PAGE);
                        request.setAttribute(EVENT_LIST_SIZE, eventService.takeAllEvents().size());
                    } catch (ServiceException e) {
                        e.printStackTrace();
                    }

                } else {
                    try {
                        events = eventService.findByNameOrWordInNameWithLimit(nameOrWordInName,
                                (indexOfPage - 1) * NUMBER_ACTIVITIES_PER_PAGE,
                                indexOfPage * NUMBER_ACTIVITIES_PER_PAGE);

                        request.setAttribute(EVENT_LIST_SIZE, eventService.findByNameOrWordInName(nameOrWordInName).size());
                    } catch (ServiceException e) {
                        e.printStackTrace();
                    }
                }

                request.setAttribute(SEARCH, events);
                request.setAttribute(NAME_OR_WORD_IN_NAME, nameOrWordInName);
                request.setAttribute(COMMAND_VALUE, SEARCH_COMMAND);

            }
            return new EventListCommand().execute(request);

        } else {
            request.setAttribute(ERROR, "Error request");
            request.setAttribute(STATUS_CODE, 404);
            return PathForJsp.ERROR.getUrl();

        }
    }
}
