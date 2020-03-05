package by.epam.project.command;

import javax.servlet.http.HttpServletRequest;

/**
 * Этот интерфес используется для обработки запроса и ответа
 *
 * @author Shpakova A.
 */

public interface Command {
    /**
     * @param request это входящий запрос
     */

    String execute(HttpServletRequest request) ;

}

