package by.epam.project.command.impl;

import by.epam.project.command.Command;
import by.epam.project.command.PathForJsp;


import javax.servlet.http.HttpServletRequest;


/**
 *  This class is used to visit the registration page with parameters.
 *
 * @author Shpakova A.
 */

public class VisitRegistrationCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return PathForJsp.REGISTRATION.getUrl();
    }
}
