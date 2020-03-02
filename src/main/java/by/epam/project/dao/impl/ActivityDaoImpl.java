package by.epam.project.dao.impl;

import by.epam.project.connection.ConnectionPool;
import by.epam.project.dao.ActivityDao;

import by.epam.project.entity.Activity;

import by.epam.project.exception.ConnectionPoolException;
import by.epam.project.exception.DaoException;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс реализует интерфейс EventDao
 *
 * @author Shpakova A.
 */


public class ActivityDaoImpl implements ActivityDao {

    private static final String SQL_TAKE_ALL_EVENTS = "SELECT id, name, image_url, description, address, data, price FROM activity";
    private static final String SQL_FIND_BY_NAME_OR_WORD_IN_NAME_WITH_LIMIT = "SELECT id, name, image_url, description, address, data, price" +
            " FROM activity WHERE name LIKE ? OR name LIKE ? OR name LIKE ? OR name LIKE ? LIMIT ?,?";  //LIMIT - ограничиваем кол-во показов. т.е. напр. из 20 строк покажет только
    private static final String SQL_FIND_BY_NAME_OR_WORD_IN_NAME = "SELECT  id, name, image_url, description, address, data, price" +
            " FROM activity WHERE name LIKE ? OR name LIKE ? OR name LIKE ? OR name LIKE ?";
    private static final String SQL_DELETE_EVENT = "DELETE FROM activity WHERE id=?";
    private static final String SQL_UPDATE_EVENT = "UPDATE activity SET name=?, image_url=?, description=?, address=?, data=?, price=? WHERE id=?";
    private static final String SQL_FIND_BY_ID = "SELECT id, name, image_url, description,address, data, price FROM activity WHERE id = ?";
    private static final String SQL_INSERT_EVENT = "INSERT INTO activity (name, image_url, description, address, data, price) VALUES (?,?,?,?,?,?)";
    private static final String SQL_FIND_BY_LIMIT = "SELECT id, name, image_url, description, address, data, price" +
            " FROM activity LIMIT ?, ?";
    private static final String SQL_FIND_BY_FILTER_WITH_LIMIT = "SELECT id, name, image_url, description, address, data, price" +
            " FROM activiy  WHERE (name LIKE ?  OR name LIKE ?  OR name LIKE ?  OR name LIKE ?)  AND (price BETWEEN ? AND ?)  LIMIT ?,?";
    private static final String SQL_FIND_BY_FILTER = "SELECT id, name, image_url, description, address, data, price" +
            " FROM activity WHERE (name LIKE ? OR name LIKE ?  OR name LIKE ?  OR name LIKE ?) AND (price BETWEEN ? AND ?)";
    private static final String SQL_FIND_BY_FILTER_WITHOUT_SEARCH_PARAM_WITH_LIMIT = "SELECT id, name, image_url, description, address, data, price" +
            " FROM activity WHERE (price BETWEEN ? AND ?) LIMIT ?,?";
    private static final String SQL_FIND_BY_FILTER_WITHOUT_SEARCH_PARAM = "SELECT id, name, image_url, description, address, data, price" +
            " FROM activity WHERE (price BETWEEN ? AND ?)";
    private static final String SQL_FIND_MIN_PRICE = "SELECT MIN(price) FROM activity";
    private static final String SQL_FIND_MAX_PRICE = "SELECT MAX(price) FROM activity";

