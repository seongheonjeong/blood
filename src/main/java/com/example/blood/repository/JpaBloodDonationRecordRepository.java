package com.example.blood.repository;

import com.example.blood.domain.BloodDonationRecord;
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
public class JpaBloodDonationRecordRepository implements BloodDonationRecordRepository {

    @PersistenceContext
    private final EntityManager entityManager; //엔티티매니저 빈 등록

    @Autowired
    public JpaBloodDonationRecordRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<BloodDonationRecord> findAll() {
        return entityManager.createQuery("select B from BloodDonationRecord B order by  B.donationDate", BloodDonationRecord.class)
                .getResultList();
    }

    @Override
    public List<BloodDonationRecord> findByMemberName(String memberName) {
        return entityManager.createQuery("select B from BloodDonationRecord B where B.member.name=:name order by  B.donationDate ",BloodDonationRecord.class)
                .setParameter("name",memberName)
                .getResultList();
    }

    @Override
    public Optional<BloodDonationRecord> findById(Long id) {
       BloodDonationRecord bloodDonationRecord=entityManager.find(BloodDonationRecord.class,id);
       return bloodDonationRecord!=null? Optional.of((bloodDonationRecord)) : Optional.empty();
    }

    //
    @Override
    public List<Object[]> findGroupByDonationAmount() {
        String sql = "SELECT 헌혈종류, 증정품종류, SUM(헌혈량) " +
                     "FROM 헌혈기록 " +
                     "GROUP BY ROLLUP(헌혈종류, 증정품종류) " +
                     "ORDER BY SUM(헌혈량) ASC";

        return entityManager.createNativeQuery(sql)
                .getResultList();
    }

    @Override
    public void save(BloodDonationRecord bloodDonationRecord) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("LAST_BDN2_PLUS_1 ");
        storedProcedureQuery.registerStoredProcedureParameter(1, Long.class, ParameterMode.OUT);
        storedProcedureQuery.execute();
        Long outParameter=(Long) storedProcedureQuery.getOutputParameterValue(1);
        bloodDonationRecord.setDonationCertificateNumber(outParameter);
        entityManager.persist(bloodDonationRecord);
    }

    @Override
    public void deleteById(Long bloodDonationRecordId) {
        StoredProcedureQuery storedProcedureQuery1 = entityManager.createStoredProcedureQuery("RET_BDP_BY_BDN1");
        storedProcedureQuery1.registerStoredProcedureParameter(1, Long.class, ParameterMode.IN);
        storedProcedureQuery1.setParameter(1, bloodDonationRecordId);
        storedProcedureQuery1.registerStoredProcedureParameter(2, String.class, ParameterMode.OUT);
        storedProcedureQuery1.execute();
        String outParameter=(String) storedProcedureQuery1.getOutputParameterValue(2);
        entityManager.createQuery("DELETE FROM BloodDonationRecord B WHERE B.donationRecordId = :id")
                .setParameter("id", bloodDonationRecordId)
                .executeUpdate();
        firstLastProcedure(outParameter);
    }
    @Override
    public void firstLastProcedure(String memberId) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("B_DAY_FIRST_LAST");
        storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
        storedProcedureQuery.setParameter(1, memberId);
        storedProcedureQuery.execute();
    }
}
