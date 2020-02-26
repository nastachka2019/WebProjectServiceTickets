package by.epam.project.service;

import by.epam.project.entity.UserComment;
import by.epam.project.exception.DaoException;
import by.epam.project.exception.ServiceException;

import java.time.LocalDate;
import java.util.List;
/**
 * Слой для взаимдействия  с UserCommentDao
 *
 * @author Shpakova A.
 */

public interface UserCommentService {
    List<UserComment> findComments(int userId, String ticketDate) throws ServiceException;

    void deleteCommentsForUser(int userId) throws ServiceException;

    void deleteCommentsByCommentator(int commentatorId) throws ServiceException;

    void deleteCommentsForUserByDate(int userId, LocalDate selectedDate) throws ServiceException;

    void insertComment(UserComment userComment) throws ServiceException;

    void deleteComment(int commentId) throws ServiceException;
}
