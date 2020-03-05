package by.epam.project.dao;

import by.epam.project.entity.Activity;
import by.epam.project.exception.DaoException;

import java.util.List;

/**
 * Interface for actions with {@link Activity} according DAO and database data
 *
 * @author Shpakova A.
 */


public interface ActivityDao extends BasicDao<Activity> {

    /**
     * Method: show all events from database
     *
     * @return list of events
     */
    List<Activity> takeAllEvents() throws DaoException;

    /**
     * Method:for search from database any event by name or word in name
     *
     * @param startIndex - start number of line from what will be shown event
     * @param endIndex   - end number of line
     * @return list of events
     */
    List<Activity> findByNameOrWordInNameWithLimit(String nameOrWordInName, int startIndex, int endIndex) throws DaoException; //для поиска события с ограничение показа на странице

    /**
     * Method:for search from database any event by name or word in name
     *
     * @return list of events
     */
    List<Activity> findByNameOrWordInName(String nameOrWordInName) throws DaoException;

    /**
     * Method:for search from database any event by from start index to end index
     *
     * @param startIndex - start number of line from what will be shown event
     * @param endIndex   - end number of line
     * @return list of events
     */
    List<Activity> findEventByLimit(int startIndex, int endIndex) throws DaoException;

    /**
     * Method:for search from database any event by name or word in name
     * from start index to end index with filter by price of ticket
     *
     * @param startIndex - start number of line from what will be shown event
     * @param endIndex   - end number of line
     * @param minPrice   - from what price will be searching
     * @param maxPrice   - maximum price of ticket
     * @return list of events
     */
    List<Activity> findEventsByFilterWithLimit(String nameOrWordInName, int minPrice, int maxPrice, int startIndex, int endIndex) throws DaoException;

    /**
     * Method:for search from database any event by name or word in name from minimum price to maximum price
     *
     * @param minPrice - from what price will be searching
     * @param maxPrice - maximum price of ticket
     * @return list of events
     */
    List<Activity> findEventsByFilter(String nameOrWordInName, int minPrice, int maxPrice) throws DaoException;

    /**
     * Method:for search from database all events from minimum price to maximum price with limits
     *
     * @param minPrice   - from what price will be searching
     * @param maxPrice   - maximum price of ticket
     * @param startIndex - start number of line from what will be shown event
     * @param endIndex   - end number of line
     * @return list of events
     */
    List<Activity> findEventsByFilterWithoutSearchParamWithLimit(
            int minPrice, int maxPrice, int startIndex, int endIndex) throws DaoException;

    /**
     * Method:for search from database any event  from minimum price to maximum price
     *
     * @param minPrice - from what price will be searching
     * @param maxPrice - maximum price of ticket
     * @return list of events
     */
    List<Activity> findEventsByFilterWithoutSearchParam(int minPrice, int maxPrice) throws DaoException;

    /**
     * Method:for search from database event with minimum price
     *
     * @return minimum price of event
     */
    int findMinPrice() throws DaoException;

    /**
     * Method:for search from database event with maximum price
     *
     * @return maximum price of event
     */
    int findMaxPrice() throws DaoException;
}