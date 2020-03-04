package by.epam.project.dao;


import by.epam.project.entity.Ticket;
import by.epam.project.exception.DaoException;

import java.util.List;
import java.util.Set;

/**
 * Interface for actions with {@link Ticket} according DAO and database data
 *
 * @author Shpakova A.
 */

public interface TicketDao extends BasicDao<Ticket> {
    /**
     * Method: update quantity of ticket when we do order
     *
     * @return quantity of tickets
     */
    void updateQuantity(int ticketId, int quantity) throws DaoException;

    /**
     * Method: find dates of ticket by user id
     *
     * @return list of all dates of tickets of user
     */
    Set<String> findDatesByUserId(int userId) throws DaoException;

    /**
     * Method: find ticket by user id. date of ticket and event type
     *
     * @return list of all tickets
     */
    List<Ticket> findTicketByUserIdAndTicketDateAndEventType(int userId, String ticketDate, String eventType) throws DaoException;

    /**
     * Method: find tickets by user id
     *
     * @return list of all tickets of user
     */
    List<Ticket> findTicketByUserId(int userId) throws DaoException;

    /**
     * Method: delete ticket from order
     */
    void deleteTicketByUserId(int userId) throws DaoException;

    /**
     * Method: show total price of order by date of ticket and user id
     *
     * @return total price of order
     */
    int totalPriceByUserIdAndTicketDate(int userId, String ticketDate) throws DaoException;


}
