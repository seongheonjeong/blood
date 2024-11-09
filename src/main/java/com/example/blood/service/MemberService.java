package com.example.blood.service;

import com.example.blood.domain.Member;
import com.example.blood.domain.Reservation;
import com.example.blood.dto.MemberDto;
import com.example.blood.dto.ReservationDto;
import com.example.blood.repository.SpringDataJpaMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberService {
    private final SpringDataJpaMemberRepository springDataJpaMemberRepository;

    @Autowired
    public MemberService(SpringDataJpaMemberRepository springDataJpaMemberRepository) {
        this.springDataJpaMemberRepository = springDataJpaMemberRepository;
    }
    public List<MemberDto> getAllMembers() {
        return springDataJpaMemberRepository.findAll().stream()
                .map(this::memberDto)
                .collect(Collectors.toList());
    }

    private MemberDto memberDto(Member member) {
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
        return springDataJpaMemberRepository.findByName(memberName).stream()
                .map(this::memberDto)
                .collect(Collectors.toList());
    }

    public List<MemberDto> getMembersByPhoneNumber(String phoneNumber) {
        return springDataJpaMemberRepository.findByPhoneNumber(phoneNumber).stream()
                .map(this::memberDto)
                .collect(Collectors.toList());
    }
}
