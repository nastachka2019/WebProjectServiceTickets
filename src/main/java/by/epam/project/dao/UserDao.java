package by.epam.project.dao;

import by.epam.project.entity.User;
import by.epam.project.entity.UserRole;
import by.epam.project.exception.ConnectionPoolException;
import by.epam.project.exception.DaoException;

import java.util.List;

/**
 * Класс представляет собой слой для взаимодействия с базой данных User
 *
 * @author Shpakova A.
 */

public interface UserDao extends BasicDao<User> {   //слой для взаимодейств. с бд

    List<User> takeAllUsers() throws DaoException, ConnectionPoolException;

    List<User> takeAllUsersWithLimit(int startIndex, int endIndex) throws DaoException, ConnectionPoolException;

    List<User> findByLoginAndPass(String login, String password) throws DaoException, ConnectionPoolException;

    List<User> findByEmailAndPass(String email, String password) throws DaoException, ConnectionPoolException;

    void updateUserRole(int userId, String userRole) throws DaoException, ConnectionPoolException;      //обновить

    boolean containsEmail(String email) throws DaoException, ConnectionPoolException;

    boolean containsLogin(String login) throws DaoException, ConnectionPoolException;

    UserRole takeUserRole(int userId) throws DaoException, ConnectionPoolException;
}

