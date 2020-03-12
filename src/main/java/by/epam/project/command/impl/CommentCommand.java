package by.epam.project.command.impl;

import by.epam.project.command.Command;
import by.epam.project.command.PathForJsp;
import by.epam.project.entity.User;
import by.epam.project.entity.UserComment;
import by.epam.project.exception.ServiceException;
import by.epam.project.service.UserCommentService;
import by.epam.project.service.impl.UserCommentServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *  This class is used that the user can leave comments about events.
 *
 * @author Shpakova A.
 */

public class CommentCommand implements Command {
    private static final String USER = "User";
    private static final String TICKET_DATE = "ticketDate";
    private static final String USER_ID_FOR_ADMIN = "userIdForAdmin";
    private static final String COMMENT = "comment";
    private static final String RESPONSE = "response";
    private static final String ERROR = "error";
    private static final String STATUS_CODE = "statusCode";
    private static final String REGEX_COMMENT = "^.{1,200}$";

    @Override
    public String execute(HttpServletRequest request) {

        User user = (User) request.getSession().getAttribute(USER);
        String selectedDate = request.getParameter(TICKET_DATE);
        String comment = request.getParameter(COMMENT);

        Pattern pattern = Pattern.compile(REGEX_COMMENT);
        Matcher matcher = pattern.matcher(comment);

        if(matcher.matches()) {
            int userId;
            if (!request.getParameter(USER_ID_FOR_ADMIN).isEmpty()) {
                userId = Integer.parseInt(request.getParameter(USER_ID_FOR_ADMIN));
            } else {
                userId = user.getUserId();
            }

            UserCommentService userCommentService = new UserCommentServiceImpl();
            try {
                userCommentService.insertComment(new UserComment.Builder()
                        .setTicketDate(LocalDate.parse(selectedDate))
                        .setUserId(userId)
                        .setCommentator(new User.Builder().setUserId(user.getUserId()).build())
                        .setComment(comment.trim())
                        .build());
            } catch (ServiceException e) {
                e.printStackTrace();
            }

            request.setAttribute(RESPONSE, true);
            return request.getHeader("Referer");
        } else {
            request.setAttribute(ERROR, "Error request");
            request.setAttribute(STATUS_CODE, 404);
            return PathForJsp.ERROR.getUrl();
        }
    }
}
