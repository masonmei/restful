package org.personal.mason.rest.example.repository;

import org.personal.mason.rest.example.model.InternalOrder;

import java.io.Serializable;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: domei
 * Date: 4/24/14
 * Time: 7:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class OrdersMemoryRepository implements OrdersRepository {
    private Map<Serializable, InternalOrder> orders;

    public OrdersMemoryRepository(final Map<Serializable, InternalOrder> orders) {
        this.orders = Collections.unmodifiableMap(orders);
    }

    @Override
    public synchronized InternalOrder save(InternalOrder order) {
        Map<Serializable, InternalOrder> modifiableOrders = new HashMap<Serializable, InternalOrder>(orders);
        modifiableOrders.put(order.getKey(), order);
        this.orders = Collections.unmodifiableMap(modifiableOrders);

        return order;
    }

    @Override
    public void delete(Serializable key) {
        if (orders.containsKey(key)) {
            Map<Serializable, InternalOrder> modifiableOrders = new HashMap<Serializable, InternalOrder>(orders);
            modifiableOrders.remove(key);
            this.orders = Collections.unmodifiableMap(modifiableOrders);
        }
    }

    @Override
    public InternalOrder findById(Serializable key) {
        return orders.get(key);
    }

    @Override
    public List<InternalOrder> findAll() {
        return Collections.unmodifiableList(new ArrayList<InternalOrder>(orders.values()));
    }
}
