package by.epam.project.dao;

import by.epam.project.entity.User;
import by.epam.project.entity.UserRole;
import by.epam.project.exception.DaoException;

import java.util.List;

/**
 * Класс представляет собой слой для взаимодействия с базой данных User
 *
 * @author Shpakova A.
 */

public interface UserDao extends BasicDao<User> {   //слой для взаимодейств. с бд

    List<User> takeAllUsers() throws DaoException;

    List<User> takeAllUsersWithLimit(int startIndex, int endIndex) throws DaoException;

    List<User> findByLoginAndPass(String login, String password) throws DaoException;

    List<User> findByEmailAndPass(String email, String password) throws DaoException;

    void updateUserRole(int userId, String userRole) throws DaoException;      //обновить

    boolean containsEmail(String email) throws DaoException;

    boolean containsLogin(String login) throws DaoException;

    UserRole takeUserRole(int userId) throws DaoException;
}

