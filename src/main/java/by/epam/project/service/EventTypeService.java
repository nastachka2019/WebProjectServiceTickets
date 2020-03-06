package by.epam.project.service;


import by.epam.project.entity.EventType;
import by.epam.project.exception.ServiceException;

/**
 * Interface for {@link by.epam.project.service.impl.EventTypeServiceImpl}
 *
 * @author Shpakova A.
 */


public interface EventTypeService {

    int findIdByEventType(String eventType) throws ServiceException;

    EventType findEventTypeById(int eventTypeId) throws ServiceException;
}


