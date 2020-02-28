package by.epam.project.dao;

import by.epam.project.entity.UserComment;
import by.epam.project.exception.ConnectionPoolException;
import by.epam.project.exception.DaoException;

import java.time.LocalDate;
import java.util.List;

/**
 * Класс представляет собой слой для взаимодействия с базой данных UserComment
 *
 * @author Shpakova A.
 */

public interface UserCommentDao extends BasicDao<UserComment> {
    List<UserComment> findComments(int userId, String ticketDate) throws DaoException;

    void deleteCommentsForUser(int userId) throws DaoException;

    void deleteCommentsByCommentator(int commentatorId) throws DaoException;

    void deleteCommentsForUserByDate(int userId, LocalDate selectedDate) throws DaoException;

}

