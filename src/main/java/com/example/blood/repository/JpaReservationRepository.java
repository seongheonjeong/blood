package com.example.blood.repository;

import com.example.blood.domain.Reservation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public class JpaReservationRepository {
    @PersistenceContext
    private final EntityManager entityManager; //엔티티매니저 빈 등록

    @Autowired
    public JpaReservationRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    public List<Reservation> findByMemberName(String memberName) {
       return entityManager.createQuery("select R from Reservation R where R.member.name= :name",Reservation.class)
               .setParameter("name",memberName)
               .getResultList();
    }
    public List<Reservation> findAll() {
        return entityManager.createQuery("select R from Reservation R", Reservation.class)
                .getResultList();
    }

    public List<Reservation> findByEmployeeName(String employeeName) {
        return entityManager.createQuery("select R from Reservation R where R.employee.name=:name",Reservation.class)
                .setParameter("name",employeeName)
                .getResultList();
    }
}
