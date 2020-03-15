package by.epam.project.command.impl;

import by.epam.project.command.Command;
import by.epam.project.command.PathForJsp;


import javax.servlet.http.HttpServletRequest;


/**
 *  This class is used to view pages after login.
 *
 * @author Shpakova A.
 */

public class VisitSignInCommand implements Command {

    private static final String ERROR = "error";

    @Override
    public String execute(HttpServletRequest request)  {
        if (request.getSession().getAttribute(ERROR) != null) {
            request.setAttribute(ERROR, request.getSession().getAttribute(ERROR));
            request.getSession().setAttribute(ERROR, null);
        }
        return PathForJsp.SIGN_IN.getUrl();
    }
}
