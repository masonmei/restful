package org.personal.mason.rest.core.event.generic;

import org.personal.mason.rest.core.event.CreateEvent;

/**
 * Created with IntelliJ IDEA.
 * User: domei
 * Date: 4/24/14
 * Time: 4:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class GenericCreateEvent<T> extends CreateEvent {

    private T object;

    public GenericCreateEvent(T object) {
        this.object = object;
    }

    public T getObject() {
        return object;
    }
}
