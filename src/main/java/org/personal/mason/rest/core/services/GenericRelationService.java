package org.personal.mason.rest.core.services;

import org.personal.mason.rest.core.event.generic.*;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: domei
 * Date: 4/25/14
 * Time: 5:46 PM
 * To change this template use File | Settings | File Templates.
 */
public interface GenericRelationService<T, R, ID extends Serializable> {
    GenericDeletedEvent<R,ID> deleteRelationObject(GenericDeleteEvent<ID> deleteEvent);

    GenericUpdatedEvent<R,ID> partialUpdateRelationObject(GenericUpdateEvent<R, ID> updateEvent);

    GenericUpdatedEvent<R,ID> updateRelationObject(GenericUpdateEvent<R, ID> updateEvent);

    GenericCreatedEvent<R,ID> createRelationObject(GenericCreateEvent<R> createEvent);

    GenericReadAllEvent<R> readAllRelationObjects(GenericRequestReadAllEvent readAllEvent);

    GenericReadEvent<R,ID> readRelationObject(GenericRequestReadEvent<ID> readEvent);
}
