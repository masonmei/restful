package org.personal.mason.rest.controller;

import org.personal.mason.rest.example.model.PaymentDetails;
import org.personal.mason.rest.example.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 * User: domei
 * Date: 4/24/14
 * Time: 6:31 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/aggregators/orders/{id}/paymentdetails")
public class OrderPaymentDetailsController {
    private OrderService orderService;

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public
    @ResponseBody
    PaymentDetails getPaymentDetails(@PathVariable String id) {
        //TODO obtain the order
        //TODO ensure payment details mapping is correct

        return null;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public
    @ResponseBody
    PaymentDetails updatePaymentDetails(@PathVariable String id) {
        //TODO use a command object?
        //TODO obtain the order
        //TODO update the order payment details.
        //TODO ensure payment details mapping is correct

        return null;
    }
}
