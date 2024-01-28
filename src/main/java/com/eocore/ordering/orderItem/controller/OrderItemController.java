package com.eocore.ordering.orderItem.controller;

import com.eocore.ordering.ordering.service.OrderingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class OrderItemController
{
    private final OrderingService orderingService;
    @Autowired
    public OrderItemController(OrderingService orderingService)
    {
        this.orderingService = orderingService;
    }
}
