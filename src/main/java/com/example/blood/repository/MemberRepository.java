package com.example.blood.repository;

import com.example.blood.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface MemberRepository {
    List<Member> findByPhoneNumber(String phoneNumber);
    List<Member> findAll();
    Member findFirstByMemberId(String memberId);

    List<Member> findByName(String memberName);
}
