package org.personal.mason.rest.controller;

import org.personal.mason.rest.core.event.generic.GenericCreateEvent;
import org.personal.mason.rest.core.event.generic.GenericCreatedEvent;
import org.personal.mason.rest.core.event.generic.GenericDeleteEvent;
import org.personal.mason.rest.core.event.generic.GenericDeletedEvent;
import org.personal.mason.rest.domain.Order;
import org.personal.mason.rest.example.event.DeleteOrderEvent;
import org.personal.mason.rest.example.event.OrderCreatedEvent;
import org.personal.mason.rest.example.event.OrderDeletedEvent;
import org.personal.mason.rest.example.event.domain.OrderDetails;
import org.personal.mason.rest.example.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: domei
 * Date: 4/24/14
 * Time: 6:01 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/aggregators/orders")
public class OrderCommandsController {
    private static Logger LOG = LoggerFactory.getLogger(OrderCommandsController.class);

    @Autowired
    private OrderService orderService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Order> createOrder(@RequestBody Order order, UriComponentsBuilder builder) {

        GenericCreatedEvent<OrderDetails> orderCreated = orderService.createOrder(new GenericCreateEvent<OrderDetails>(order.toOrderDetails()));

        Order newOrder = Order.fromOrderDetails(orderCreated.getObject());

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                builder.path("/aggregators/orders/{id}")
                        .buildAndExpand(orderCreated.getNewObjectKey().toString()).toUri());

        return new ResponseEntity<Order>(newOrder, headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Order> cancelOrder(@PathVariable Serializable id) {

        GenericDeletedEvent<OrderDetails> orderDeleted = orderService.deleteOrder(new GenericDeleteEvent(id));

        if (!orderDeleted.isEntityFound()) {
            return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
        }

        Order order = Order.fromOrderDetails(orderDeleted.getObject());

        if (orderDeleted.isDeletionCompleted()) {
            return new ResponseEntity<Order>(order, HttpStatus.OK);
        }

        return new ResponseEntity<Order>(order, HttpStatus.FORBIDDEN);
    }
}
