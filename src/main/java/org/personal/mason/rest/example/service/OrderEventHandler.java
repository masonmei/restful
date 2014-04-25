package org.personal.mason.rest.example.service;

import org.personal.mason.rest.core.event.generic.*;
import org.personal.mason.rest.example.event.domain.OrderDetails;
import org.personal.mason.rest.example.model.InternalOrder;
import org.personal.mason.rest.example.model.OrderStatus;
import org.personal.mason.rest.example.model.OrderStatusDetails;
import org.personal.mason.rest.example.repository.OrdersRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: domei
 * Date: 4/24/14
 * Time: 7:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class OrderEventHandler implements OrderService {
    private final OrdersRepository ordersRepository;

    public OrderEventHandler(final OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    @Override
    public GenericReadAllEvent<OrderDetails> requestAllOrders(GenericRequestReadAllEvent<OrderDetails> requestAllCurrentOrdersEvent) {
        List<OrderDetails> generatedDetails = new ArrayList<OrderDetails>();
        for (InternalOrder order : ordersRepository.findAll()) {
            generatedDetails.add(order.toOrderDetails());
        }
        return new GenericReadAllEvent<OrderDetails>(generatedDetails);
    }

    @Override
    public GenericReadEvent<OrderDetails> requestOrderDetails(GenericRequestReadEvent<OrderDetails> requestOrderDetailsEvent) {
        InternalOrder order = ordersRepository.findById(requestOrderDetailsEvent.getKey());
        if (order == null) {
            return GenericReadEvent.notFound(requestOrderDetailsEvent.getKey());
        }

        return new GenericReadEvent<OrderDetails>(requestOrderDetailsEvent.getKey(), order.toOrderDetails());
    }

    @Override
    public GenericReadEvent<OrderStatusDetails> requestOrderStatus(GenericRequestReadEvent<OrderStatusDetails> requestOrderStatusEvent) {
        InternalOrder order = ordersRepository.findById(requestOrderStatusEvent.getKey());

        if (order == null) {
            return GenericReadEvent.notFound(requestOrderStatusEvent.getKey());
        }

        return new GenericReadEvent<OrderStatusDetails>(requestOrderStatusEvent.getKey(), order.getStatus().toStatusDetails());
    }

    @Override
    public GenericCreatedEvent<OrderDetails> createOrder(GenericCreateEvent<OrderDetails> event) {
        InternalOrder order = InternalOrder.fromOrderDetails(event.getObject());
        order.addStatus(new OrderStatus(new Date(), "InternalOrder Created"));
        order = ordersRepository.save(order);
        return new GenericCreatedEvent<OrderDetails>(order.getKey(), order.toOrderDetails());
    }

    @Override
    public GenericUpdatedEvent<OrderDetails> setOrderPayment(GenericUpdateEvent<OrderDetails> setOrderPaymentEvent) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public GenericDeletedEvent<OrderDetails> deleteOrder(GenericDeleteEvent<OrderDetails> deleteOrderEvent) {
        InternalOrder order = ordersRepository.findById(deleteOrderEvent.getKey());

        if (order == null) {
            return GenericDeletedEvent.notFound(deleteOrderEvent.getKey());
        }

        OrderDetails details = order.toOrderDetails();

        //TODOCUMENT This contains some specific domain logic, not exposed to the outside world, and not part of the
        //persistence rules.

        if (!order.canBeDeleted()) {
            return GenericDeletedEvent.deletionForbidden(deleteOrderEvent.getKey(), details);
        }

        ordersRepository.delete(deleteOrderEvent.getKey());
        return new GenericDeletedEvent<OrderDetails>(deleteOrderEvent.getKey(), details);
    }
}
