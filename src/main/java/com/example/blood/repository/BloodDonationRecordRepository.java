package com.example.blood.repository;

import com.example.blood.domain.BloodDonationRecord;
import java.util.List;

public interface BloodDonationRecordRepository {
    List<BloodDonationRecord> findAll();
    List<BloodDonationRecord> findByMemberName(String memberName);
    List<BloodDonationRecord> findByBloodDonationRelaySession(String bloodDonationRelaySession);
    void save(BloodDonationRecord bloodDonationRecord);
}
