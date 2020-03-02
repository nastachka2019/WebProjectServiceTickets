package by.epam.project.dao.impl;

import by.epam.project.connection.ConnectionPool;
import by.epam.project.dao.TicketDao;
import by.epam.project.entity.Activity;
import by.epam.project.entity.EventType;
import by.epam.project.entity.Ticket;
import by.epam.project.entity.User;
import by.epam.project.exception.DaoException;
import by.epam.project.exception.ServiceException;
import by.epam.project.service.ActivityService;
import by.epam.project.service.EventTypeService;
import by.epam.project.service.UserService;
import by.epam.project.service.impl.ActivityServiceImpl;
import by.epam.project.service.impl.EventTypeServiceImpl;
import by.epam.project.service.impl.UserServiceImpl;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Класс реализует интерфейс TicketDao
 *
 * @author Shpakova A.
 */

public class TicketDaoImpl implements TicketDao {
    private static final String SQL_INSERT = "INSERT INTO ticket (user_id, event_id, quantity, event_type_id, date) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_DELETE_TICKET = "DELETE FROM ticket WHERE id=?";
    private static final String SQL_UPDATE_TICKET = "UPDATE ticket SET user_id=?, event_id=?, quantity=?, event_type_id=?, date=? WHERE id=?";
    private static final String SQL_UPDATE_QUANTITY = "UPDATE ticket SET quantity=? WHERE id=?";
    private static final String SQL_DELETE_TICKET_BY_USER_ID = "DELETE FROM ticket WHERE id=?";
    private static final String SQL_FIND_DATE_BY_USER_ID = "SELECT date FROM ticket WHERE id = ?";
    private static final String SQL_FIND_TICKET_BY_USER_ID_AND_TICKET_DATE_AND_EVENT_TYPE =
            "SELECT ticket.id, ticket.user_id, ticket.event_id,ticket.quantity, ticket.event_type_id, ticket.date FROM ticket INNER JOIN event_type ON ticket.event_type_id= event_type.id" +
                    " WHERE user_id=? AND date=? AND event_type=?";
    private static final String SQL_FIND_TICKET_BY_USER_ID_TICKET_DATE_EVENT_TYPE_EVENT_ID = "SELECT ticket_id, user_id, event_id, quantity, event_type_id, date" +
            " FROM ticket WHERE user_id=? AND event_id=? AND date=? AND event_type_id=?";
    private static final String SQL_FIND_TICKET_BY_ID = "SELECT id, user_id, event_id, quantity. event_type_id, date" +
            " FROM ticket WHERE id=?";
    private static final String SQL_COUNT_TOTAL_PRICE_BY_USER =
            "SELECT (price * quantity) FROM activity INNER JOIN ticket ON activity.id=ticket.event_id GROUP BY user_id";

    @Override
    public void updateQuantity(int ticketId, int quantity) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        try {
            preparedStatement = connection.prepareStatement(SQL_UPDATE_QUANTITY);
            preparedStatement.setInt(1, quantity);
            preparedStatement.setInt(2, ticketId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    @Override
    public Set<String> findDatesByUserId(int userId) throws DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        Set<String> dates = new TreeSet<>();
        try {
            preparedStatement = connection.prepareStatement(SQL_FIND_DATE_BY_USER_ID);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                dates.add(resultSet.getString(1));
            }
            return dates;
        } catch (SQLException e) {
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

    private EventType findEventTypeById(int eventTypeId) throws ServiceException {
        EventTypeService eventTypeService = new EventTypeServiceImpl();
        return eventTypeService.findEventTypeById(eventTypeId);
    }

    private Activity findEventById(int eventId) throws ServiceException {
        ActivityService eventService = new ActivityServiceImpl();
        return eventService.findByEventId(eventId);
    }

    @Override
    public List<Ticket> findTicketByUserIdAndTicketDateAndEventType(int userId, String ticketDate, String eventType) throws DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        List<Ticket> ticketList = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(SQL_FIND_TICKET_BY_USER_ID_AND_TICKET_DATE_AND_EVENT_TYPE);
            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, ticketDate);
            preparedStatement.setString(3, eventType);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ticketList.add(new Ticket.Builder()
                        .setTicketId(resultSet.getInt(1))
                        .setUser(findUserById(resultSet.getInt(2)))
                        .setEvent(findEventById(resultSet.getInt(3)))
                        .setQuantity(resultSet.getInt(4))
                        .setEventType(findEventTypeById(resultSet.getInt(5)))
                        .build());
            }
            return ticketList;

        } catch (SQLException | ServiceException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
    }

