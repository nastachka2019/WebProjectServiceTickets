package by.epam.project.dao;

import by.epam.project.entity.Ticket;
import by.epam.project.exception.ConnectionPoolException;
import by.epam.project.exception.DaoException;

import java.util.List;
import java.util.Set;

/**
 * Класс представляет собой слой для взаимодействия с базой данных Ticket
 *
 * @author Shpakova A.
 */

public interface TicketDao extends BasicDao<Ticket> {

    void updateQuantity(int ticketId, int quantity) throws DaoException;

    Set<String> findDatesByUserId(int userId) throws DaoException;

    List<Ticket> findTicketByUserIdAndTicketDateAndEventType(int userId, String ticketDate, String eventType) throws DaoException;

//    Ticket findTicketByUserIdTicketDateEventTypeEventId(Ticket ticket) throws DaoException, ConnectionPoolException;

    void deleteTicketByUserId(int userId) throws DaoException;

    int totalPriceByUserIdAndTicketDate(int userId,String ticketDate) throws DaoException;


}
