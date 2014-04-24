package org.personal.mason.rest.example.model;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: domei
 * Date: 4/24/14
 * Time: 7:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class OrderStatus {
    private Date statusDate;
    private String status;

    public OrderStatus(final Date date, final String status) {
        this.status = status;
        this.statusDate = date;
    }

    public Date getStatusDate() {
        return statusDate;
    }

    public String getStatus() {
        return status;
    }

    public OrderStatusDetails toStatusDetails() {
        return new OrderStatusDetails(statusDate, status);
    }
}
