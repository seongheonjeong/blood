package com.example.blood.repository;

import com.example.blood.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaMemberRepository implements MemberRepository{
    @PersistenceContext
    private final EntityManager entityManager;
    @Autowired
    public JpaMemberRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Member> findAll() {
        return entityManager.createQuery("SELECT m FROM Member m order by m.memberId", Member.class)
                .getResultList();
    }


    @Override
    public Optional<Member> findFirstByMemberId(String memberId) {
        Member member=entityManager.find(Member.class,memberId);
        return member !=null ? Optional.of(member) : Optional.empty();
    }

    @Override
    public List<Member> findByName(String memberName) {
        return entityManager.createQuery("SELECT m FROM Member m WHERE m.name = :memberName", Member.class)
                .setParameter("memberName", memberName)
                .getResultList();
    }

    @Override
    public List<Object[]> findBloodDonationRanking() {
        String sql = "SELECT 회원id, 이름, COALESCE(헌혈횟수, 0) AS 헌혈횟수, " +
                     "DENSE_RANK() OVER (ORDER BY COALESCE(헌혈횟수, 0) DESC) AS 헌혈랭킹 " +
                     "FROM 헌혈자";
        return entityManager.createNativeQuery(sql)
                .getResultList();
    }

    @Override
    //헌혈자(이름, 생년월일, 성별, 혈액형, 휴대폰번호, 주소)
    //회원ID, 헌혈횟수, 최초헌혈일, 마지막헌혈일은 자동화(프로시저 호출 및 트리거)
    //회원ID(프로시저, LAST_BDP_PLUS_1)
    //헌혈횟수(트리거, BD_COUNT_PLUS_1)
    public void save(Member member) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("LAST_BDP_PLUS_1");
        storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.OUT);
        storedProcedureQuery.execute();
        String memberId=(String) storedProcedureQuery.getOutputParameterValue(1);
        member.setMemberId(memberId);
        entityManager.persist(member);
    }
}
