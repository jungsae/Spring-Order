package com.eocore.ordering.ordering.dto;

import com.eocore.ordering.ordering.domain.OrderStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Builder
public class OrderingDto
{
    private Long id;
    private OrderStatus orderStatus;
    private LocalDateTime createdTime;
}
