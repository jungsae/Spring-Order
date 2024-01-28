package com.eocore.ordering.member.service;

import com.eocore.ordering.member.domain.Member;
import com.eocore.ordering.member.domain.Role;
import com.eocore.ordering.member.dto.MemberDetailResDto;
import com.eocore.ordering.member.dto.MemberListResDto;
import com.eocore.ordering.member.dto.MemberOrderResDto;
import com.eocore.ordering.member.dto.MemberSaveReqDto;
import com.eocore.ordering.member.repository.MemberRepository;
import com.eocore.ordering.ordering.domain.Ordering;
import com.eocore.ordering.ordering.dto.OrderingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService
{
    private final MemberRepository memberRepository;
    @Autowired
    public MemberService(MemberRepository memberRepository)
    {
        this.memberRepository = memberRepository;
    }

    public Member findById(Long id)
    {
        return memberRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("없는 사람"));
    }

    public List<MemberListResDto> findAll()
    {
        List<Member> members = memberRepository.findAll();
        List<MemberListResDto> memberListResDtos = new ArrayList<>();

        for (Member member: members)
        {
            MemberListResDto tempMem = MemberListResDto.builder()
                    .id(member.getId())
                    .name(member.getName())
                    .email(member.getEmail())
                    .build();
            memberListResDtos.add(tempMem);
        }
        return memberListResDtos;
    }

    public Member signUp(MemberSaveReqDto memberSaveReqDto) throws  IllegalArgumentException
    {
        Optional<Member> check = memberRepository.findByEmail(memberSaveReqDto.getEmail());
        if (check.isPresent()) throw new IllegalArgumentException("중복 이메일");

        Member member = Member.builder()
                .name(memberSaveReqDto.getName())
                .email(memberSaveReqDto.getEmail())
                .password(memberSaveReqDto.getPassword())
                .role(Role.valueOf(memberSaveReqDto.getRole()))
                .address(memberSaveReqDto.getAddress())
                .build();

        return memberRepository.save(member);
    }
    public MemberDetailResDto memberDetail(Long id)
    {
        Member member = memberRepository.findById(id).orElseThrow(() ->new EntityNotFoundException("없는 유저"));

        List<OrderingDto> orderingDtos = new ArrayList<>();
        for (Ordering ordering : member.getOrderings())
        {
            OrderingDto orderingDto = OrderingDto.builder()
                    .id(ordering.getId())
                    .orderStatus(ordering.getOrderStatus())
                    .createdTime(ordering.getCreatedTime())
                    .build();
            orderingDtos.add(orderingDto);
        }

        return MemberDetailResDto.builder()
                .id(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .password(member.getPassword())
                .role(String.valueOf(member.getRole()))
                .orderings(orderingDtos)
                .createdTime(member.getCreatedTime())
                .build();
    }
    public MemberOrderResDto ordersByMember(Long id)
    {
        Member member = this.findById(id);
        List<OrderingDto> orderingDtos = new ArrayList<>();
        for (Ordering ordering: member.getOrderings())
        {
            OrderingDto orderingDto = OrderingDto.builder()
                    .id(ordering.getId())
                    .orderStatus(ordering.getOrderStatus())
                    .createdTime(ordering.getCreatedTime())
                    .build();
            orderingDtos.add(orderingDto);
        }

        return MemberOrderResDto.builder()
                .id(member.getId())
                .name(member.getName())
                .orderings(orderingDtos)
                .build();
    }
}
