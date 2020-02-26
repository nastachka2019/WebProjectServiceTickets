package by.epam.project.service.impl;

import by.epam.project.dao.UserDao;
import by.epam.project.dao.impl.UserDaoImpl;
import by.epam.project.entity.User;
import by.epam.project.entity.UserRole;
import by.epam.project.exception.ConnectionPoolException;
import by.epam.project.exception.DaoException;
import by.epam.project.exception.ServiceException;
import by.epam.project.service.UserService;

import java.util.List;

/**
 * Класс, реализующий UserService
 *
 * @author Shpakova A.
 */

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserServiceImpl() {                 //если нужно будет вызвать м-ды из др. классов
        this.userDao = new UserDaoImpl();
    }


    @Override
    public List<User> takeAllUsers() throws ServiceException {
        try {
            return userDao.takeAllUsers();
        } catch (DaoException | ConnectionPoolException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> takeAllUsersWithLimit(int startIndex, int endIndex) throws ServiceException {
        try {
            return userDao.takeAllUsersWithLimit(startIndex, endIndex);
        } catch (DaoException | ConnectionPoolException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> findByLoginAndPass(String login, String password) throws ServiceException {
        try {
            return userDao.findByLoginAndPass(login, password);
        } catch (DaoException | ConnectionPoolException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> findByEmailAndPass(String email, String password) throws ServiceException {
        try {
            return userDao.findByEmailAndPass(email, password);
        } catch (DaoException | ConnectionPoolException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateUserRole(int userId, String userRole) throws ServiceException {
        try {
            userDao.updateUserRole(userId, userRole);
        } catch (DaoException | ConnectionPoolException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean containsEmail(String email) throws ServiceException {
        try {
            return userDao.containsEmail(email);
        } catch (DaoException | ConnectionPoolException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean containsLogin(String login) throws ServiceException {
        try {
            return userDao.containsLogin(login);
        } catch (DaoException | ConnectionPoolException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public UserRole takeUserRole(int userId) throws ServiceException {
        try {
            return userDao.takeUserRole(userId);
        } catch (DaoException | ConnectionPoolException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User findUserById(int userId) throws ServiceException {
        try {
            return userDao.findById(userId);
        } catch (DaoException | ConnectionPoolException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void insertUser(User user) throws ServiceException {
        try {
            userDao.insert(user);
        } catch (DaoException | ConnectionPoolException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteUser(int userId) throws ServiceException {
        try {
            userDao.delete(userId);
        } catch (DaoException | ConnectionPoolException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void update(User user) {
        try {
            userDao.update(user);
        } catch (DaoException | ConnectionPoolException e) {
            e.printStackTrace();
        }
    }
}

