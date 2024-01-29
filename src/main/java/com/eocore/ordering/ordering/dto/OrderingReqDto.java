package com.eocore.ordering.ordering.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderingReqDto
{
    private Long memberId;
    private List<OrderItemDto> orderItems;
    @Data
    public static class OrderItemDto
    {
        private Long itemId;
        private int count;
    }
}
