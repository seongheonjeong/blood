package com.example.blood.service;

import com.example.blood.domain.Member;
import com.example.blood.dto.MemberDto;
import com.example.blood.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<MemberDto> getAllMembers() {
        return memberRepository.findAll().stream()
                .map(this::convertDto)
                .collect(Collectors.toList());
    }

    private MemberDto convertDto(Member member) {
        return new MemberDto(
                member.getMemberId(),
                member.getName(),
                member.getBirth(),
                member.getGender(),
                member.getBloodType(),
                member.getPhoneNumber(),
                member.getAddress(),
                member.getDonationCount(),
                member.getFirstDonationDate(),
                member.getLastDonationDate()
        );
    }

    public List<MemberDto> getMembersByMemberName(String memberName) {
        return memberRepository.findByName(memberName).stream()
                .map(this::convertDto)
                .collect(Collectors.toList());
    }

    public List<MemberDto> getMembersByPhoneNumber(String phoneNumber) {
        return memberRepository.findByPhoneNumber(phoneNumber).stream()
                .map(this::convertDto)
                .collect(Collectors.toList());
    }
}
