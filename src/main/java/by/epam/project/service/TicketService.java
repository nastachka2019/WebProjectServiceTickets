package by.epam.project.service;


import by.epam.project.entity.Ticket;
import by.epam.project.exception.ServiceException;

import java.util.List;
import java.util.Set;

/**
 * Interface for {@link by.epam.project.service.impl.TicketServiceImpl}
 *
 * @author Shpakova A.
 */

public interface TicketService {
   
    void updateQuantity(int ticketId, int quantity) throws ServiceException;

    Set<String> findDatesByUserId(int userId) throws ServiceException;

    List<Ticket> findTicketByUserIdAndTicketDateAndEventType(int userId, String ticketDate, String eventType) throws ServiceException;

    void deleteTicketByUserId(int userId) throws ServiceException;

    void insertTicket(Ticket ticket) throws ServiceException;

    void deleteTicket(int ticketId) throws ServiceException;

    int totalPriceByUserIdAndTicketDate(int userId, String ticketDate) throws ServiceException;


}
