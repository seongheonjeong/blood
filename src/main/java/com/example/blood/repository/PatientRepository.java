package com.example.blood.repository;

import com.example.blood.domain.Patient;
import java.util.List;
import java.util.Optional;

//환우 인터페이스
public interface PatientRepository {
    Optional<Patient> findById(String id);
    List<Patient> findByPatientName(String patientName);
    List<Patient> findAll();
    List<Object[]> findDonationDetails();
    void save(Patient patient);
}
