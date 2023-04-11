package com.example.tien.Final.repos;

import com.example.tien.Final.entity.Salary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaryRepository extends JpaRepository<Salary, Long> {
    Salary findByEmployeeId(Long salaryId);
}
