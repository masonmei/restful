package org.personal.mason.rest.core.event.generic;

import org.personal.mason.rest.core.event.ReadEvent;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: domei
 * Date: 4/24/14
 * Time: 5:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class GenericReadEvent<T> extends ReadEvent {

    private Serializable key;
    private T object;

    private GenericReadEvent(Serializable key) {
        this.key = key;
    }

    public GenericReadEvent(Serializable key, T object) {
        this.key = key;
        this.object = object;
    }

    public Serializable getKey() {
        return key;
    }

    public T getObject() {
        return object;
    }

    public static <T> GenericReadEvent<T> notFound(Serializable key){
        GenericReadEvent<T> event = new GenericReadEvent<T>(key);
        event.entityFound = false;
        return event;
    }
}
