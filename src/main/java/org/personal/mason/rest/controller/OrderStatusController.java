package org.personal.mason.rest.controller;

import org.personal.mason.rest.core.event.generic.GenericReadEvent;
import org.personal.mason.rest.core.event.generic.GenericRequestReadEvent;
import org.personal.mason.rest.domain.OrderStatus;
import org.personal.mason.rest.example.model.OrderStatusDetails;
import org.personal.mason.rest.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: domei
 * Date: 4/24/14
 * Time: 6:37 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/aggregators/orders/{id}/status")
public class OrderStatusController {

    @Autowired
    private OrderService orderService;

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<OrderStatus> getOrderStatus(@PathVariable Serializable id) {

        GenericReadEvent<OrderStatusDetails> orderStatusEvent = orderService.requestOrderStatus(new GenericRequestReadEvent<OrderStatusDetails>(id));

        if (!orderStatusEvent.isEntityFound()) {
            return new ResponseEntity<OrderStatus>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<OrderStatus>(
                OrderStatus.fromOrderStatusDetails(orderStatusEvent.getKey(), orderStatusEvent.getObject()),
                HttpStatus.OK);
    }
}
