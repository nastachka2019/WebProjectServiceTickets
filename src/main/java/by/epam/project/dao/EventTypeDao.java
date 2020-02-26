package by.epam.project.dao;

import by.epam.project.entity.EventType;
import by.epam.project.exception.ConnectionPoolException;
import by.epam.project.exception.DaoException;


/**
 * Класс представляет собой слой для взаимодействия с базой данных EventType
 *
 * @author Shpakova A.
 */

public interface EventTypeDao extends BasicDao<EventType> {

    int findIdByEventType(String eventType) throws DaoException, ConnectionPoolException;
}
