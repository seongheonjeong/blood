package com.example.blood.controller;


import com.example.blood.domain.Member;
import com.example.blood.dto.MemberDto;
import com.example.blood.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("member")
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    //전체조회
    @GetMapping
    public List<MemberDto> getAllMembers() {
        return memberService.getAllMembers();
    }
    //멤버이름으로
    @GetMapping(params = "memberName")
    public List<MemberDto> getMembersByMemberName(@RequestParam String memberName) {
        return memberService.getMembersByMemberName(memberName);
    }
    //휴대폰전화로
    @GetMapping(params = "phoneNumber")
    public List<MemberDto> getMembersByPhoneNumber(@RequestParam String phoneNumber) {
        return memberService.getMembersByPhoneNumber(phoneNumber);
    }
}
