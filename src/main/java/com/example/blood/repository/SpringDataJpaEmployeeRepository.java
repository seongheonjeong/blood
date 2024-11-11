package com.example.blood.repository;

import com.example.blood.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataJpaEmployeeRepository extends JpaRepository<Employee,Long> {
    Employee findFirstByEmployeeId(String employeeId);
}
