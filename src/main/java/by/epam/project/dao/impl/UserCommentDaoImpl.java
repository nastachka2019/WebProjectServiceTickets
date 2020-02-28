package by.epam.project.dao.impl;

import by.epam.project.connection.ConnectionPool;
import by.epam.project.dao.UserCommentDao;

import by.epam.project.entity.User;
import by.epam.project.entity.UserComment;
import by.epam.project.exception.ConnectionPoolException;
import by.epam.project.exception.DaoException;
import by.epam.project.exception.ServiceException;

import by.epam.project.service.UserService;

import by.epam.project.service.impl.UserServiceImpl;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс реализует интерфейс UserCommentDao
 *
 * @author Shpakova A.
 */


public class UserCommentDaoImpl implements UserCommentDao {
    private static final String SQL_INSERT_COMMENT = "INSERT into user_comment (dateComment, ticketDate, user_id, comment,commentator_id)" +
            " VALUES (?,?,?,?,?)";
    private static final String SQL_DELETE_COMMENT = "DELETE FROM user_comment WHERE comment_id=?";
    private static final String SQL_FIND_COMMENTS = "SELECT comment_id, dateComment, ticketDate, user_id, comment,commentator_id" +
            " FROM user_comment WHERE user_id=? AND ticketDate=?";
    private static final String SQL_UPDATE = "UPDATE user_comment SET user_id=?, dateComment=?, ticketDate=?, user_id=?, comment=?,commentator_id=?" +
            " WHERE comment_id=?";
    private static final String SQL_FIND_COMMENT_BY_ID = "SELECT comment_id, dateComment, ticketDate, user_id, comment,commentator_id" +
                    " FROM user_comment WHERE comment_id=?";
    private static final String SQL_DELETE_COMMENTS_FOR_USER_BY_DATE = "DELETE FROM user_comment WHERE user_id=? AND dateComment=?";
    private static final String SQL_DELETE_COMMENTS_FOR_USER = "DELETE FROM user_comment WHERE user_id=?";
    private static final String SQL_DELETE_COMMENTS_BY_COMMENTATOR = "DELETE FROM user_comment WHERE commentator_id=?";

    @Override
    public List<UserComment> findComments(int userId, String ticketDate) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        ResultSet resultSet = null;
        List<UserComment> userCommentList = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(SQL_FIND_COMMENTS);
            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, ticketDate);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                userCommentList.add(new UserComment.Builder()
                        .setCommentId(resultSet.getInt(1))
                        .setDateOfComment(resultSet.getTimestamp(2).toLocalDateTime())
                        .setTicketDate(resultSet.getDate(3).toLocalDate())
                        .setUserId(resultSet.getInt(4))
                        .setComment(resultSet.getString(5))
                        .setCommentator(createUser(resultSet.getInt(6)))
                        .build()
                );
            }
            return userCommentList;

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
           closeConnection(connection);
        }
    }

    @Override
    public void deleteCommentsForUser(int userId) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        try {
            preparedStatement = connection.prepareStatement(SQL_DELETE_COMMENTS_FOR_USER);
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    @Override
    public void deleteCommentsByCommentator(int commentatorId) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        try {
            preparedStatement = connection.prepareStatement(SQL_DELETE_COMMENTS_BY_COMMENTATOR);
            preparedStatement.setInt(1, commentatorId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    @Override
    public void deleteCommentsForUserByDate(int userId, LocalDate selectedDate) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        try {
            preparedStatement = connection.prepareStatement(SQL_DELETE_COMMENTS_FOR_USER_BY_DATE);
            preparedStatement.setInt(1, userId);
            preparedStatement.setDate(2, Date.valueOf(selectedDate));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    private User createUser(int userId) throws DaoException {
        UserService userService = new UserServiceImpl();
        try {
            return userService.findUserById(userId);
        } catch (ServiceException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void insert(UserComment userComment) throws DaoException{
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        try {
            preparedStatement = connection.prepareStatement(SQL_INSERT_COMMENT);
            preparedStatement.setTimestamp(1, Timestamp.valueOf(userComment.getDateOfComment()));
            preparedStatement.setDate(2, Date.valueOf(userComment.getTicketDate()));
            preparedStatement.setInt(3, userComment.getUserId());
            preparedStatement.setString(4, userComment.getComment());
            preparedStatement.setInt(5, userComment.getCommentator().getUserId());
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
            preparedStatement = connection.prepareStatement(SQL_DELETE_COMMENT);
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
    public void update(UserComment userComment) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        try {
            preparedStatement = connection.prepareStatement(SQL_UPDATE);
            preparedStatement.setTimestamp(1, Timestamp.valueOf(userComment.getDateOfComment()));
            preparedStatement.setDate(2, Date.valueOf(userComment.getTicketDate()));
            preparedStatement.setInt(3, userComment.getUserId());
            preparedStatement.setString(4, userComment.getComment());
            preparedStatement.setInt(5, userComment.getCommentator().getUserId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    @Override
    public UserComment findById(int id) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_FIND_COMMENT_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new UserComment.Builder()
                        .setCommentId(resultSet.getInt(1))
                        .setDateOfComment(resultSet.getTimestamp(2).toLocalDateTime())
                        .setTicketDate(resultSet.getDate(3).toLocalDate())
                        .setUserId(resultSet.getInt(4))
                        .setComment(resultSet.getString(5))
                        .setCommentator(findUserById(resultSet.getInt(6)))
                        .build();
            } else {
                throw new DaoException("No comment with such id");
            }

        } catch (SQLException | ServiceException e){
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
    }
    private User findUserById(int userId) throws ServiceException {
        UserService userService = new UserServiceImpl();
        return userService.findUserById(userId);
    }
}

