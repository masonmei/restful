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
public class GenericReadEvent<T, ID extends Serializable> extends ReadEvent {

    private ID key;
    private T object;

    private GenericReadEvent(ID key) {
        this.key = key;
    }

    public GenericReadEvent(ID key, T object) {
        this.key = key;
        this.object = object;
    }

    public ID getKey() {
        return key;
    }

    public T getObject() {
        return object;
    }

    public static <T, ID extends Serializable> GenericReadEvent<T, ID> notFound(ID key){
        GenericReadEvent<T, ID> event = new GenericReadEvent<T, ID>(key);
        event.entityFound = false;
        return event;
    }
}
