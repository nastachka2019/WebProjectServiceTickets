package by.epam.project.dao;

import by.epam.project.entity.Ticket;
import by.epam.project.entity.User;
import by.epam.project.entity.UserRole;
import by.epam.project.exception.DaoException;

import java.util.List;

/**
 * Interface for actions with {@link Ticket} according DAO and database data
 *
 * @author Shpakova A.
 */

public interface UserDao extends BasicDao<User> {
    /**
     * Method: show all registered users
     *
     * @return list of users
     */
    List<User> takeAllUsers() throws DaoException;

    /**
     * Method: show all registered users from start index to end index
     */
    List<User> takeAllUsersWithLimit(int startIndex, int endIndex) throws DaoException;

    /**
     * Method: for user sign in account with password and login (user name)
     */
    List<User> findByLoginAndPass(String login, String password) throws DaoException;

    /**
     * Method: for user sign in account with password and email
     */
    List<User> findByEmailAndPass(String email, String password) throws DaoException;

    /**
     * Method: for updating type of user if administrator will change the type of user
     */
    void updateUserRole(int userId, String userRole) throws DaoException;      //обновить

    /**
     * Method: for checking new registering email if it belong to another user
     */
    boolean containsEmail(String email) throws DaoException;

    /**
     * Method: for checking new registering login if it belong to another user
     */
    boolean containsLogin(String login) throws DaoException;

    /**
     * Method: show to administrator all registered user with their user type
     */
    UserRole takeUserRole(int userId) throws DaoException;
}

