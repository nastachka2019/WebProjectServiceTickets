package by.epam.project.dao.impl;

import by.epam.project.connection.ConnectionPool;
import by.epam.project.dao.UserDao;
import by.epam.project.entity.User;
import by.epam.project.entity.UserRole;
import by.epam.project.exception.ConnectionPoolException;
import by.epam.project.exception.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Types.NULL;

/**
 * Класс реализует интерфейс UserDao
 *
 * @author Shpakova A.
 */

public class UserDaoImpl implements UserDao {

    private static final String SQL_INSERT_USER = "INSERT into user (user_role, user_name, user_surname,email, login, password, phone, date_of_birth)" +
            " VALUES (?, ?, ?, ?, ?, ?, ?,?)";
    private static final String SQL_DELETE_USER = "DELETE FROM user WHERE id=?";
    private static final String SQL_TAKE_ALL_USERS = "SELECT id, user_role, user_name, user_surname,email, login, password, phone, date_of_birth FROM user";
    private static final String SQL_FIND_USER_BY_ID = "SELECT id, user_role, user_name, user_surname, email, login, password, phone, date_of_birth FROM user WHERE id=?";
    private static final String SQL_FIND_USER_BY_LOGIN_AND_PASS = "SELECT id, user_role, user_name, user_surname,email, login, password, phone, date_of_birth" +
            " FROM user WHERE login = ? AND password = ? ";
    private static final String SQL_FIND_USER_BY_EMAIL = "SELECT id, user_role, user_name, user_surname,email, login, password, phone, date_of_birth" +
            " FROM user WHERE email = ?";
    private static final String SQL_FIND_USER_BY_EMAIL_AND_PASS = "SELECT id, user_role, user_name, user_surname,email, login, password, phone, date_of_birth" +
            " FROM user WHERE email = ? AND password = ? ";
    private static final String SQL_FIND_BY_LOGIN = "SELECT password FROM user WHERE login = ?";  //если забыл пароль
    private static final String SQL_FIND_BY_EMAIl = "SELECT password FROM user WHERE email = ?";
    private static final String SQL_FIND_USER_BY_LOGIN = "SELECT id,user_role, user_name, user_surname,email, login, password, phone, date_of_birth" +
            " FROM user WHERE login = ?";
    private static final String SQL_UPDATE = "UPDATE user SET user_role=?, user_name=?, user_surname=?,email=?, login=?, password=?, phone=?, date_of_birth=? WHERE id=?";
    private static final String SQL_TAKE_USER_ROLE = "SELECT user_role FROM user WHERE id=?";
    private static final String SQL_TAKE_ALL_USERS_WITH_LIMIT =
            "SELECT id, user_role, user_name, user_surname, email, login, password, phone, date_of_birth FROM user LIMIT ?,?";
    private static final String SQL_UPDATE_USER_ROLE = "UPDATE user SET user_role=? WHERE id=?";

