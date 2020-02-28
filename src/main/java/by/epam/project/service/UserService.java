package by.epam.project.service;

import by.epam.project.entity.User;
import by.epam.project.entity.UserRole;
import by.epam.project.exception.DaoException;
import by.epam.project.exception.ServiceException;

import java.util.List;

/**
 * Слой для взаимдействия  с UserDao
 *
 * @author Shpakova A.
 */

public interface UserService {
    List<User> takeAllUsers() throws ServiceException;

    List<User> takeAllUsersWithLimit(int startIndex, int endIndex) throws ServiceException;

    List<User> findByLoginAndPass(String login, String password) throws ServiceException;

    List<User> findByEmailAndPass(String email, String password) throws ServiceException;

    void updateUserRole(int userId, String userRole) throws ServiceException;     //обновить

    boolean containsEmail(String email) throws ServiceException;

    boolean containsLogin(String login) throws ServiceException;

    UserRole takeUserRole(int userId) throws ServiceException;

    User findUserById(int userId) throws ServiceException;

    void insertUser(User user) throws ServiceException;

    void deleteUser(int userId) throws ServiceException;

    void update(User user) throws ServiceException;


}
