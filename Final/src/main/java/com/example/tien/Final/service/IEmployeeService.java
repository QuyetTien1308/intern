package com.example.tien.Final.service;

import com.example.tien.Final.Dto.EmployeeDto;
import com.example.tien.Final.entity.Employee;
import com.example.tien.Final.payload.request.UpdateRoleRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IEmployeeService {


    List<EmployeeDto> findAll();

    EmployeeDto findOne(Long id);

    EmployeeDto create(EmployeeDto dto);

    EmployeeDto update(EmployeeDto employeeDto);

    String delete(Long id);

    Double getEmployeePayroll(Long id);

    Boolean updateRole(UpdateRoleRequest request);

    ResponseEntity<?> seeSalaryEmployees();
}
