package com.example.blood.repository;

import com.example.blood.domain.Patient;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class JpaPatientRepository implements PatientRepository {

    @PersistenceContext
    private final EntityManager entityManager; //엔티티매니저 빈 등록
    @Autowired
    public JpaPatientRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Patient> findById(String id) {
        Patient patient = entityManager.find(Patient.class, id);
        return patient != null ? Optional.of(patient) : Optional.empty();
    }

    @Override
    public List<Patient> findByPatientName(String patientName) {
        return entityManager.createQuery("SELECT p FROM Patient p WHERE p.name=:name ",Patient.class)
                .setParameter("name", patientName)
                .getResultList();
    }
    @Override
    public List<Patient> findAll() {
        return entityManager.createQuery("SELECT p FROM Patient p order by  p.patientId asc", Patient.class)
                .getResultList();
    }

    @Override
    public List<Object[]> findDonationDetails() {
        return entityManager.createQuery("SELECT B.donationCertificateNumber,B.member.memberId,B.member.name,B.patient.patientId,B.patient.name FROM BloodDonationRecord B", Object[].class)
                .getResultList();
    }

    @Override
    public void save(Patient patient) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("LAST_PA_PLUS_1");
        storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.OUT);
        storedProcedureQuery.execute();
        String patientId=(String) storedProcedureQuery.getOutputParameterValue(1);
        patient.setPatientId(patientId);
        entityManager.persist(patient);
    }


}
