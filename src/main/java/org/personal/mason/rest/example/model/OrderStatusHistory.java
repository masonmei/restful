package org.personal.mason.rest.example.model;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: domei
 * Date: 4/24/14
 * Time: 7:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class OrderStatusHistory {
    private final Set<OrderStatus> orderStatuses;

    public OrderStatusHistory(final Set<OrderStatus> orderStatuses) {
        this.orderStatuses = orderStatuses;
    }

    public void addStatus(final OrderStatus orderStatus) {
        this.orderStatuses.add(orderStatus);
    }
}
