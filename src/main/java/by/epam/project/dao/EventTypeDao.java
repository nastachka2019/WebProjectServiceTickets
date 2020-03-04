package by.epam.project.dao;


import by.epam.project.entity.EventType;
import by.epam.project.exception.DaoException;


/**
 * Interface for actions with {@link EventType} according DAO and database data
 *
 * @author Shpakova A.
 */

public interface EventTypeDao extends BasicDao<EventType> {
    /**
     * Method: find id of event type by name of event type
     *
     * @return id of event type from database
     */
    int findIdByEventType(String eventType) throws DaoException;
}
