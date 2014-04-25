package org.personal.mason.rest.core.event.generic;

import org.personal.mason.rest.core.event.CreatedEvent;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: domei
 * Date: 4/24/14
 * Time: 4:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class GenericCreatedEvent<T, ID extends Serializable> extends CreatedEvent {

    private final ID objectKey;
    private final T object;

    public GenericCreatedEvent(ID objectKey, T object) {
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
