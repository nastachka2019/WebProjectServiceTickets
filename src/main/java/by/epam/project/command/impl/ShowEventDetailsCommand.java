package by.epam.project.command.impl;

import by.epam.project.command.Command;
import by.epam.project.command.PathForJsp;
import by.epam.project.entity.Activity;
import by.epam.project.exception.ServiceException;
import by.epam.project.service.ActivityService;
import by.epam.project.service.impl.ActivityServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Этот класс используется, чтобы показать детали мероприятия
 *
 * @author Shpakova A.
 */

public class ShowEventDetailsCommand implements Command {
    private static final int startQuantity = 1;
    private static final String EVENT_ID = "eventId";
    private static final String ERROR = "error";
    private static final String STATUS_CODE = "statusCode";
    private static final String EVENT = "event";
    private static final String QUANTITY = "quantity";
    private static final String OK = "ok";
    private static final String REGEX_ID = "^[1-9]\\d*$";

    @Override
    public String execute(HttpServletRequest request,HttpServletResponse response)  {

        if (request.getSession().getAttribute(OK) != null) {
            request.setAttribute(OK, request.getSession().getAttribute(OK));
            request.getSession().setAttribute(OK, null);
        }

        String page;
        if (request.getParameter(EVENT_ID) != null) {

            Pattern pattern = Pattern.compile(REGEX_ID);
            Matcher matcher = pattern.matcher(request.getParameter(EVENT_ID));

            if (matcher.matches()) {
                int eventId = Integer.parseInt(request.getParameter(EVENT_ID));
                ActivityService activityService=new ActivityServiceImpl();
                Activity activity= null;
                try {
                    activity = activityService.findByEventId(eventId);
                } catch (ServiceException e) {
                    e.printStackTrace();
                }


                if (activity == null) {
                    request.setAttribute(ERROR, "Product with such id not found");
                    request.setAttribute(STATUS_CODE, 404);
                    page = PathForJsp.ERROR.getUrl();
                } else {
                    request.setAttribute(EVENT, activity);
                    request.setAttribute(QUANTITY, startQuantity);

                    page = PathForJsp.EVENT.getUrl();
                }

            } else {
                request.setAttribute(ERROR, "Error request");
                request.setAttribute(STATUS_CODE, 404);
                page = PathForJsp.ERROR.getUrl();
            }
        } else {
            request.setAttribute(ERROR, "Error request");
            request.setAttribute(STATUS_CODE, 404);
            page = PathForJsp.ERROR.getUrl();
        }

        return page;
    }
}