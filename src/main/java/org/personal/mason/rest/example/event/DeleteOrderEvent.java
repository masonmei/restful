package org.personal.mason.rest.example.event;

import org.personal.mason.rest.core.event.generic.GenericDeleteEvent;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: domei
 * Date: 4/24/14
 * Time: 5:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class DeleteOrderEvent extends GenericDeleteEvent<OrderDetails> {

    public DeleteOrderEvent(Serializable objectKey) {
        super(objectKey);
    }
}
