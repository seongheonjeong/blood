package com.example.blood.service;

import com.example.blood.domain.Member;
import com.example.blood.dto.BloodDonationRankingDto;
import com.example.blood.dto.MemberDto;
import com.example.blood.dto.RequestMemberDto;
import com.example.blood.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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
    private Member convertToMember(RequestMemberDto requestMemberDto) {
        Member member = new Member();
        member.setName(requestMemberDto.getName());
        member.setBirth(requestMemberDto.getBirth());
        member.setGender(requestMemberDto.getGender());
        member.setBloodType(requestMemberDto.getBloodType());
        member.setPhoneNumber(requestMemberDto.getPhoneNumber());
        member.setAddress(requestMemberDto.getAddress());
        return member;
    }
    public List<BloodDonationRankingDto> getDonationRanking() {
        return memberRepository.findBloodDonationRanking().stream()
                .map(ranking -> new BloodDonationRankingDto(
                        (String) ranking[0], // 회원id
                        (String) ranking[1], // 이름
                        ranking[2] != null ? ((Number) ranking[2]).intValue() : 0, // 헌혈횟수
                        ((Number) ranking[3]).intValue()
                ))
                .collect(Collectors.toList());
    }

    public List<MemberDto> getMembersByMemberName(String memberName) {
        return memberRepository.findByName(memberName).stream()
                .map(this::convertDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void addMember(RequestMemberDto requestMemberDto) {
        Member member = convertToMember(requestMemberDto);
        memberRepository.save(member);
    }

    //이름, 생년월일, 성별, 혈액형, 휴대폰번호, 주소
    @Transactional
    public void updateMember(String memberId, String name, String birth, String gender, String bloodType, String phoneNumber, String address) {
        Member member = memberRepository.findFirstByMemberId(memberId).orElse(null);
        if (name != null) member.setName(name);
        if (birth != null) member.setBirth(LocalDate.parse(birth));
        if (gender != null) member.setGender(gender);
        if (bloodType != null) member.setBloodType(bloodType);
        if (phoneNumber != null) member.setPhoneNumber(phoneNumber);
        if (address != null) member.setAddress(address);
    }
}
