package org.personal.mason.rest.domain;

import org.personal.mason.rest.controller.OrderQueriesController;
import org.personal.mason.rest.controller.OrderStatusController;
import org.personal.mason.rest.example.model.OrderStatusDetails;
import org.springframework.hateoas.ResourceSupport;

import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;
import java.util.Date;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created with IntelliJ IDEA.
 * User: domei
 * Date: 4/24/14
 * Time: 6:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class OrderStatus extends ResourceSupport {
    @XmlElement
    private Serializable orderId;

    @XmlElement
    private Date statusDate;

    @XmlElement
    private String status;

    public static OrderStatus fromOrderStatusDetails(Serializable key, OrderStatusDetails orderDetails) {
        OrderStatus status = new OrderStatus();

        status.orderId = key;
        status.status = orderDetails.getStatus();
        status.statusDate = orderDetails.getStatusDate();

        status.add(linkTo(OrderStatusController.class, key.toString()).withSelfRel());
        status.add(linkTo(OrderQueriesController.class).slash(key).withRel("InternalOrder"));

        return status;
    }

    public Serializable getOrderId() {
        return orderId;
    }

    public Date getStatusDate() {
        return statusDate;
    }

    public String getStatus() {
        return status;
    }
}
