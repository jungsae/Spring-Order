package com.eocore.ordering.orderItem.service;

import com.eocore.ordering.orderItem.domain.OrderItem;
import com.eocore.ordering.orderItem.dto.OrderItemsResDto;
import com.eocore.ordering.orderItem.repository.OrderItemRepository;
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
        for (OrderItem orderItem : orderItemRepository.findByOrdering_Id(id))
        {
            OrderItemsResDto orderItemsResDto = new OrderItemsResDto();
            orderItemsResDto.setName(orderItem.getItem().getName());

            List<OrderItemsResDto.ItemDto> itemDtos = new ArrayList<>();
            OrderItemsResDto.ItemDto itemDto = new OrderItemsResDto.ItemDto();
            itemDto.setCount(orderItem.getQuantity());
            itemDto.setPrice(orderItem.getItem().getPrice());
            itemDto.setTotalPrice(orderItem.getQuantity() * orderItem.getItem().getPrice());
            itemDtos.add(itemDto);

            orderItemsResDto.setItems(itemDtos);
            orderItemsResDtos.add(orderItemsResDto);
            orderItemsResDto.setItems(itemDtos);
        }
        return orderItemsResDtos;
    }
}
