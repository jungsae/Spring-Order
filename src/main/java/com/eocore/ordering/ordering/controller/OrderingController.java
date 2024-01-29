package com.eocore.ordering.ordering.controller;

import com.eocore.ordering.ordering.dto.OrderingReqDto;
import com.eocore.ordering.ordering.service.OrderingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/new")
    public ResponseEntity<?> order(@RequestBody OrderingReqDto orderingReqDto)
    {
        orderingService.saveOrder(orderingReqDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
