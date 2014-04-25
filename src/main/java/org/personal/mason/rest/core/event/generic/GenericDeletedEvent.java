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
public class GenericDeletedEvent<T, ID extends Serializable> extends DeletedEvent {

    private ID key;
    private T object;
    private boolean deletionCompleted;

    private GenericDeletedEvent(ID key) {
        this.key = key;
    }

    public GenericDeletedEvent(ID key, T object) {
        this.key = key;
        this.object = object;
        this.deletionCompleted = true;
    }

    public ID getKey() {
        return key;
    }

    public T getObject() {
        return object;
    }

    public boolean isDeletionCompleted() {
        return deletionCompleted;
    }

    public static <T, ID extends Serializable> GenericDeletedEvent<T, ID> deletionForbidden(ID key, T object){
        GenericDeletedEvent<T, ID> event = new GenericDeletedEvent<T, ID>(key, object);
        event.entityFound = true;
        event.deletionCompleted = true;
        return event;
    }

    public static <T, ID extends Serializable> GenericDeletedEvent<T, ID> notFound(ID key){
        GenericDeletedEvent<T, ID> event = new GenericDeletedEvent<T, ID>(key);
        event.entityFound = false;
        return event;
    }
}
