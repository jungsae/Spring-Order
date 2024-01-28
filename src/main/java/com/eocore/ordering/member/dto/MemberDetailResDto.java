package com.eocore.ordering.member.dto;

import com.eocore.ordering.ordering.dto.OrderingDto;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class MemberDetailResDto
{
    private Long id;
    private String email;
    private String name;
    private String password;
    private String role;
    private LocalDateTime createdTime;
    private List<OrderingDto> orderings;
}
