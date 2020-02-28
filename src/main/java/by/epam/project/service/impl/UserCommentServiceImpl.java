package by.epam.project.service.impl;

import by.epam.project.dao.UserCommentDao;
import by.epam.project.dao.impl.UserCommentDaoImpl;
import by.epam.project.entity.UserComment;
import by.epam.project.exception.ConnectionPoolException;
import by.epam.project.exception.DaoException;
import by.epam.project.exception.ServiceException;
import by.epam.project.service.UserCommentService;

import java.time.LocalDate;
import java.util.List;

/**
 * Класс, реализующий UserCommentService
 *
 * @author Shpakova A.
 */

public class UserCommentServiceImpl implements UserCommentService {
    private UserCommentDao userCommentDao;

    public UserCommentServiceImpl() {                 //если нужно будет вызвать м-ды из др. классов
        this.userCommentDao = new UserCommentDaoImpl();
    }


    @Override
    public List<UserComment> findComments(int userId, String ticketDate) throws ServiceException {
        try {
            return userCommentDao.findComments(userId, ticketDate);
        } catch (DaoException  e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteCommentsForUser(int userId) throws ServiceException {
        try {
            userCommentDao.deleteCommentsForUser(userId);
        } catch (DaoException  e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteCommentsByCommentator(int commentatorId) throws ServiceException {
        try {
            userCommentDao.deleteCommentsByCommentator(commentatorId);
        } catch (DaoException  e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteCommentsForUserByDate(int userId, LocalDate selectedDate) throws ServiceException {
        try {
            userCommentDao.deleteCommentsForUserByDate(userId, selectedDate);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void insertComment(UserComment userComment) throws ServiceException {
        try {
            userCommentDao.insert(userComment);
        } catch (DaoException  e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteComment(int commentId) throws ServiceException {
        try {
            userCommentDao.delete(commentId);
        } catch (DaoException  e) {
            throw new ServiceException(e);
        }
    }
}
