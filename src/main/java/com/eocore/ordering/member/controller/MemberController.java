package com.eocore.ordering.member.controller;

import com.eocore.ordering.member.domain.Member;
import com.eocore.ordering.member.dto.MemberDetailResDto;
import com.eocore.ordering.member.dto.MemberListResDto;
import com.eocore.ordering.member.dto.MemberOrderResDto;
import com.eocore.ordering.member.dto.MemberSaveReqDto;
import com.eocore.ordering.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberController
{
    private final MemberService memberService;
    @Autowired
    public MemberController(MemberService memberService)
    {
        this.memberService = memberService;
    }

    @GetMapping("/list")
    public List<MemberListResDto> members()
    {
        return memberService.findAll();
    }

    @GetMapping("/{id}/orders")
    public ResponseEntity<?> ordersByMember(@PathVariable Long id)
    {
        try
        {
            MemberOrderResDto memberOrderResDto = memberService.ordersByMember(id);
            return new ResponseEntity<>(memberOrderResDto, HttpStatus.OK);
        }catch (EntityNotFoundException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/detail")
    public ResponseEntity<?> memberDetail(@PathVariable Long id)
    {
        try
        {
            MemberDetailResDto memberDetailResDto = memberService.memberDetail(id);
            return new ResponseEntity<>(memberDetailResDto, HttpStatus.OK);
        }catch (EntityNotFoundException e)
        {
            return new ResponseEntity<>(e.getMessage() ,HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/new")
    public ResponseEntity<?> signUp(@RequestBody MemberSaveReqDto memberSaveReqDto)
    {
        try
        {
            Member member = memberService.signUp(memberSaveReqDto);
            return new ResponseEntity<>(member.getName(),HttpStatus.CREATED);
        }
        catch (IllegalArgumentException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
}
