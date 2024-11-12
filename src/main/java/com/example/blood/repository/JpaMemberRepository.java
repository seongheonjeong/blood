package com.example.blood.repository;

import com.example.blood.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaMemberRepository implements MemberRepository{
    @PersistenceContext
    private final EntityManager entityManager;
    @Autowired
    public JpaMemberRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Member> findByPhoneNumber(String phoneNumber) {
        return entityManager.createQuery("SELECT m FROM Member m WHERE m.phoneNumber = :phoneNumber", Member.class)
                .setParameter("phoneNumber", phoneNumber)
                .getResultList();
    }

    @Override
    public List<Member> findAll() {
        return entityManager.createQuery("SELECT m FROM Member m", Member.class)
                .getResultList();
    }


    @Override
    public Member findFirstByMemberId(String memberId) {
        return entityManager.createQuery("SELECT m FROM Member m WHERE m.memberId = :memberId", Member.class)
                .setParameter("memberId", memberId)
                .getSingleResult();
    }

    @Override
    public List<Member> findByName(String memberName) {
        return entityManager.createQuery("SELECT m FROM Member m WHERE m.name = :memberName", Member.class)
                .setParameter("memberName", memberName)
                .getResultList();
    }
}
