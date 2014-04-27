package org.personal.mason.rest.core.services;

import org.personal.mason.rest.core.event.generic.*;
import org.personal.mason.rest.core.event.relation.*;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: domei
 * Date: 4/25/14
 * Time: 5:46 PM
 * To change this template use File | Settings | File Templates.
 */
public interface GenericRelationService<T, R, ID extends Serializable, RID extends Serializable> {

    GenericRelationReadEvent<T, R, ID, RID> readRelationObject(GenericRelationRequestReadEvent<T, R, ID, RID> requestReadEvent);

    GenericRelationReadAllEvent<T,R,ID> readAllRelationObjects(GenericRelationRequestReadAllEvent<T, R, ID> requestReadAllEvent);

    GenericRelationCreatedEvent<T,R,ID,RID> createRelationObject(GenericRelationCreateEvent<T, R, ID> createEvent);

    GenericRelationUpdatedEvent<T,R,ID,RID> updateRelationObject(GenericRelationUpdateEvent<T, R, ID, RID> updateEvent);

    GenericRelationUpdatedEvent<T,R,ID,RID> partialUpdateRelationObject(GenericRelationUpdateEvent<T, R, ID, RID> updateEvent);

    GenericRelationDeletedEvent<T,R,ID,RID> deleteRelationObject(GenericRelationDeleteEvent<T, R, ID, RID> deleteEvent);
}
