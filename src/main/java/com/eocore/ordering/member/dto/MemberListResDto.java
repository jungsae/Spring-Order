package com.eocore.ordering.member.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class MemberListResDto
{
    private Long id;
    private String name;
    private String email;
}
