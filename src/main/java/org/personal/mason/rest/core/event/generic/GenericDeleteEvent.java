package org.personal.mason.rest.core.event.generic;

import org.personal.mason.rest.core.event.DeleteEvent;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: domei
 * Date: 4/24/14
 * Time: 4:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class GenericDeleteEvent<ID extends Serializable> extends DeleteEvent {

    private final ID key;

    public GenericDeleteEvent(ID key) {
        this.key = key;
    }

    public ID getKey() {
        return key;
    }
}
