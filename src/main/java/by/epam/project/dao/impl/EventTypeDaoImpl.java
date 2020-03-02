package by.epam.project.dao.impl;

import by.epam.project.connection.ConnectionPool;
import by.epam.project.dao.EventTypeDao;
import by.epam.project.entity.EventType;
import by.epam.project.exception.ConnectionPoolException;
import by.epam.project.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Класс реализует интерфейс EventTypeDao
 *
 * @author Shpakova A.
 */

public class EventTypeDaoImpl implements EventTypeDao {
    private static final String SQL_FIND_BY_EVENT_TYPE = "SELECT id, event_type FROM event_type WHERE event_type = ?";
    private static final String SQL_INSERT = "INSERT INTO event_type (event_type) VALUES (?)";
    private static final String SQL_UPDATE = "UPDATE event_type SET event_type=? WHERE id=?";
    private static final String SQL_DELETE = "DELETE FROM event_type WHERE id=?";
    private static final String SQL_FIND_BY_ID = "SELECT id, event_type FROM event_type WHERE id=?";

    @Override
    public int findIdByEventType(String eventType) throws DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        try {
            preparedStatement = connection.prepareStatement(SQL_FIND_BY_EVENT_TYPE);
            preparedStatement.setString(1, eventType);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
            return resultSet.getInt(1);           //?

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    @Override
    public void insert(EventType eventType) throws DaoException{
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        try {
            preparedStatement = connection.prepareStatement(SQL_INSERT);
            preparedStatement.setString(1, eventType.getEventTypeValue());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    @Override
    public void delete(int id) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        try {
            preparedStatement = connection.prepareStatement(SQL_DELETE);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    @Override
    public void update(EventType eventType) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        try {
            preparedStatement = connection.prepareStatement(SQL_UPDATE);
            preparedStatement.setString(1, eventType.getEventTypeValue());
            preparedStatement.setInt(2, eventType.getEventTypeId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }

    }

    @Override
    public EventType findById(int id) throws DaoException{
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        try {
            preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID);
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new EventType.Builder()
                        .setEventTypeId(resultSet.getInt(1))
                        .setEventTypeValue(resultSet.getString(2))
                        .build();
            } else {
                throw new DaoException("No event type with such id");
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
    }
}
