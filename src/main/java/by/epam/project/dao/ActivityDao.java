package by.epam.project.dao;

import by.epam.project.entity.Activity;
import by.epam.project.exception.ConnectionPoolException;
import by.epam.project.exception.DaoException;

import java.util.List;

/**
 * Класс представляет собой слой для взаимодействия с базой данных Event
 *
 * @author Shpakova A.
 */

public interface ActivityDao extends BasicDao<Activity> {

    List<Activity> takeAllEvents() throws DaoException;

    List<Activity> findByNameOrWordInNameWithLimit(String nameOrWordInName, int startIndex, int endIndex) throws DaoException; //для поиска события с ограничение показа на странице

    List<Activity> findByNameOrWordInName(String nameOrWordInName) throws DaoException;

    List<Activity> findEventByLimit(int startIndex, int endIndex) throws DaoException;

    List<Activity> findEventsByFilterWithLimit(String nameOrWordInName, int minPrice, int maxPrice, int startIndex, int endIndex) throws DaoException;

    List<Activity> findEventsByFilter(String nameOrWordInName, int minPrice, int maxPrice) throws DaoException;

    List<Activity> findEventsByFilterWithoutSearchParamWithLimit(
            int minPrice, int maxPrice, int startIndex, int endIndex) throws DaoException;

    List<Activity> findEventsByFilterWithoutSearchParam (int minPrice, int maxPrice) throws DaoException;

    List<Activity> findEventsByLimit(int startIndex, int endIndex) throws DaoException;

    int findMinPrice() throws DaoException;

    int findMaxPrice() throws DaoException;
}