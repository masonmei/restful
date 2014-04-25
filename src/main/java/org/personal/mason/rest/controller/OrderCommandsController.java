package org.personal.mason.rest.controller;

import org.personal.mason.rest.core.controller.GenericController;
import org.personal.mason.rest.core.services.GenericService;
import org.personal.mason.rest.domain.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * User: domei
 * Date: 4/25/14
 * Time: 4:35 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping(value = {"/aggregators/orders"})
public class OrderCommandsController extends GenericController<Order, String> {

    public OrderCommandsController(GenericService<Order, String> genericService) {
        super(genericService);
    }
}
