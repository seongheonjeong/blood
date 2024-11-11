package com.example.blood.service;

import com.example.blood.domain.*;
import com.example.blood.dto.BloodDonationRecordDto;
import com.example.blood.dto.PatientDto;
import com.example.blood.dto.RequestBloodDonationRecordDto;
import com.example.blood.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Transactional //트랜잭션 관리
@Service
public class BloodDonationRecordService {
    private final JpaBloodDonationRecordRepository jpaBloodDonationRecordRepository;
    private final SpringDataJpaMemberRepository springDataJpaMemberRepository;
    private final SpringDataJpaEmployeeRepository springDataJpaEmployeeRepository;

    private final JdbcPatientRepository jdbcPatientRepository;
    @Autowired
    public BloodDonationRecordService(JpaBloodDonationRecordRepository jpaBloodDonationRecordRepository, SpringDataJpaMemberRepository springDataJpaMemberRepository, SpringDataJpaEmployeeRepository springDataJpaEmployeeRepository, SpringDataJpaBloodDonationRelay springDataJpaBloodDonationRelay, JdbcPatientRepository jdbcPatientRepository) {
        this.jpaBloodDonationRecordRepository = jpaBloodDonationRecordRepository;
        this.springDataJpaMemberRepository = springDataJpaMemberRepository;
        this.springDataJpaEmployeeRepository = springDataJpaEmployeeRepository;
        this.jdbcPatientRepository = jdbcPatientRepository;
    }

    public BloodDonationRecordDto convertDto(BloodDonationRecord bloodDonationRecord) {
        return new BloodDonationRecordDto(
                bloodDonationRecord.getDonationRecordId(),
                bloodDonationRecord.getMember().getName(),
                bloodDonationRecord.getDonationCertificateNumber(),
                bloodDonationRecord.getEmployee().getName(),
                bloodDonationRecord.getDonationDate(),
                bloodDonationRecord.getDonationType(),
                bloodDonationRecord.getDonationAmount() != null ? bloodDonationRecord.getDonationAmount() : 0,
                bloodDonationRecord.getExpirationDate(),
                bloodDonationRecord.getGiveaway(),
                bloodDonationRecord.getBloodDonationRelay() != null ? bloodDonationRecord.getBloodDonationRelay().getBloodDonationRelaySession() : null,
                bloodDonationRecord.getPatient().getName()
        );
    }
    public List<BloodDonationRecordDto> getAllBloodDonationRecords() {
        return jpaBloodDonationRecordRepository.findAll().stream()
                .map(this::convertDto)
                .collect(Collectors.toList());
    }
    public List<BloodDonationRecordDto> getBloodDonationRecordsByMemberName(String memberName) {
        return jpaBloodDonationRecordRepository.findByMemberName(memberName).stream()
                .map(this::convertDto)
                .collect(Collectors.toList());
    }

    public List<BloodDonationRecordDto> getBloodDonationRecordsByBloodDonationRelaySession(String bloodDonationRelaySession) {
        return jpaBloodDonationRecordRepository.findByBloodDonationRelaySession(bloodDonationRelaySession).stream()
                .map(this::convertDto)
                .collect(Collectors.toList());
    }
    public Patient convertToPatient(PatientDto patientDto) {
        Patient patient = new Patient();
        patient.setPatientId(patientDto.getPatientId());
        patient.setName(patientDto.getName());
        patient.setBirthDate(patientDto.getBirth());
        patient.setPhoneNumber(patientDto.getPhoneNumber());
        patient.setGender(patientDto.getGender());
        patient.setHospitalName(patientDto.getHospitalName());
        patient.setDiseaseName(patientDto.getDiseaseName());
        return patient;
    }
    public void addBloodDonationRecord(RequestBloodDonationRecordDto inputBloodDonationRecordDto) {

        Member member = springDataJpaMemberRepository.findFirstByMemberId(inputBloodDonationRecordDto.getMemberId());
        Employee employee=springDataJpaEmployeeRepository.findFirstByEmployeeId(inputBloodDonationRecordDto.getEmployeeId());
        PatientDto patientDto = jdbcPatientRepository.findById(inputBloodDonationRecordDto.getDonationPatientId()).orElse(null);
        Patient patient = patientDto != null ? convertToPatient(patientDto) : null;
        BloodDonationRecord bloodDonationRecord=new BloodDonationRecord();
        bloodDonationRecord.setMember(member); //회원
        bloodDonationRecord.setEmployee(employee); //직원
        bloodDonationRecord.setDonationDate(inputBloodDonationRecordDto.getDonationDate()); //헌혈일자
        bloodDonationRecord.setDonationType(inputBloodDonationRecordDto.getDonationType()); //헌혈종류
        bloodDonationRecord.setDonationAmount(inputBloodDonationRecordDto.getDonationAmount());//헌혈량
        bloodDonationRecord.setGiveaway(inputBloodDonationRecordDto.getGiveaway()); //증점품종료
        bloodDonationRecord.setPatient(patient);
        jpaBloodDonationRecordRepository.save(bloodDonationRecord);
    }
}
