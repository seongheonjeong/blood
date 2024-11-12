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
    private final BloodDonationRecordRepository bloodDonationRecordRepository;
    private final JpaBloodDonationRecordRepository jpaBloodDonationRecordRepository;

    private final MemberRepository memberRepository;
    private final PatientRepository patientRepository;
    private final SpringDataJpaEmployeeRepository springDataJpaEmployeeRepository;


    @Autowired
    public BloodDonationRecordService(BloodDonationRecordRepository bloodDonationRecordRepository, JpaBloodDonationRecordRepository jpaBloodDonationRecordRepository,SpringDataJpaEmployeeRepository springDataJpaEmployeeRepository, SpringDataJpaBloodDonationRelay springDataJpaBloodDonationRelay,  MemberRepository memberRepository, PatientRepository patientRepository) {
        this.bloodDonationRecordRepository = bloodDonationRecordRepository;
        this.jpaBloodDonationRecordRepository = jpaBloodDonationRecordRepository;
        this.springDataJpaEmployeeRepository = springDataJpaEmployeeRepository;
        this.memberRepository = memberRepository;
        this.patientRepository = patientRepository;
    }

    //BloodDonationRecord=>BloodDonationRecordDto로 변환
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
    //JPA 헌혈기록 전체조회
    public List<BloodDonationRecordDto> getAllBloodDonationRecords() {
        return bloodDonationRecordRepository.findAll().stream()
                .map(this::convertDto)
                .collect(Collectors.toList());
    }
    //JPA 헌혈기록 회원이름으로 조회
    public List<BloodDonationRecordDto> getBloodDonationRecordsByMemberName(String memberName) {
        return bloodDonationRecordRepository.findByMemberName(memberName).stream()
                .map(this::convertDto)
                .collect(Collectors.toList());
    }
    //JPA 헌혈기록 헌혈회차별 조회
    public List<BloodDonationRecordDto> getBloodDonationRecordsByBloodDonationRelaySession(String bloodDonationRelaySession) {
        return bloodDonationRecordRepository.findByBloodDonationRelaySession(bloodDonationRelaySession).stream()
                .map(this::convertDto)
                .collect(Collectors.toList());
    }
    //PatientDto를 Patient로 변환
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
    //삽입 JPA 이용,수정할꺼임(JPA만이용할꺼)
    public void addBloodDonationRecord(RequestBloodDonationRecordDto inputBloodDonationRecordDto) {

        Member member = memberRepository.findFirstByMemberId(inputBloodDonationRecordDto.getMemberId());
        Employee employee=springDataJpaEmployeeRepository.findFirstByEmployeeId(inputBloodDonationRecordDto.getEmployeeId());
        Patient patient = patientRepository.findById(inputBloodDonationRecordDto.getDonationPatientId()).orElse(null);
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
