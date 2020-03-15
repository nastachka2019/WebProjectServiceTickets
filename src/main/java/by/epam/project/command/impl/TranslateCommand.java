package by.epam.project.command.impl;

import by.epam.project.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * This class is used for translating of pages.
 *
 * @author Shpakova A.
 */

public class TranslateCommand implements Command {
    private static final Logger logger= LogManager.getLogger();
    private static final String LANGUAGE = "language";
    private static final String LOCALE = "locale";
    private static final String RESPONSE = "response";
    @Override
    public String execute(HttpServletRequest request) {

        String stringLanguage = request.getParameter(LANGUAGE);
        Locale locale;
        switch (stringLanguage) {
            case "Русский":
                locale = new Locale("ru");
                break;
            case "English":
                locale = new Locale("en");
                break;
                default:
                logger.error("There is no " + stringLanguage + " language. English language will be used instead.");
                locale = new Locale("en");
                break;
        }

        request.getSession().setAttribute(LOCALE, locale);
        request.setAttribute(RESPONSE, true);
        return request.getHeader("Referer");
    }
}
