package org.personal.mason.rest.core.event.generic;

import org.personal.mason.rest.core.event.DeletedEvent;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: domei
 * Date: 4/24/14
 * Time: 4:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class GenericDeletedEvent<T> extends DeletedEvent {

    private Serializable key;
    private T object;
    private boolean deletionCompleted;

    private GenericDeletedEvent(Serializable key) {
        this.key = key;
    }

    public GenericDeletedEvent(Serializable key, T object) {
        this.key = key;
        this.object = object;
        this.deletionCompleted = true;
    }

    public Serializable getKey() {
        return key;
    }

    public T getObject() {
        return object;
    }

    public boolean isDeletionCompleted() {
        return deletionCompleted;
    }

    public static <T> GenericDeletedEvent<T> deletionForbidden(Serializable key, T object){
        GenericDeletedEvent<T> event = new GenericDeletedEvent<T>(key, object);
        event.entityFound = true;
        event.deletionCompleted = true;
        return event;
    }

    public static <T> GenericDeletedEvent<T> notFound(Serializable key){
        GenericDeletedEvent<T> event = new GenericDeletedEvent<T>(key);
        event.entityFound = false;
        return event;
    }
}
