package com.eocore.ordering.orderItem.controller;

import com.eocore.ordering.orderItem.service.OrderItemService;
import com.eocore.ordering.ordering.service.OrderingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orderItems")
public class OrderItemController
{
    private final OrderItemService orderingService;
    @Autowired
    public OrderItemController(OrderItemService orderingService)
    {
        this.orderingService = orderingService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> orderItemsByOrderId(@PathVariable Long id)
    {
        return new ResponseEntity<>(orderingService.findByOrderId(id),HttpStatus.OK);
    }
}
