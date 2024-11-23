package com.example.blood.repository;

import com.example.blood.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    List<Member> findAll();
    Optional<Member> findFirstByMemberId(String memberId);

    List<Member> findByName(String memberName);

     List<Object[]> findBloodDonationRanking();
    void save(Member member);
}
