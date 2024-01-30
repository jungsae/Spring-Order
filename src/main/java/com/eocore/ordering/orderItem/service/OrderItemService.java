package com.eocore.ordering.orderItem.service;

import com.eocore.ordering.orderItem.domain.OrderItem;
import com.eocore.ordering.orderItem.dto.OrderItemsResDto;
import com.eocore.ordering.orderItem.repository.OrderItemRepository;
import com.eocore.ordering.ordering.domain.Ordering;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderItemService
{
    private final OrderItemRepository orderItemRepository;
    @Autowired
    public OrderItemService(OrderItemRepository orderItemRepository)
    {
        this.orderItemRepository = orderItemRepository;
    }
    public List<OrderItemsResDto> findByOrderId(Long id) {
        List<OrderItemsResDto> orderItemsResDtos = new ArrayList<>();
        System.out.println(id);
        System.out.println("출력: "+orderItemRepository.findByOrdering_Id(id));

        for (OrderItem orderItem : orderItemRepository.findByOrdering_Id(id))
        {
            OrderItemsResDto orderItemsResDto = new OrderItemsResDto();
            orderItemsResDto.setName(orderItem.getItem().getName());
            orderItemsResDto.setCount(orderItem.getQuantity());

            List<OrderItemsResDto.ItemDto> itemDtos = new ArrayList<>();
            OrderItemsResDto.ItemDto itemDto = new OrderItemsResDto.ItemDto();
            itemDto.setName(orderItem.getItem().getName());
            itemDto.setPrice(orderItem.getItem().getPrice());
            itemDtos.add(itemDto);

            orderItemsResDto.setItems(itemDtos);
            orderItemsResDtos.add(orderItemsResDto);
        }
        return orderItemsResDtos;
    }
}
