package com.example.tien.Final.repos;

import com.example.tien.Final.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileReponsitory extends JpaRepository<Employee, Long> {
}
