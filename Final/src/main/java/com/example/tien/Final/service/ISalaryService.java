package com.example.tien.Final.service;

import com.example.tien.Final.Dto.SalaryDto;
import com.example.tien.Final.entity.Salary;
import com.example.tien.Final.payload.response.GetAllRes;

import java.util.List;

public interface ISalaryService {
    List<SalaryDto> getSalary();
    Salary save(SalaryDto salaryDto);


    List<GetAllRes> SalariesByUser();
}
