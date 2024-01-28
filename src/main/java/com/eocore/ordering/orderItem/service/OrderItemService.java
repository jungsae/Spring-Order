package com.eocore.ordering.orderItem.service;

import com.eocore.ordering.orderItem.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService
{
    private final OrderItemRepository orderItemRepository;
    @Autowired
    public OrderItemService(OrderItemRepository orderItemRepository)
    {
        this.orderItemRepository = orderItemRepository;
    }
}
