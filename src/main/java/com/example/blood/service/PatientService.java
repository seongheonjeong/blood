package com.example.blood.service;

import com.example.blood.domain.Patient;
import com.example.blood.dto.DonationDetailsDto;
import com.example.blood.dto.PatientDto;
import com.example.blood.dto.RequestPatientDto;
import com.example.blood.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }
    private PatientDto convertToDto(Patient patient) {
        return new PatientDto(
                patient.getPatientId(),
                patient.getName(),
                patient.getBirth(),
                patient.getPhoneNumber(),
                patient.getGender(),
                patient.getHospitalName(),
                patient.getDiseaseName()
        );
    }
    private Patient convertToPatient(RequestPatientDto requestPatientDto) {
        Patient patient=new Patient();
        patient.setName(requestPatientDto.getName());
        patient.setBirth(requestPatientDto.getBirth());
        patient.setGender(requestPatientDto.getGender());
        patient.setHospitalName(requestPatientDto.getHospitalName());
        patient.setDiseaseName(requestPatientDto.getDiseaseName());
        patient.setPhoneNumber(requestPatientDto.getPhoneNumber());
        return patient;
    }
    public List<DonationDetailsDto> getDonationDetails() {
        return patientRepository.findDonationDetails().stream()
                .map(details -> new DonationDetailsDto(
                        (Long) details[0],
                        (String) details[1],
                        (String) details[2],
                        (String) details[3],
                        (String) details[4]
                ))
                .collect(Collectors.toList());
    }
    public List<PatientDto> getAllPatients() {
        return patientRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<PatientDto> getPatientsByPatientName(String patientName) {
        return patientRepository.findByPatientName(patientName).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    @Transactional
    public void addPatient(RequestPatientDto requestPatientDto) {
        Patient patient=convertToPatient(requestPatientDto);
        patientRepository.save(patient);
    }
    @Transactional
    public void updatePatient(String patientId, String name, LocalDate birth, String phoneNumber, String gender, String hospitalName, String diseaseName) {
        Patient patient=patientRepository.findById(patientId).orElse(null);
        if (name != null)  patient.setName(name);
        if (birth != null) patient.setBirth(birth);
        if (phoneNumber != null) patient.setPhoneNumber(phoneNumber);
        if (gender != null) patient.setGender(gender);
        if (hospitalName != null) patient.setHospitalName(hospitalName);
        if (diseaseName != null) patient.setDiseaseName(diseaseName);
    }
}

