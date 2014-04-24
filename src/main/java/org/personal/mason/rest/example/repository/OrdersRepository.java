package org.personal.mason.rest.example.repository;

import org.personal.mason.rest.example.model.InternalOrder;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: domei
 * Date: 4/24/14
 * Time: 7:09 PM
 * To change this template use File | Settings | File Templates.
 */
public interface OrdersRepository {
    InternalOrder save(InternalOrder order);
    void delete(Serializable key);
    InternalOrder findById(Serializable key);
    List<InternalOrder> findAll();
}
