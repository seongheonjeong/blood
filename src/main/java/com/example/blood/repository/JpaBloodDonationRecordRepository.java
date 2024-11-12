package com.example.blood.repository;

import com.example.blood.domain.BloodDonationRecord;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaBloodDonationRecordRepository implements BloodDonationRecordRepository {

    @PersistenceContext
    private final EntityManager entityManager; //엔티티매니저 빈 등록

    @Autowired
    public JpaBloodDonationRecordRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<BloodDonationRecord> findAll() {
        return entityManager.createQuery("select B from BloodDonationRecord B", BloodDonationRecord.class)
                .getResultList();
    }
    @Override
    public List<BloodDonationRecord> findByMemberName(String memberName) {
        return entityManager.createQuery("select B from BloodDonationRecord B where B.member.name=:name ",BloodDonationRecord.class)
                .setParameter("name",memberName)
                .getResultList();
    }
    @Override
    public List<BloodDonationRecord>findByBloodDonationRelaySession(String bloodDonationRelaySession) {
        return entityManager.createQuery("select B from BloodDonationRecord B where B.bloodDonationRelay.bloodDonationRelaySession=:bloodDonationRelaySession ",BloodDonationRecord.class)
                .setParameter("bloodDonationRelaySession",bloodDonationRelaySession)
                .getResultList();
    }
    @Override
    public void save(BloodDonationRecord bloodDonationRecord) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("LAST_BDN2_PLUS_1 ");
        storedProcedureQuery.registerStoredProcedureParameter(1, Long.class, ParameterMode.OUT);
        storedProcedureQuery.execute();
        Long outParameter=(Long)  storedProcedureQuery.getOutputParameterValue(1);
        bloodDonationRecord.setDonationCertificateNumber(outParameter);
        entityManager.persist(bloodDonationRecord);
    }

    @Override
    public void deleteById(Long bloodDonationRecordId) {
        entityManager.createQuery("DELETE FROM BloodDonationRecord B WHERE B.donationRecordId = :id")
                .setParameter("id", bloodDonationRecordId)
                .executeUpdate();
    }
}
