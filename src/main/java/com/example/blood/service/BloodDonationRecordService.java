package com.example.blood.service;

import com.example.blood.domain.BloodDonationRecord;
import com.example.blood.dto.BloodDonationRecordDto;
import com.example.blood.repository.JpaBloodDonationRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BloodDonationRecordService {
    private final JpaBloodDonationRecordRepository bloodDonationRecordRepository;
    @Autowired
    public BloodDonationRecordService(JpaBloodDonationRecordRepository bloodDonationRecordRepository) {
        this.bloodDonationRecordRepository = bloodDonationRecordRepository;
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
        return bloodDonationRecordRepository.findAll().stream()
                .map(this::convertDto)
                .collect(Collectors.toList());
    }
    public List<BloodDonationRecordDto> getBloodDonationRecordsByMemberName(String memberName) {
        return bloodDonationRecordRepository.findByMemberName(memberName).stream()
                .map(this::convertDto)
                .collect(Collectors.toList());
    }

    public List<BloodDonationRecordDto> getBloodDonationRecordsByBloodDonationRelaySession(String bloodDonationRelaySession) {
        return bloodDonationRecordRepository.findByBloodDonationRelaySession(bloodDonationRelaySession).stream()
                .map(this::convertDto)
                .collect(Collectors.toList());
    }
}
