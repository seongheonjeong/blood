package com.example.blood.repository;

import com.example.blood.domain.Reservation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public class JpaReservationRepository implements ReservationRepository {
    @PersistenceContext
    private final EntityManager entityManager; //엔티티매니저 빈 등록

    @Autowired
    public JpaReservationRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Reservation> findByReservationStatus(String reservationStatus) {
       return entityManager.createQuery("select R from Reservation R where R.reservationStatus= :reservationStatus order by R.reservationDateTime",Reservation.class)
               .setParameter("reservationStatus",reservationStatus)
               .getResultList();
    }
    @Override
    public List<Reservation> findAll() {
        return entityManager.createQuery("select R from Reservation R order by  R.reservationDateTime", Reservation.class)
                .getResultList();
    }

    @Override
    public List<Object[]> findReservationCount() {
            String sql= "SELECT A.담당예약직원id, B.이름, COUNT(*)" +
                    "FROM 예약내역 A inner join 직원 B on (A.담당예약직원id = B.직원id)" +
                    "WHERE 예약상태 = '정상'" +
                    "GROUP BY A.담당예약직원id, B.이름";
        return entityManager.createNativeQuery(sql)
                .getResultList();
    }

}
