package com.example.blood.service;

import com.example.blood.domain.*;
import com.example.blood.dto.BloodDonationRecordDto;
import com.example.blood.dto.DonationAmountDto;
import com.example.blood.dto.PatientDto;
import com.example.blood.dto.RequestBloodDonationRecordDto;
import com.example.blood.repository.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class BloodDonationRecordService {
    private final BloodDonationRecordRepository bloodDonationRecordRepository;

    private final MemberRepository memberRepository;
    private final PatientRepository patientRepository;
    private final SpringDataJpaEmployeeRepository springDataJpaEmployeeRepository;
    @PersistenceContext
    EntityManager entityManager;
    
    //이름 암호화
    private String encryptName(String name) {
        return name.charAt(0) + "**";
    }
    private String toStringtDonationAmountToLiter(Object donationAmount) {
        if (donationAmount == null) {
            return "0L"; //null값일시
        }
        double liter = ((Number) donationAmount).doubleValue() / 1000.0;
        return Double.toString(liter)+"L";
    }
    private String toStringDonationAmountToMil(Integer donationAmount) {
        if (donationAmount == null) {
            return "0ml"; // null 값 처리
        }
        return donationAmount + "ml";
    }
    @Autowired
    public BloodDonationRecordService(BloodDonationRecordRepository bloodDonationRecordRepository,SpringDataJpaEmployeeRepository springDataJpaEmployeeRepository, SpringDataJpaBloodDonationRelay springDataJpaBloodDonationRelay,  MemberRepository memberRepository, PatientRepository patientRepository) {
        this.bloodDonationRecordRepository = bloodDonationRecordRepository;
        this.springDataJpaEmployeeRepository = springDataJpaEmployeeRepository;
        this.memberRepository = memberRepository;
        this.patientRepository = patientRepository;
    }

    //BloodDonationRecord=>BloodDonationRecordDto로 변환
    public BloodDonationRecordDto convertRecordDto(BloodDonationRecord bloodDonationRecord) {
        return new BloodDonationRecordDto(
                bloodDonationRecord.getDonationRecordId(),
                bloodDonationRecord.getMember().getName(),
                bloodDonationRecord.getDonationCertificateNumber(),
                bloodDonationRecord.getEmployee().getName(),
                bloodDonationRecord.getDonationDate(),
                bloodDonationRecord.getDonationType(),
                toStringDonationAmountToMil(bloodDonationRecord.getDonationAmount()),
                bloodDonationRecord.getExpirationDate(),
                bloodDonationRecord.getGiveaway(),
                bloodDonationRecord.getBloodDonationRelay() != null ? bloodDonationRecord.getBloodDonationRelay().getBloodDonationRelaySession() : null,
                encryptName(bloodDonationRecord.getPatient().getName())
        );
    }
    //JPA 헌혈기록 전체조회
    public List<BloodDonationRecordDto> getAllBloodDonationRecords() {
        return bloodDonationRecordRepository.findAll().stream()
                .map(this::convertRecordDto)
                .collect(Collectors.toList());
    }
    //JPA 헌혈기록 회원이름으로 조회
    public List<BloodDonationRecordDto> getBloodDonationRecordsByMemberName(String memberName) {
        return bloodDonationRecordRepository.findByMemberName(memberName).stream()
                .map(this::convertRecordDto)
                .collect(Collectors.toList());
    }
    //JPA 헌혈기록 헌혈회차별 조회
    //객체가 null값을 못받길래 object 배열사용
    //stream객체 생성 -> map 메서드 통한 변환(for문 안써도 됨 간편하게 변환 가능) -> 스트림 다시 List로 변환
    public List<DonationAmountDto> findGroupByDonationAmount() {
        return bloodDonationRecordRepository.findGroupByDonationAmount().stream()
                .map(groupRecord -> new DonationAmountDto(
                        groupRecord[0] != null ? (String) groupRecord[0] : "전체헌혈종류", //헌혈종류
                        groupRecord[1] != null ? (String) groupRecord[1] : "전체증정품종류", // 증점품종류
                        toStringtDonationAmountToLiter(groupRecord[2])
                ))
                .collect(Collectors.toList());
    }
    @Transactional
    public void addBloodDonationRecord(RequestBloodDonationRecordDto inputBloodDonationRecordDto) {

        Member member = memberRepository.findFirstByMemberId(inputBloodDonationRecordDto.getMemberId()).orElse(null);
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
    @Transactional
    public void deleteBloodDonationRecord(Long bloodDonationRecordId) {
        bloodDonationRecordRepository.deleteById(bloodDonationRecordId);
    }

    //변경감지통해 수정 , 서비스단에서 프로시저 호출
    @Transactional
    public void updateDonationRecord(Long donationRecordId, String employeeId, LocalDate donationDate, String donationType, Integer donationAmount, String giveaway) {
        BloodDonationRecord bloodDonationRecord = bloodDonationRecordRepository.findById(donationRecordId).orElse(null);
        if (employeeId != null) {
            Employee employee = springDataJpaEmployeeRepository.findFirstByEmployeeId(employeeId);
            if (employee != null) {
                bloodDonationRecord.setEmployee(employee);
            }
        }
        if (donationDate != null) { bloodDonationRecord.setDonationDate(donationDate);
        }
        if (donationType != null) bloodDonationRecord.setDonationType(donationType);
        if (donationAmount != null) bloodDonationRecord.setDonationAmount(donationAmount);
        if (giveaway != null) bloodDonationRecord.setGiveaway(giveaway);
        entityManager.flush();
        bloodDonationRecordRepository.firstLastProcedure(bloodDonationRecord.getMember().getMemberId());
    }
}
