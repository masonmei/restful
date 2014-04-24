package org.personal.mason.rest.example.event.domain;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: domei
 * Date: 4/24/14
 * Time: 7:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class OrderDetails {
    private Serializable key;
    private Date dateTimeOfSubmission;
    private Map<String, Integer> orderItems;

    public OrderDetails() {
        key = null;
    }

    public OrderDetails(Serializable key) {
        this.key = key;
    }

    public Date getDateTimeOfSubmission() {
        return this.dateTimeOfSubmission;
    }

    public void setDateTimeOfSubmission(Date dateTimeOfSubmission) {
        this.dateTimeOfSubmission = dateTimeOfSubmission;
    }

    public Map<String, Integer> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Map<String, Integer> orderItems) {
        if (orderItems == null) {
            this.orderItems = Collections.emptyMap();
        } else {
            this.orderItems = Collections.unmodifiableMap(orderItems);
        }
    }

    public Serializable getKey() {
        return key;
    }

    public void setKey(Serializable key) {
        this.key = key;
    }
}
