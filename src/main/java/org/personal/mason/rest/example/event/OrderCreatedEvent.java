package org.personal.mason.rest.example.event;

import org.personal.mason.rest.core.event.generic.GenericCreatedEvent;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: domei
 * Date: 4/24/14
 * Time: 5:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class OrderCreatedEvent extends GenericCreatedEvent<OrderDetails> {
    public OrderCreatedEvent(Serializable newObjectKey, OrderDetails object) {
        super(newObjectKey, object);
    }
}
