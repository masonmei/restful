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
public class GenericCreatedEvent<T> extends CreatedEvent {

    private final Serializable newObjectKey;
    private final T object;

    public GenericCreatedEvent(Serializable newObjectKey, T object) {
        this.newObjectKey = newObjectKey;
        this.object = object;
    }

    public Serializable getNewObjectKey() {
        return newObjectKey;
    }

    public T getObject() {
        return object;
    }
}
