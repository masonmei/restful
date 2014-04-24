package org.personal.mason.rest.example.model;

import org.personal.mason.rest.example.event.domain.OrderDetails;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: domei
 * Date: 4/24/14
 * Time: 7:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class InternalOrder {
    private final Date dateTimeOfSubmission;
    private Map<String, Integer> orderItems;
    private final Serializable key;
    private Customer customer;

    private OrderStatus status;
    private List<OrderStatus> statusHistory;

    public InternalOrder(final Date dateTimeOfSubmission) {
        this.key = UUID.randomUUID().toString();
        this.dateTimeOfSubmission = dateTimeOfSubmission;
        statusHistory = new ArrayList<OrderStatus>();
    }

    public void addStatus(OrderStatus newStatus) {
        statusHistory.add(newStatus);
        status = newStatus;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public Date getDateTimeOfSubmission() {
        return dateTimeOfSubmission;
    }

    public Serializable getKey() {
        return key;
    }

    public void setOrderItems(Map<String, Integer> orderItems) {
        if (orderItems == null) {
            this.orderItems = Collections.emptyMap();
        } else {
            this.orderItems = Collections.unmodifiableMap(orderItems);
        }
    }

    public Map<String, Integer> getOrderItems() {
        return orderItems;
    }

    public boolean canBeDeleted() {
        return true;
    }

    public OrderDetails toOrderDetails() {
        OrderDetails details = new OrderDetails();
        BeanUtils.copyProperties(this, details);
        return details;
    }

    public static InternalOrder fromOrderDetails(OrderDetails orderDetails) {
        InternalOrder order = new InternalOrder(orderDetails.getDateTimeOfSubmission());
        BeanUtils.copyProperties(orderDetails, order);
        return order;
    }
}
