package org.personal.mason.rest.core.event.relation;

import org.personal.mason.rest.core.event.CreatedEvent;

import java.io.Serializable;

/**
 * Created by mason on 4/26/14.
 */
public class GenericRelationCreatedEvent<T, R, ID extends Serializable, RID extends Serializable> extends CreatedEvent {

    private final T object;
    private final ID objectKey;

    private final R relationObject;
    private final RID relationObjectKey;

    public GenericRelationCreatedEvent(T object, ID objectKey, R relationObject, RID relationObjectKey) {
        this.object = object;
        this.objectKey = objectKey;
        this.relationObject = relationObject;
        this.relationObjectKey = relationObjectKey;
    }

    public T getObject() {
        return object;
    }

    public ID getObjectKey() {
        return objectKey;
    }

    public R getRelationObject() {
        return relationObject;
    }

    public RID getRelationObjectKey() {
        return relationObjectKey;
    }
}