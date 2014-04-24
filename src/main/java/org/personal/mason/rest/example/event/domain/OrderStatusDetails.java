package org.personal.mason.rest.example.event.domain;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: domei
 * Date: 4/24/14
 * Time: 7:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class OrderStatusDetails {
    private Date statusDate;
    private String status;

    public OrderStatusDetails(Date statusDate, String status) {
        this.status = status;
        this.statusDate = statusDate;
    }

    public Date getStatusDate() {
        return statusDate;
    }

    public String getStatus() {
        return status;
    }
}
