package com.example.tien.Final.service;

import com.example.tien.Final.Dto.SalaryDto;
import com.example.tien.Final.entity.Salary;
import com.example.tien.Final.payload.response.GetAllRes;
import com.example.tien.Final.payload.response.SalariesResponse;

import java.util.List;

public interface ISalaryService {
    List<SalaryDto> getSalary();
    SalariesResponse save(SalaryDto salaryDto);


    List<GetAllRes> SalariesByUser();
}
