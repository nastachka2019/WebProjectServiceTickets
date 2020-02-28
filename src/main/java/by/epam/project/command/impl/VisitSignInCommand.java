package by.epam.project.command.impl;

import by.epam.project.command.Command;
import by.epam.project.command.PathForJsp;
import by.epam.project.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Этот класс используется для посещения страницы после входа с параметрами по умолчанию
 *
 * @author Shpakova A.
 */

public class VisitSignInCommand implements Command {

    private static final String ERROR = "error";

    @Override
    public String execute(HttpServletRequest request,  HttpServletResponse response)  {
        if (request.getSession().getAttribute(ERROR) != null) {
            request.setAttribute(ERROR, request.getSession().getAttribute(ERROR));
            request.getSession().setAttribute(ERROR, null);
        }
        return PathForJsp.SIGN_IN.getUrl();
    }
}
