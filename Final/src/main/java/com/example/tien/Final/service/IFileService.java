package com.example.tien.Final.service;

import com.example.tien.Final.Dto.EmployeeDto;
import com.example.tien.Final.entity.Employee;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IFileService {

    EmployeeDto save(EmployeeDto employeeDto);
    List<EmployeeDto> readEngineersFromFiles(MultipartFile file) throws IOException;
}
