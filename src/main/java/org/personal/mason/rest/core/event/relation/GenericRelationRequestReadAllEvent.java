package org.personal.mason.rest.core.event.relation;

import org.personal.mason.rest.core.event.RequestReadEvent;

import java.io.Serializable;

/**
 * Created by mason on 4/26/14.
 */
public class GenericRelationRequestReadAllEvent<T, R, ID extends Serializable> extends RequestReadEvent {
    private ID objectKey;
    private T object;

    public GenericRelationRequestReadAllEvent(ID objectKey, T object) {
        this.objectKey = objectKey;
        this.object = object;
    }

    public ID getObjectKey() {
        return objectKey;
    }

    public T getObject() {
        return object;
    }
}
