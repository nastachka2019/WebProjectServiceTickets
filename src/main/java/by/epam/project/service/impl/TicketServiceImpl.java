package by.epam.project.service.impl;

import by.epam.project.dao.TicketDao;
import by.epam.project.dao.impl.TicketDaoImpl;
import by.epam.project.entity.Ticket;
import by.epam.project.exception.DaoException;
import by.epam.project.exception.ServiceException;
import by.epam.project.service.EventTypeService;
import by.epam.project.service.TicketService;

import java.util.List;
import java.util.Set;

/**
 * This class implements {@link TicketService}
 *
 * @author Shpakova A.
 */

public class TicketServiceImpl implements TicketService {

    private TicketDao ticketDao;

    public TicketServiceImpl() {                 //если нужно будет вызвать м-ды из др. классов
        this.ticketDao = new TicketDaoImpl();
    }


    @Override
    public void updateQuantity(int ticketId, int quantity) throws ServiceException {
        try {
            ticketDao.updateQuantity(ticketId, quantity);
        } catch (DaoException  e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Set<String> findDatesByUserId(int userId) throws ServiceException {
        try {
            return ticketDao.findDatesByUserId(userId);
        } catch (DaoException  e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Ticket> findTicketByUserIdAndTicketDateAndEventType(int userId, String ticketDate, String eventType) throws ServiceException {
        try {
            return ticketDao.findTicketByUserIdAndTicketDateAndEventType(userId, ticketDate, eventType);
        } catch (DaoException  e) {
            throw new ServiceException(e);
        }
    }


    @Override
    public void deleteTicketByUserId(int userId) throws ServiceException {
        try {
            ticketDao.deleteTicketByUserId(userId);
        } catch (DaoException  e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void insertTicket(Ticket ticket) throws ServiceException {
        try {
            ticketDao.insert(ticket);
        } catch (DaoException  e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteTicket(int ticketId) throws ServiceException {
        try {
            ticketDao.delete(ticketId);
        } catch (DaoException  e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int totalPriceByUserIdAndTicketDate(int userId, String ticketDate) throws ServiceException {
        try {
            return ticketDao.totalPriceByUserIdAndTicketDate(userId, ticketDate);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
