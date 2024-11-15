package com.example.blood.service;

import com.example.blood.domain.*;
import com.example.blood.dto.BloodDonationRecordDto;
import com.example.blood.dto.DonationAmountDto;
import com.example.blood.dto.PatientDto;
import com.example.blood.dto.RequestBloodDonationRecordDto;
import com.example.blood.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BloodDonationRecordService {
    private final BloodDonationRecordRepository bloodDonationRecordRepository;

    private final MemberRepository memberRepository;
    private final PatientRepository patientRepository;
    private final SpringDataJpaEmployeeRepository springDataJpaEmployeeRepository;


    @Autowired
    public BloodDonationRecordService(BloodDonationRecordRepository bloodDonationRecordRepository,SpringDataJpaEmployeeRepository springDataJpaEmployeeRepository, SpringDataJpaBloodDonationRelay springDataJpaBloodDonationRelay,  MemberRepository memberRepository, PatientRepository patientRepository) {
        this.bloodDonationRecordRepository = bloodDonationRecordRepository;
        this.springDataJpaEmployeeRepository = springDataJpaEmployeeRepository;
        this.memberRepository = memberRepository;
        this.patientRepository = patientRepository;
    }

    //BloodDonationRecord=>BloodDonationRecordDto로 변환
    public BloodDonationRecordDto convertRecordtDto(BloodDonationRecord bloodDonationRecord) {
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
                .map(this::convertRecordtDto)
                .collect(Collectors.toList());
    }
    //JPA 헌혈기록 회원이름으로 조회
    public List<BloodDonationRecordDto> getBloodDonationRecordsByMemberName(String memberName) {
        return bloodDonationRecordRepository.findByMemberName(memberName).stream()
                .map(this::convertRecordtDto)
                .collect(Collectors.toList());
    }
    //JPA 헌혈기록 헌혈회차별 조회
    public List<BloodDonationRecordDto> getBloodDonationRecordsByBloodDonationRelaySession(String bloodDonationRelaySession) {
        return bloodDonationRecordRepository.findByBloodDonationRelaySession(bloodDonationRelaySession).stream()
                .map(this::convertRecordtDto)
                .collect(Collectors.toList());
    }
    //객체가 null값을 못받길래 object 배열사용
    //stream객체 생성 -> map 메서드 통한 변환(for문 안써도 됨 간편하게 변환 가능) -> 스트림 다시 List로 변환
    public List<DonationAmountDto> findGroupByDonationAmount() {
        return bloodDonationRecordRepository.findGroupByDonationAmount().stream()
                .map(groupRecord -> new DonationAmountDto(
                        groupRecord[0] != null ? (String) groupRecord[0] : "전체헌혈종류", //헌혈종류
                        groupRecord[1] != null ? (String) groupRecord[1] : "전체증정품종류", // 증점품종류
                        groupRecord[2] != null ?  ((Number) groupRecord[2]).intValue() : 0 // 헌혈량
                ))
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
    @Transactional //트랜잭션 관리
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
        bloodDonationRecordRepository.save(bloodDonationRecord);
    }

    public void deleteBloodDonationRecord(Long bloodDonationRecordId) {
        bloodDonationRecordRepository.deleteById(bloodDonationRecordId);
    }
}
