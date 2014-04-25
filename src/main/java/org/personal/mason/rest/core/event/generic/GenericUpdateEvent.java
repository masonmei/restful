package org.personal.mason.rest.core.event.generic;

import org.personal.mason.rest.core.event.UpdateEvent;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: domei
 * Date: 4/24/14
 * Time: 5:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class GenericUpdateEvent<T, ID extends Serializable> extends UpdateEvent {
    private ID key;
    private T object;

    public GenericUpdateEvent(ID key, T object) {
        this.key = key;
        this.object = object;
    }

    public ID getKey() {
        return key;
    }

    public T getObject() {
        return object;
    }
}
