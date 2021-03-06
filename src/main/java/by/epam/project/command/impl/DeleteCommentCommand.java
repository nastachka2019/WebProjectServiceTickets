package by.epam.project.command.impl;

import by.epam.project.command.Command;
import by.epam.project.exception.ServiceException;
import by.epam.project.service.UserCommentService;
import by.epam.project.service.impl.UserCommentServiceImpl;

import javax.servlet.http.HttpServletRequest;


/**
 *  This class is used to delete a comment on an event.
 *
 * @author Shpakova A.
 */

public class DeleteCommentCommand implements Command {
    private static final String COMMENT_ID = "commentId";
    private static final String RESPONSE = "response";

    @Override
    public String execute(HttpServletRequest request)  {

        int commentId = Integer.parseInt(request.getParameter(COMMENT_ID));

        UserCommentService userCommentService = new UserCommentServiceImpl();
        try {
            userCommentService.deleteComment(commentId);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        request.setAttribute(RESPONSE, true);
        return request.getHeader("Referer");
    }
}
