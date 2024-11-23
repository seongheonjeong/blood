package com.example.blood.repository;

import com.example.blood.domain.BloodDonationRecord;
import java.util.List;
import java.util.Optional;

public interface BloodDonationRecordRepository {
    List<BloodDonationRecord> findAll();
    List<BloodDonationRecord> findByMemberName(String memberName);


    Optional<BloodDonationRecord> findById(Long id);

    List<Object[]> findGroupByDonationAmount();
    void save(BloodDonationRecord bloodDonationRecord);
    void deleteById(Long bloodDonationRecordId);
    void firstLastProcedure(String memberId);
}
