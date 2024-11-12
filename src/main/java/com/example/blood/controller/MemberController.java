package com.example.blood.controller;

import com.example.blood.dto.MemberDto;
import com.example.blood.dto.RequestMemberDto;
import com.example.blood.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping
    public ResponseEntity<String> addMember(@RequestBody RequestMemberDto requestMemberDto) {
        try {
            memberService.addMember(requestMemberDto);
            return new ResponseEntity<>("successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping(params = "memberId")
    public ResponseEntity<String> updateMember(
            @RequestParam String memberId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String birth,
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) String bloodType,
            @RequestParam(required = false) String phoneNumber,
            @RequestParam(required = false) String address) {
        try {
            memberService.updateMember(memberId, name, birth, gender, bloodType, phoneNumber, address);
            return new ResponseEntity<>("Successfully updated.", HttpStatus.OK); //204
        } catch (Exception e) {
            return new ResponseEntity<>("Failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
