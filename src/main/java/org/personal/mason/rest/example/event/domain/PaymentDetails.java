package org.personal.mason.rest.example.event.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: domei
 * Date: 4/24/14
 * Time: 7:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class PaymentDetails {
    private Serializable key;
    private Date dateTimeOfSubmission;

    public PaymentDetails() {
        key = null;
    }

    public PaymentDetails(Serializable key) {
        this.key = key;
    }

    public Date getDateTimeOfSubmission() {
        return this.dateTimeOfSubmission;
    }

    public void setDateTimeOfSubmission(Date dateTimeOfSubmission) {
        this.dateTimeOfSubmission = dateTimeOfSubmission;
    }

    public Serializable getKey() {
        return key;
    }

    public void setKey(Serializable key) {
        this.key = key;
    }
}
