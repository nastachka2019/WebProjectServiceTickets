package by.epam.project.dao;


import by.epam.project.entity.UserComment;
import by.epam.project.exception.DaoException;

import java.time.LocalDate;
import java.util.List;

/**
 * Interface for actions with {@link UserComment} according DAO and database data
 *
 * @author Shpakova A.
 */

public interface UserCommentDao extends BasicDao<UserComment> {
    /**
     * Method: show comments of any event by date of ticket from user
     *
     * @return list of comments of any event
     */
    List<UserComment> findComments(int userId, String ticketDate) throws DaoException;

    /**
     * Method: delete comment from user by administrator
     */
    void deleteCommentsForUser(int userId) throws DaoException;

    /**
     * Method: delete comment by user
     */
    void deleteCommentsByCommentator(int commentatorId) throws DaoException;

    /**
     * Method: delete comment by user by date of comment
     */
    void deleteCommentsForUserByDate(int userId, LocalDate selectedDate) throws DaoException;

}

