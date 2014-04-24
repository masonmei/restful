package org.personal.mason.rest.example.event.domain;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: domei
 * Date: 4/24/14
 * Time: 7:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class OrderStatus {
    private Serializable key;

    public OrderStatus() {
        key = null;
    }

    public OrderStatus(Serializable key) {
        this.key = key;
    }

    public Serializable getKey() {
        return key;
    }

    public void setKey(Serializable key) {
        this.key = key;
    }
}
