package org.personal.mason.rest.example.event;

import org.personal.mason.rest.core.event.generic.GenericDeletedEvent;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: domei
 * Date: 4/24/14
 * Time: 5:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class OrderDeletedEvent extends GenericDeletedEvent<OrderDetails> {
    public OrderDeletedEvent(Serializable key, OrderDetails object) {
        super(key, object);
    }
}
