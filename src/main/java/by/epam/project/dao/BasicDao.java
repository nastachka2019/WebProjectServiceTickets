package by.epam.project.dao;

import by.epam.project.connection.ConnectionPool;
import by.epam.project.entity.Entity;
import by.epam.project.exception.ConnectionPoolException;
import by.epam.project.exception.DaoException;
import org.apache.logging.log4j.LogManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Description of common methods that will be used when interacting with the database
 *
 * @author Shpakova A.
 */

public interface BasicDao<T extends Entity> {

   
    /**
     * Method: insert in database row with parameters
     *
     * @throws DaoException object
     */
    void insert(T entity) throws DaoException;

    /**
     * Method: update database's row  by id
     *
     * @throws DaoException object
     */
    void delete(int id) throws DaoException;  //удаление по ключу

    /**
     * Method: delete from database object by id
     *
     * @throws DaoException object
     */

    void update(T entity) throws DaoException;

    /**
     * Method: find object by id in database table depending on method's realization
     * and create suitable object.
     *
     * @return found object
     * @throws DaoException object
     */
    T findById(int id) throws DaoException;

    /**
     * Method: close Statement
     *
     * @param resultSet ResultSet
     */
    default void closeResultSet(ResultSet resultSet) { //метод закрытия экземпляра ResultSet
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                LogManager.getLogger().error(e.getMessage(), e);
            }
        }
    }

    /**
     * Method: close Statement
     *
     * @param statement Statement
     */
    default void closePreparedStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                LogManager.getLogger().error(e.getMessage(), e);
            }
        }
    }

    /**
     * Method: Returning a Connection instance to the connection pool
     */
    default void closeConnection(Connection connection) throws DaoException {
        ConnectionPool connectionPool = ConnectionPool.INSTANCE;
        try {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        } catch (ConnectionPoolException e) {
            throw new DaoException();
        }
    }
}

