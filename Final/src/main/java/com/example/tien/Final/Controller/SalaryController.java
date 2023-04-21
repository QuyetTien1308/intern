package com.example.tien.Final.Controller;

import com.example.tien.Final.Dto.SalaryDto;
import com.example.tien.Final.entity.Salary;
import com.example.tien.Final.payload.response.GetAllRes;
import com.example.tien.Final.payload.response.SalariesResponse;
import com.example.tien.Final.service.ISalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salaries")
public class SalaryController {
    @Autowired
    public ISalaryService salaryService;
    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<SalaryDto> getAllSalary(){
        return salaryService.getSalary();
    }

    @GetMapping("/SalariesByUser")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public List<GetAllRes> SalariesByUser(){
        return salaryService.SalariesByUser();
    }
    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public SalariesResponse addSalariesByEmployee(@RequestBody SalaryDto salaryDto){
        return salaryService.save(salaryDto);
    }


}
