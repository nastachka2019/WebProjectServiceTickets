package by.epam.project.dao;

import by.epam.project.entity.Entity;
import by.epam.project.exception.ConnectionPoolException;
import by.epam.project.exception.DaoException;
import org.apache.logging.log4j.LogManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Описание общих методов, которые будут использоваться при взаимодействии с бд
 *
 * @author Shpakova A.
 */

public interface BasicDao<T extends Entity> {
    void insert(T entity) throws DaoException, ConnectionPoolException;    //вставка по ключу

    void delete(int id) throws DaoException, ConnectionPoolException;  //удаление по ключу

    void update(T entity) throws DaoException, ConnectionPoolException; //обновить по ключу

    T findById(int id) throws DaoException, ConnectionPoolException;

    default void closeResultSet(ResultSet resultSet) throws DaoException { //метод закрытия экземпляра ResultSet
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                LogManager.getLogger().error(e.getMessage(), e);
            }
        }
    }

    default void closePreparedStatement(Statement statement) {      //метод закрытия экземпляра PrepareStatement
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                LogManager.getLogger().error(e.getMessage(), e);
            }
        }
    }
}

