package org.personal.mason.rest.example.event;

import org.personal.mason.rest.core.event.generic.GenericReadAllEvent;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: domei
 * Date: 4/24/14
 * Time: 5:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class AllOrderEvent extends GenericReadAllEvent<OrderDetails> {

    public AllOrderEvent(List<OrderDetails> readableObjects) {
        super(readableObjects);
    }
}
