package by.epam.project.service.impl;

import by.epam.project.dao.ActivityDao;

import by.epam.project.dao.impl.ActivityDaoImpl;
import by.epam.project.entity.Activity;
import by.epam.project.exception.ConnectionPoolException;
import by.epam.project.exception.DaoException;
import by.epam.project.exception.ServiceException;
import by.epam.project.service.ActivityService;

import java.util.List;

/**
 * Класс, реализующий ActivityService
 *
 * @author Shpakova A.
 */

public class ActivityServiceImpl implements ActivityService {
    private ActivityDao activityDao;

    public ActivityServiceImpl() {
        activityDao = new ActivityDaoImpl();
    }


    @Override
    public List<Activity> takeAllEvents() throws ServiceException {
        try {
            return activityDao.takeAllEvents();
        } catch (DaoException | ConnectionPoolException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Activity> findByNameOrWordInNameWithLimit(String nameOrWordInName, int startIndex, int endIndex) throws ServiceException {
        try {
            return activityDao.findByNameOrWordInNameWithLimit(nameOrWordInName, startIndex, endIndex);
        } catch (DaoException | ConnectionPoolException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Activity> findByNameOrWordInName(String nameOrWordInName) throws ServiceException {
        try {
            return activityDao.findByNameOrWordInName(nameOrWordInName);
        } catch (DaoException | ConnectionPoolException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Activity findByEventId(int eventId) throws ServiceException {
        try {
            return activityDao.findById(eventId);
        } catch (DaoException | ConnectionPoolException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Activity> findEventByLimit(int startIndex, int endIndex) throws ServiceException {
        try {
            return activityDao.findEventByLimit(startIndex, endIndex);
        } catch (DaoException | ConnectionPoolException e) {
            throw new ServiceException(e);
        }
    }
}
