package org.personal.mason.rest.example.event;

import org.personal.mason.rest.core.event.generic.GenericReadEvent;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: domei
 * Date: 4/24/14
 * Time: 5:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class OrderDetailsEvent extends GenericReadEvent<OrderDetails> {
    public OrderDetailsEvent(Serializable key, OrderDetails object) {
        super(key, object);
    }
}
