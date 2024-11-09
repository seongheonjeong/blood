package com.example.blood.repository;

import com.example.blood.domain.Member;
import com.example.blood.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByName(String name);
    List<Member> findByPhoneNumber(String phoneNumber);

}
