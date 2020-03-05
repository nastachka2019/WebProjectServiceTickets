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
        } catch (DaoException e ) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Activity> findByNameOrWordInNameWithLimit(String nameOrWordInName, int startIndex, int endIndex) throws ServiceException {
        try {
            return activityDao.findByNameOrWordInNameWithLimit(nameOrWordInName, startIndex, endIndex);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Activity> findByNameOrWordInName(String nameOrWordInName) throws ServiceException {
        try {
            return activityDao.findByNameOrWordInName(nameOrWordInName);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Activity findByEventId(int eventId) throws ServiceException {
        try {
            return activityDao.findById(eventId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Activity> findEventByLimit(int startIndex, int endIndex) throws ServiceException {
        try {
            return activityDao.findEventByLimit(startIndex, endIndex);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Activity> findEventsByFilterWithLimit(String nameOrWordInName, int minPrice, int maxPrice, int startIndex, int endIndex) throws ServiceException {
        try {
            if (nameOrWordInName.isEmpty()) {
                return activityDao.findEventsByFilterWithoutSearchParamWithLimit(minPrice, maxPrice, startIndex, endIndex);
            } else {
                return activityDao.findEventsByFilterWithLimit(nameOrWordInName,
                        minPrice, maxPrice, startIndex, endIndex);
            }
        } catch (DaoException  e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Activity> findEventsByFilter(String nameOrWordInName, int minPrice, int maxPrice) throws ServiceException {
        try {
            if (nameOrWordInName.isEmpty()) {
                return activityDao.findEventsByFilterWithoutSearchParam(minPrice, maxPrice);
            } else {
                return activityDao.findEventsByFilter(nameOrWordInName, minPrice, maxPrice);
            }
        } catch (DaoException  e) {
            throw new ServiceException(e);
        }
    }


    @Override
    public int findMinPrice() throws ServiceException {
        try {
            return activityDao.findMinPrice();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int findMaxPrice() throws ServiceException {
        try {
            return activityDao.findMaxPrice();
        } catch (DaoException  e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int checkPrice(int minPrice) throws ServiceException {
        try {
            int minPossible = activityDao.findMinPrice();
            int maxPossible = activityDao.findMaxPrice();

            if (minPrice < minPossible) {
                return minPossible;
            } else if (minPrice > maxPossible) {
                return maxPossible;
            } else {
                return minPrice;
            }
        } catch (DaoException  e) {
            throw new ServiceException(e);
        }
    }
}
