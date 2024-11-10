package com.example.blood.controller;

import com.example.blood.dto.MemberDto;
import com.example.blood.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public ResponseEntity<List<MemberDto>> getAllMembers() {
        List<MemberDto> memberDtoList = memberService.getAllMembers();
        if (memberDtoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(memberDtoList, HttpStatus.OK);
    }

    @GetMapping(params = "memberName")
    public ResponseEntity<List<MemberDto>> getMembersByMemberName(@RequestParam String memberName) {
        List<MemberDto> memberDtoList = memberService.getMembersByMemberName(memberName);
        if (memberDtoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(memberDtoList, HttpStatus.OK);
    }

    @GetMapping(params = "phoneNumber")
    public ResponseEntity<List<MemberDto>> getMembersByPhoneNumber(@RequestParam String phoneNumber) {
        List<MemberDto> memberDtoList = memberService.getMembersByPhoneNumber(phoneNumber);
        if (memberDtoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(memberDtoList, HttpStatus.OK);
    }
}
