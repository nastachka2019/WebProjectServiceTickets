package by.epam.project.command;

import javax.servlet.http.HttpServletRequest;

/**
 * This interface is used to process the request and response.
 *
 * @author Shpakova A.
 */

public interface Command {
    /**
     * @param request is an incoming request.
     */

    String execute(HttpServletRequest request) ;

}

