package by.epam.project.service;

import by.epam.project.entity.UserComment;
import by.epam.project.exception.ServiceException;

import java.time.LocalDate;
import java.util.List;

/**
 * Interface for {@link by.epam.project.service.impl.UserCommentServiceImpl}
 *
 * @author Shpakova A.
 */

public interface UserCommentService {

    List<UserComment> findComments(int userId, String ticketDate) throws ServiceException;

    void deleteCommentsForUser(int userId) throws ServiceException;

    void deleteCommentsForUserByDate(int userId, LocalDate selectedDate) throws ServiceException;

    void insertComment(UserComment userComment) throws ServiceException;

    void deleteComment(int commentId) throws ServiceException;

}
