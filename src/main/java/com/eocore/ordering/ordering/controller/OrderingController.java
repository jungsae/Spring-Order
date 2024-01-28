package com.eocore.ordering.ordering.controller;

import com.eocore.ordering.ordering.service.OrderingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderingController
{
    private final OrderingService orderingService;
    @Autowired
    public OrderingController(OrderingService orderingService)
    {
        this.orderingService = orderingService;
    }

    @GetMapping("/list")
    public ResponseEntity<?> members()
    {
        return new ResponseEntity<>(orderingService.findAll(), HttpStatus.OK);
    }
}
