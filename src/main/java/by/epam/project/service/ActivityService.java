package by.epam.project.service;

import by.epam.project.entity.Activity;

import by.epam.project.exception.ServiceException;

import java.util.List;

/**
 * Interface for {@link by.epam.project.service.impl.ActivityServiceImpl}
 *
 * @author Shpakova A.
 */

public interface ActivityService {

    List<Activity> takeAllEvents() throws ServiceException;

    List<Activity> findByNameOrWordInNameWithLimit(String nameOrWordInName, int startIndex, int endIndex) throws ServiceException; //для поиска события с ограничение показа на странице

    List<Activity> findByNameOrWordInName(String nameOrWordInName) throws ServiceException;

    Activity findByEventId(int eventId) throws ServiceException;

    List<Activity> findEventByLimit(int startIndex, int endIndex) throws ServiceException;

    List<Activity> findEventsByFilterWithLimit(String nameOrWordInName, int minPrice,
                                               int maxPrice, int startIndex, int endIndex) throws ServiceException;

    List<Activity> findEventsByFilter(String nameOrWordInName, int minPrice, int maxPrice) throws ServiceException;


    int findMinPrice() throws ServiceException;

    int findMaxPrice() throws ServiceException;

    int checkPrice(int minPrice) throws ServiceException;

}
