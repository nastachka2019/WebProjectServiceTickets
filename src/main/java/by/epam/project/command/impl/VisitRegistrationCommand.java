package by.epam.project.command.impl;

import by.epam.project.command.Command;
import by.epam.project.command.PathForJsp;
import by.epam.project.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Этот класс используется для посещения страницы регистрации с параметрами по умолчанию
 *
 * @author Shpakova A.
 */

public class VisitRegistrationCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return PathForJsp.REGISTRATION.getUrl();
    }
}
