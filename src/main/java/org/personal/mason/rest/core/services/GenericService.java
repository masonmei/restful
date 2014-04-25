package org.personal.mason.rest.core.services;

import org.personal.mason.rest.core.event.generic.*;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: domei
 * Date: 4/25/14
 * Time: 2:08 PM
 * To change this template use File | Settings | File Templates.
 */
public interface GenericService<T, ID extends Serializable> {
    GenericCreatedEvent<T, ID> createObject(GenericCreateEvent<T> createEvent);

    GenericDeletedEvent<T, ID> deleteObject(GenericDeleteEvent<ID> deleteEvent);

    GenericReadEvent<T, ID> readObject(GenericRequestReadEvent<ID> readEvent);

    GenericReadAllEvent<T> readAllObjects(GenericRequestReadAllEvent readAllEvent);

    GenericUpdatedEvent<T,ID> updateObject(GenericUpdateEvent<T, ID> updateEvent);

    GenericUpdatedEvent<T,ID> partialUpdateObject(GenericUpdateEvent<T, ID> updateEvent);
}
