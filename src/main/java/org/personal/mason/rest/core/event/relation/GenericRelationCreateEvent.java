package org.personal.mason.rest.core.event.relation;

import org.personal.mason.rest.core.event.CreateEvent;

import java.io.Serializable;

/**
 * Created by mason on 4/26/14.
 */
public class GenericRelationCreateEvent<T, R, ID extends Serializable> extends CreateEvent {

    private T object;
    private ID objectKey;

    private R relationObject;

    public GenericRelationCreateEvent(ID objectKey, T object, R relationObject) {
        this.object = object;
        this.objectKey = objectKey;
        this.relationObject = relationObject;
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
}
