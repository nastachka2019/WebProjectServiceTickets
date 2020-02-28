package by.epam.project.service.impl;

import by.epam.project.dao.EventTypeDao;
import by.epam.project.dao.impl.EventTypeDaoImpl;

import by.epam.project.entity.EventType;
import by.epam.project.exception.ConnectionPoolException;
import by.epam.project.exception.DaoException;
import by.epam.project.exception.ServiceException;
import by.epam.project.service.EventTypeService;

/**
 * Класс, реализующий EventTypeService
 *
 * @author Shpakova A.
 */

public class EventTypeServiceImpl implements EventTypeService {
    private EventTypeDao eventTypeDao;

    public EventTypeServiceImpl() {                  //если нужно будет вызвать м-ды из др. классов
        eventTypeDao= new EventTypeDaoImpl();
    }


    @Override
    public int findIdByEventType(String eventType) throws ServiceException {
        try {
            return eventTypeDao.findIdByEventType(eventType);
        } catch (DaoException  e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public EventType findEventTypeById(int eventTypeId) throws ServiceException {
        try {
            return eventTypeDao.findById(eventTypeId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
