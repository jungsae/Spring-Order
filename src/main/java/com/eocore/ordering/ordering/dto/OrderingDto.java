package com.eocore.ordering.ordering.dto;

import com.eocore.ordering.orderItem.dto.OrderItemsResDto;
import com.eocore.ordering.ordering.domain.OrderStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class OrderingDto
{
    private Long id;
    private OrderStatus orderStatus;
    private LocalDateTime createdTime;
    private List<OrderItemsResDto> orderItemsResDtos;
}
