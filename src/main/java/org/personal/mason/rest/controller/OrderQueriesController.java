package org.personal.mason.rest.controller;

import org.personal.mason.rest.core.event.generic.GenericReadAllEvent;
import org.personal.mason.rest.core.event.generic.GenericReadEvent;
import org.personal.mason.rest.core.event.generic.GenericRequestReadAllEvent;
import org.personal.mason.rest.core.event.generic.GenericRequestReadEvent;
import org.personal.mason.rest.domain.Order;
import org.personal.mason.rest.example.event.OrderDetailsEvent;
import org.personal.mason.rest.example.event.domain.OrderDetails;
import org.personal.mason.rest.example.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: domei
 * Date: 4/24/14
 * Time: 6:33 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/aggregators/orders")
public class OrderQueriesController {

    private static Logger LOG = LoggerFactory.getLogger(OrderQueriesController.class);

    @Autowired
    private OrderService orderService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<Order>();
        for (OrderDetails detail : orderService.requestAllOrders(new GenericRequestReadAllEvent<OrderDetails>()).getReadableObjects()) {
            orders.add(Order.fromOrderDetails(detail));
        }
        return orders;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Order> viewOrder(@PathVariable Serializable id) {

        GenericReadEvent<OrderDetails> details = orderService.requestOrderDetails(new GenericRequestReadEvent<OrderDetails>(id));

        if (!details.isEntityFound()) {
            return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
        }

        Order order = Order.fromOrderDetails(details.getObject());

        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }
}
