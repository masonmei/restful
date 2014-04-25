package org.personal.mason.rest.example.service;

import org.personal.mason.rest.core.event.generic.*;
import org.personal.mason.rest.example.event.domain.OrderDetails;
import org.personal.mason.rest.example.model.OrderStatus;
import org.personal.mason.rest.example.model.OrderStatusDetails;

/**
 * Created with IntelliJ IDEA.
 * User: domei
 * Date: 4/24/14
 * Time: 6:03 PM
 * To change this template use File | Settings | File Templates.
 */
public interface OrderService {
    public GenericReadAllEvent<OrderDetails> requestAllOrders(GenericRequestReadAllEvent<OrderDetails> requestAllCurrentOrdersEvent);

    public GenericReadEvent<OrderDetails> requestOrderDetails(GenericRequestReadEvent<OrderDetails> requestOrderDetailsEvent);

    public GenericReadEvent<OrderStatusDetails> requestOrderStatus(GenericRequestReadEvent<OrderStatusDetails> requestOrderStatusEvent);

    public GenericCreatedEvent<OrderDetails> createOrder(GenericCreateEvent<OrderDetails> event);

    public GenericUpdatedEvent<OrderDetails> setOrderPayment(GenericUpdateEvent<OrderDetails> setOrderPaymentEvent);

    public GenericDeletedEvent<OrderDetails> deleteOrder(GenericDeleteEvent<OrderDetails> deleteOrderEvent);
}
