package by.epam.project.service;

import by.epam.project.entity.Activity;

import by.epam.project.exception.ServiceException;

import java.util.List;

/**
 * Слой для взаимдействия  с ActivityDao
 *
 * @author Shpakova A.
 */

public interface ActivityService {
    List<Activity> takeAllEvents() throws ServiceException;

    List<Activity> findByNameOrWordInNameWithLimit(String nameOrWordInName, int startIndex, int endIndex) throws ServiceException; //для поиска события с ограничение показа на странице

    List<Activity> findByNameOrWordInName(String nameOrWordInName) throws ServiceException;

    Activity findByEventId(int eventId) throws ServiceException;

    List<Activity> findEventByLimit(int startIndex, int endIndex) throws ServiceException;

}
