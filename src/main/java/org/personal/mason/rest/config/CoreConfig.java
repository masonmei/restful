package org.personal.mason.rest.config;

import org.personal.mason.rest.example.model.InternalOrder;
import org.personal.mason.rest.example.repository.OrdersMemoryRepository;
import org.personal.mason.rest.example.repository.OrdersRepository;
import org.personal.mason.rest.example.service.OrderEventHandler;
import org.personal.mason.rest.example.service.OrderService;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: domei
 * Date: 4/24/14
 * Time: 5:44 PM
 * To change this template use File | Settings | File Templates.
 */

@Component
public class CoreConfig {

    @Bean
    public OrderService createService(OrdersRepository repo) {
        return new OrderEventHandler(repo);
    }

    @Bean
    public OrdersRepository createRepo() {
        return new OrdersMemoryRepository(new HashMap<Serializable, InternalOrder>());
    }
}
