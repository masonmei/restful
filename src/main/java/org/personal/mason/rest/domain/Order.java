package org.personal.mason.rest.domain;

import org.personal.mason.rest.controller.OrderQueriesController;
import org.personal.mason.rest.example.event.domain.OrderDetails;
import org.springframework.hateoas.ResourceSupport;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created with IntelliJ IDEA.
 * User: domei
 * Date: 4/24/14
 * Time: 6:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class Order extends ResourceSupport implements Serializable {
    private Date dateTimeOfSubmission;
    private Map<String, Integer> items;
    private Serializable key;

    public Date getDateTimeOfSubmission() {
        return dateTimeOfSubmission;
    }

    public Map<String, Integer> getItems() {
        return items;
    }

    public Serializable getKey() {
        return key;
    }

    public void setDateTimeOfSubmission(Date dateTimeOfSubmission) {
        this.dateTimeOfSubmission = dateTimeOfSubmission;
    }

    public void setItems(Map<String, Integer> items) {
        if (items == null) {
            this.items = Collections.emptyMap();
        } else {
            this.items = Collections.unmodifiableMap(items);
        }
    }

    public void setKey(Serializable key) {
        this.key = key;
    }

    public OrderDetails toOrderDetails() {
        OrderDetails details = new OrderDetails();

        details.setOrderItems(items);
        details.setKey(key);
        details.setDateTimeOfSubmission(dateTimeOfSubmission);

        return details;
    }

    // {!begin fromOrderDetails}
    public static Order fromOrderDetails(OrderDetails orderDetails) {
        Order order = new Order();

        order.dateTimeOfSubmission = orderDetails.getDateTimeOfSubmission();
        order.key = orderDetails.getKey();
        order.setItems(orderDetails.getOrderItems());

        //TODOCUMENT.  Adding the library, the above extends ResourceSupport and
        //this section is all that is actually needed in our model to add hateoas support.

        //Much of the rest of the framework is helping deal with the blending of domains that happens in many spring apps
        //We have explicitly avoided that.
        // {!begin selfRel}
        order.add(linkTo(OrderQueriesController.class).slash(order.key).withSelfRel());
        // {!end selfRel}
        // {!begin status}
        order.add(linkTo(OrderQueriesController.class).slash(order.key).slash("status").withRel("InternalOrder Status"));
        // {!end status}
        order.add(linkTo(OrderQueriesController.class).slash(order.key).slash("paymentdetails").withRel("Payment Details"));

        return order;
    }
    // {!end fromOrderDetails}
}
