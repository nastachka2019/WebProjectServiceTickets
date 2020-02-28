package by.epam.project.command.impl;

import by.epam.project.command.Command;
import by.epam.project.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Этот класс используется для выхода из кабинета пользователя
 *
 * @author Shpakova A.
 */

public class SignOutCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final String USER = "User";
    private static final String RESPONSE = "response";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        logger.info("User " + ((User)request.getSession().getAttribute(USER)).getLogin() + " signed out.");
        request.getSession().setAttribute(USER, null);
        request.setAttribute(RESPONSE, true);
        return request.getContextPath();
    }
}
