package com.eocore.ordering.orderItem.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OrderItemsResDto
{
    private String name;
    private List<ItemDto> items = new ArrayList<>();
    @Data
    public static class ItemDto
    {
        private int count;
        private int price;
        private int totalPrice;
    }
}
