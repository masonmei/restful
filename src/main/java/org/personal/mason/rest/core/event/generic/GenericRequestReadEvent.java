package org.personal.mason.rest.core.event.generic;

import org.personal.mason.rest.core.event.RequestReadEvent;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: domei
 * Date: 4/24/14
 * Time: 5:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class GenericRequestReadEvent<T> extends RequestReadEvent {

    private Serializable key;

    public GenericRequestReadEvent(Serializable key) {
        this.key = key;
    }

    public Serializable getKey() {
        return key;
    }
}