    @Override
    public void insert(User user) throws DaoException {
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_INSERT_USER);
            preparedStatement.setString(1, user.getUserRole().name());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getSurname());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getLogin());
            preparedStatement.setString(6, user.getPassword());
            preparedStatement.setString(7, user.getPhone());
            if (user.getDateOfBirth() != null) {
                preparedStatement.setDate(8, Date.valueOf(user.getDateOfBirth()));   //можно не указывать
            } else {
                preparedStatement.setNull(8, NULL);
            }
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
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_DELETE_USER);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    @Override
    public void update(User user) throws DaoException{
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_UPDATE);
            preparedStatement.setString(1, user.getUserRole().name());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getSurname());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getLogin());
            preparedStatement.setString(6, user.getPassword());
            preparedStatement.setString(7, user.getPhone());
            if (user.getDateOfBirth() != null) {
                preparedStatement.setDate(8, Date.valueOf(user.getDateOfBirth())); //можно не указывать
            } else {
                preparedStatement.setNull(8, NULL);
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    @Override
    public User findById(int id) throws DaoException{
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_FIND_USER_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User.Builder userBuilder = new User.Builder()
                        .setUserId(resultSet.getInt(1))
                        .setUserRole(UserRole.valueOf(resultSet.getString(2)))
                        .setName(resultSet.getString(3))
                        .setSurname(resultSet.getString(4))
                        .setEmail(resultSet.getString(5))
                        .setLogin(resultSet.getString(6))
                        .setPassword(resultSet.getString(7))
                        .setPhone(resultSet.getString(8));
                if (resultSet.getDate(9) != null) {
                    userBuilder.setDateOfBirth(resultSet.getDate(9).toLocalDate());
                }
                return userBuilder.build();
            } else {
                throw new DaoException("No user with such id");
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
        }


    @Override
    public boolean containsLogin(String login) throws DaoException {
        return dbContains(login, SQL_FIND_USER_BY_LOGIN);
    }

    private boolean dbContains(String value, String sqlQuery) throws DaoException{ //для команды изменения пользовательских пар-ов
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, value);
            resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    @Override
    public boolean containsEmail(String email) throws DaoException {
        return dbContains(email, SQL_FIND_USER_BY_EMAIL);
    }

    @Override
    public List<User> takeAllUsers() throws DaoException{
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<User> userList = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(SQL_TAKE_ALL_USERS);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User.Builder userBuilder = new User.Builder()
                        .setUserId(resultSet.getInt(1))
                        .setUserRole(UserRole.valueOf(resultSet.getString(2)))
                        .setName(resultSet.getString(3))
                        .setSurname(resultSet.getString(4))
                        .setEmail(resultSet.getString(5))
                        .setLogin(resultSet.getString(6))
                        .setPassword(resultSet.getString(7))
                        .setPhone(resultSet.getString(8));
                if (resultSet.getDate(9) != null) {
                    userBuilder.setDateOfBirth(resultSet.getDate(9).toLocalDate());
                }
                userList.add(userBuilder.build());
            }
            return userList;

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    @Override
    public List<User> takeAllUsersWithLimit(int startIndex, int endIndex) throws DaoException {
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<User> userList = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(SQL_TAKE_ALL_USERS_WITH_LIMIT);
            preparedStatement.setInt(1, startIndex);
            preparedStatement.setInt(2, endIndex);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                User.Builder userBuilder = new User.Builder()
                        .setUserId(resultSet.getInt(1))
                        .setUserRole(UserRole.valueOf(resultSet.getString(2)))
                        .setName(resultSet.getString(3))
                        .setSurname(resultSet.getString(4))
                        .setEmail(resultSet.getString(5))
                        .setLogin(resultSet.getString(6))
                        .setPassword(resultSet.getString(7))
                        .setPhone(resultSet.getString(8));
                if (resultSet.getDate(9) != null) {
                    userBuilder.setDateOfBirth(resultSet.getDate(9).toLocalDate());
                }

                userList.add(userBuilder.build());
            }
            return userList;

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    @Override
    public List<User> findByLoginAndPass(String login, String password) throws DaoException {    //если забыл логин
        return findParams(login, password, SQL_FIND_USER_BY_LOGIN_AND_PASS, SQL_FIND_BY_LOGIN);
    }

    @Override
    public List<User> findByEmailAndPass(String email, String password) throws DaoException {
        return findParams(email, password, SQL_FIND_USER_BY_EMAIL_AND_PASS, SQL_FIND_BY_EMAIl);
    }

    @Override
    public void updateUserRole(int userId, String userRole) throws DaoException{
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_UPDATE_USER_ROLE);
            preparedStatement.setString(1, userRole);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    @Override
    public UserRole takeUserRole(int userId) throws DaoException {
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_TAKE_USER_ROLE);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return UserRole.valueOf(resultSet.getString(1));
            } else {
                throw new DaoException("No user with such id");
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
    }


    private String findPassByValue(String value, String dataSql) throws DaoException {   // скл запрос. т.е можем получить пароль по какому-то параметру.
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(dataSql);
            preparedStatement.setString(1, value);
            resultSet = preparedStatement.executeQuery();
            String password = null;
            if (resultSet.next()) {
                password = resultSet.getString(1);
            }
            return password;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    private List<User> findParams(String value1, String value2, String dataSql, String dataSql2) throws DaoException{ //по нескольким параметроам можем получить инфу о юзерах(find by email and password)
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<User> users = new ArrayList<>();
        String pass = findPassByValue(value1, dataSql2);
        if (pass != null) {
            try {
                preparedStatement = connection.prepareStatement(dataSql);
                preparedStatement.setString(1, value1);
                preparedStatement.setString(2, pass);
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    User.Builder userBuilder = new User.Builder()
                            .setUserId(resultSet.getInt(1))
                            .setUserRole(UserRole.valueOf(resultSet.getString(2)))
                            .setName(resultSet.getString(3))
                            .setSurname(resultSet.getString(4))
                            .setEmail(resultSet.getString(5))
                            .setLogin(resultSet.getString(6))
                            .setPassword(resultSet.getString(7))
                            .setPhone(resultSet.getString(8))
                            .setDateOfBirth(resultSet.getDate(9).toLocalDate());
                    users.add(userBuilder.build());
                }
                return users;

            } catch (SQLException e) {
                throw new DaoException(e);
            } finally {
                closeResultSet(resultSet);
                closePreparedStatement(preparedStatement);
                closeConnection(connection);
            }
        }
        return users;
    }
}