    @Override
    public List<Activity> takeAllEvents() throws DaoException{

        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        ResultSet resultSet = null;
        List<Activity> events = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(SQL_TAKE_ALL_EVENTS);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                events.add(new Activity.Builder()
                        .setEventId(resultSet.getInt(1))
                        .setName(resultSet.getString(2))
                        .setImageURL(resultSet.getString(3))
                        .setDescription(resultSet.getString(4))
                        .setAddress(resultSet.getString(5))
                        .setDate(resultSet.getDate(6).toLocalDate())
                        .setPrice(resultSet.getBigDecimal(7))
                        .build()
                );
            }
            return events;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
           closeConnection(connection);
        }

    }

    @Override
    public List<Activity> findByNameOrWordInNameWithLimit(String nameOrWordInName, int startIndex, int endIndex) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        ResultSet resultSet = null;
        List<Activity> events = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(SQL_FIND_BY_NAME_OR_WORD_IN_NAME_WITH_LIMIT);
            preparedStatement.setString(1, nameOrWordInName + " %");
            preparedStatement.setString(2, "% " + nameOrWordInName + " %");
            preparedStatement.setString(3, "% " + nameOrWordInName);
            preparedStatement.setString(4, nameOrWordInName);
            preparedStatement.setInt(5, startIndex);
            preparedStatement.setInt(6, endIndex);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                events.add(new Activity.Builder()
                        .setEventId(resultSet.getInt(1))
                        .setName(resultSet.getString(2))
                        .setImageURL(resultSet.getString(3))
                        .setDescription(resultSet.getString(4))
                        .setAddress(resultSet.getString(5))
                        .setDate(resultSet.getDate(6).toLocalDate())
                        .setPrice(resultSet.getBigDecimal(7))
                        .build()
                );
            }
            return events;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    @Override
    public List<Activity> findByNameOrWordInName(String nameOrWordInName) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        ResultSet resultSet = null;
        List<Activity> events = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(SQL_FIND_BY_NAME_OR_WORD_IN_NAME);
            preparedStatement.setString(1, nameOrWordInName + " %");
            preparedStatement.setString(2, "% " + nameOrWordInName + " %");
            preparedStatement.setString(3, "% " + nameOrWordInName);
            preparedStatement.setString(4, nameOrWordInName);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                events.add(new Activity.Builder()
                        .setEventId(resultSet.getInt(1))
                        .setName(resultSet.getString(2))
                        .setImageURL(resultSet.getString(3))
                        .setDescription(resultSet.getString(4))
                        .setAddress(resultSet.getString(5))
                        .setDate(resultSet.getDate(6).toLocalDate())
                        .setPrice(resultSet.getBigDecimal(7))
                        .build()
                );
            }
            return events;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    @Override
    public List<Activity> findEventByLimit(int startIndex, int endIndex) throws DaoException{
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        ResultSet resultSet = null;
        List<Activity> events = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(SQL_FIND_BY_LIMIT);
            preparedStatement.setInt(1, startIndex);
            preparedStatement.setInt(2, endIndex);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                events.add(new Activity.Builder()
                        .setEventId(resultSet.getInt(1))
                        .setName(resultSet.getString(2))
                        .setImageURL(resultSet.getString(3))
                        .setDescription(resultSet.getString(4))
                        .setAddress(resultSet.getString(5))
                        .setDate(resultSet.getDate(6).toLocalDate())
                        .setPrice(resultSet.getBigDecimal(7))
                        .build()
                );
            }
            return events;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    @Override
    public List<Activity> findEventsByFilterWithLimit(String nameOrWordInName, int minPrice, int maxPrice, int startIndex, int endIndex) throws DaoException{
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        List<Activity> events = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(SQL_FIND_BY_FILTER_WITH_LIMIT);
            preparedStatement.setString(1, nameOrWordInName + " %");
            preparedStatement.setString(2, "% " + nameOrWordInName + " %");
            preparedStatement.setString(3, "% " + nameOrWordInName);
            preparedStatement.setString(4, nameOrWordInName);
            preparedStatement.setInt(5, minPrice);
            preparedStatement.setInt(6, maxPrice);
            preparedStatement.setInt(7, startIndex);
            preparedStatement.setInt(8, endIndex);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                events.add(new Activity.Builder()
                        .setEventId(resultSet.getInt(1))
                        .setName(resultSet.getString(2))
                        .setImageURL(resultSet.getString(3))
                        .setDescription(resultSet.getString(4))
                        .setAddress(resultSet.getString(5))
                        .setDate(resultSet.getDate(6).toLocalDate())
                        .setPrice(resultSet.getBigDecimal(7))
                        .build()
                );
            }
            return events;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    @Override
    public List<Activity> findEventsByFilter(String nameOrWordInName, int minPrice, int maxPrice) throws DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        List<Activity> events = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(SQL_FIND_BY_FILTER);
            preparedStatement.setString(1, nameOrWordInName + " %");
            preparedStatement.setString(2, "% " + nameOrWordInName + " %");
            preparedStatement.setString(3, "% " + nameOrWordInName);
            preparedStatement.setString(4, nameOrWordInName);
            preparedStatement.setInt(5, minPrice);
            preparedStatement.setInt(6, maxPrice);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                events.add(new Activity.Builder()
                        .setEventId(resultSet.getInt(1))
                        .setName(resultSet.getString(2))
                        .setImageURL(resultSet.getString(3))
                        .setDescription(resultSet.getString(4))
                        .setAddress(resultSet.getString(5))
                        .setDate(resultSet.getDate(6).toLocalDate())
                        .setPrice(resultSet.getBigDecimal(7))
                        .build()
                );
            }
            return events;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    @Override
    public List<Activity> findEventsByFilterWithoutSearchParamWithLimit(int minPrice, int maxPrice, int startIndex, int endIndex) throws DaoException{
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        List<Activity> events = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(SQL_FIND_BY_FILTER_WITHOUT_SEARCH_PARAM_WITH_LIMIT);
            preparedStatement.setInt(1, minPrice);
            preparedStatement.setInt(2, maxPrice);
            preparedStatement.setInt(3, startIndex);
            preparedStatement.setInt(4, endIndex);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                events.add(new Activity.Builder()
                        .setEventId(resultSet.getInt(1))
                        .setName(resultSet.getString(2))
                        .setImageURL(resultSet.getString(3))
                        .setDescription(resultSet.getString(4))
                        .setAddress(resultSet.getString(5))
                        .setDate(resultSet.getDate(6).toLocalDate())
                        .setPrice(resultSet.getBigDecimal(7))
                        .build()
                );
            }
            return events;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    @Override
    public List<Activity> findEventsByFilterWithoutSearchParam(int minPrice, int maxPrice) throws DaoException{
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        List<Activity> events = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(SQL_FIND_BY_FILTER_WITHOUT_SEARCH_PARAM);
            preparedStatement.setInt(1, minPrice);
            preparedStatement.setInt(2, maxPrice);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                events.add(new Activity.Builder()
                        .setEventId(resultSet.getInt(1))
                        .setName(resultSet.getString(2))
                        .setImageURL(resultSet.getString(3))
                        .setDescription(resultSet.getString(4))
                        .setAddress(resultSet.getString(5))
                        .setDate(resultSet.getDate(6).toLocalDate())
                        .setPrice(resultSet.getBigDecimal(7))
                        .build()
                );
            }
            return events;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    @Override
    public List<Activity> findEventsByLimit(int startIndex, int endIndex) throws DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        List<Activity> events = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(SQL_FIND_BY_LIMIT);
            preparedStatement.setInt(1, startIndex);
            preparedStatement.setInt(2, endIndex);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                events.add(new Activity.Builder()
                        .setEventId(resultSet.getInt(1))
                        .setName(resultSet.getString(2))
                        .setImageURL(resultSet.getString(3))
                        .setDescription(resultSet.getString(4))
                        .setAddress(resultSet.getString(5))
                        .setDate(resultSet.getDate(6).toLocalDate())
                        .setPrice(resultSet.getBigDecimal(7))
                        .build()
                );
            }
            return events;

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    @Override
    public int findMinPrice() throws DaoException {
        return findMinOrMax(SQL_FIND_MIN_PRICE);
    }

    public int findMinOrMax(String sqlQuery) throws DaoException {
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = ConnectionPool.INSTANCE.getConnection();
       // List<Activity> events = new ArrayList<>();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                throw new DaoException("Impossible to find min or max price");
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(statement);
            closeConnection(connection);
        }
    }

    @Override
    public int findMaxPrice() throws DaoException {
        return findMinOrMax(SQL_FIND_MAX_PRICE);
    }

    @Override
    public void insert(Activity event) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        try {
            preparedStatement = connection.prepareStatement(SQL_INSERT_EVENT);
            preparedStatement.setString(1, event.getName());
            preparedStatement.setString(2, event.getImageURL());
            preparedStatement.setString(3, event.getDescription());
            preparedStatement.setString(4, event.getAddress());
            preparedStatement.setDate(5, Date.valueOf(event.getDate()));
            preparedStatement.setBigDecimal(6, event.getPrice());
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
            preparedStatement = connection.prepareStatement(SQL_DELETE_EVENT);
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
    public void update(Activity event) throws DaoException{
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        try {
            preparedStatement = connection.prepareStatement(SQL_UPDATE_EVENT);
            preparedStatement.setString(1, event.getName());
            preparedStatement.setString(2, event.getImageURL());
            preparedStatement.setString(3, event.getDescription());
            preparedStatement.setString(4, event.getAddress());
            preparedStatement.setDate(5, Date.valueOf(event.getDate()));
            preparedStatement.setBigDecimal(6, event.getPrice());
            preparedStatement.setInt(7, event.getEventId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    @Override
    public Activity findById(int id) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new Activity.Builder()
                        .setEventId(resultSet.getInt(1))
                        .setName(resultSet.getString(2))
                        .setImageURL(resultSet.getString(3))
                        .setDescription(resultSet.getString(4))
                        .setAddress(resultSet.getString(5))
                        .setDate(resultSet.getDate(6).toLocalDate())
                        .setPrice(resultSet.getBigDecimal(7))
                        .build();
            } else {
                throw new DaoException("No event with such id");
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