//    @Override
//    public Ticket findTicketByUserIdTicketDateEventTypeEventId(Ticket ticket) throws DaoException, ConnectionPoolException {
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//        Connection connection = ConnectionPool.INSTANCE.getConnection();
//        try {
//            preparedStatement = connection.prepareStatement(SQL_FIND_TICKET_BY_USER_ID_TICKET_DATE_EVENT_TYPE_EVENT_ID);
//            preparedStatement.setInt(1, ticket.getUser().getUserId());
//            preparedStatement.setInt(2, ticket.getEvent().getEventId());
//            preparedStatement.setInt(3, ticket.getEventType().getEventTypeId());
//            preparedStatement.setDate(4, Date.valueOf(ticket.getDate()));
//            resultSet = preparedStatement.executeQuery();
//
//            if (resultSet.next()) {
//                return new Ticket.Builder()
//                        .setTicketId(resultSet.getInt(1))
//                        .setUser(findUserById(resultSet.getInt(2)))
//                        .setEvent(findEventById(resultSet.getInt(3)))
//                        .setQuantity(resultSet.getInt(4))
//                        .setEventType(findEventTypeById(resultSet.getInt(5)))
//                        .setDate(resultSet.getDate(6).toLocalDate())
//                        .setQuantity(resultSet.getInt(6))
//                        .build();
//            }
//            return ticket;
//
//        } catch (SQLException | ServiceException e) {
//            throw new DaoException(e);
//        } finally {
//            closeResultSet(resultSet);
//            closePreparedStatement(preparedStatement);
//            ConnectionPool.INSTANCE.releaseConnection(connection);
//        }
//
//    }

    @Override
    public void deleteTicketByUserId(int userId) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        try {
            preparedStatement = connection.prepareStatement(SQL_DELETE_TICKET_BY_USER_ID);
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
    public int totalPriceByUserId(int userId) throws DaoException {
        return totalValueByUserId(userId,  SQL_COUNT_TOTAL_PRICE_BY_USER);
    }
    private int totalValueByUserId(int userId,  String sqlQuery) throws DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        try {
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                return 0;
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
    public void insert(Ticket ticket) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        try {
            preparedStatement = connection.prepareStatement(SQL_INSERT);
            preparedStatement.setInt(1, ticket.getUser().getUserId());
            preparedStatement.setInt(2, ticket.getEvent().getEventId());
            preparedStatement.setInt(3, ticket.getQuantity());
            preparedStatement.setInt(4, ticket.getEventType().getEventTypeId());
            preparedStatement.setDate(5, Date.valueOf(ticket.getDate()));
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
            preparedStatement = connection.prepareStatement(SQL_DELETE_TICKET);
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
    public void update(Ticket ticket) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        try {
            preparedStatement = connection.prepareStatement(SQL_UPDATE_TICKET);
            preparedStatement.setInt(1, ticket.getUser().getUserId());
            preparedStatement.setInt(2, ticket.getEvent().getEventId());
            preparedStatement.setInt(3, ticket.getQuantity());
            preparedStatement.setInt(4, ticket.getEventType().getEventTypeId());
            preparedStatement.setDate(5, Date.valueOf(ticket.getDate()));
            preparedStatement.setInt(6, ticket.getTicketId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    @Override
    public Ticket findById(int id) throws DaoException{
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        try {
            preparedStatement = connection.prepareStatement(SQL_FIND_TICKET_BY_ID);
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Ticket.Builder()
                        .setTicketId(resultSet.getInt(1))
                        .setUser(findUserById(resultSet.getInt(2)))
                        .setEvent(findEventById(resultSet.getInt(3)))
                        .setQuantity(resultSet.getInt(4))
                        .setEventType(findEventTypeById(resultSet.getInt(5)))
                        .setDate(resultSet.getDate(6).toLocalDate())
                        .build();
            } else {
                throw new DaoException("No ticket with such id");
            }
        } catch (SQLException | ServiceException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
    }
}

