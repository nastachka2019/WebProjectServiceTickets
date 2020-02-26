package by.epam.project.command;

import by.epam.project.exception.CommandException;
import by.epam.project.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Этот интерфес используется для обработки запроса и ответа
 *
 * @author Shpakova A.
 */

public interface Command {

    String execute(HttpServletRequest request) throws CommandException, ServiceException;

}

