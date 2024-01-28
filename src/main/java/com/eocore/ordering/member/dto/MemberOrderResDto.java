package com.eocore.ordering.member.dto;

import com.eocore.ordering.ordering.dto.OrderingDto;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class MemberOrderResDto
{
    private Long id;
    private String name;
    private List<OrderingDto> orderings;
}
