package com.example.tien.Final.repos;

import com.example.tien.Final.entity.Employee;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByUsername(String username);
    Boolean existsByUsername(String username);
    Employee findOneById(Long id);
}
