package com.example.blood.repository;

import com.example.blood.domain.BloodDonationRecord;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaBloodDonationRecordRepository {

    @PersistenceContext
    private final EntityManager entityManager; //엔티티매니저 빈 등록

    @Autowired
    public JpaBloodDonationRecordRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<BloodDonationRecord> findAll() {
        return entityManager.createQuery("select B from BloodDonationRecord B", BloodDonationRecord.class)
                .getResultList();
    }
    public List<BloodDonationRecord> findByMemberName(String memberName) {
        return entityManager.createQuery("select B from BloodDonationRecord B where B.member.name=:name ",BloodDonationRecord.class)
                .setParameter("name",memberName)
                .getResultList();
    }

    public List<BloodDonationRecord>findByBloodDonationRelaySession(String bloodDonationRelaySession) {
        return entityManager.createQuery("select B from BloodDonationRecord B where B.bloodDonationRelay.bloodDonationRelaySession=:bloodDonationRelaySession ",BloodDonationRecord.class)
                .setParameter("bloodDonationRelaySession",bloodDonationRelaySession)
                .getResultList();
    }
}
