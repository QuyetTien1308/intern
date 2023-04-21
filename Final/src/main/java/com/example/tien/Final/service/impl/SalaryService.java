package com.example.tien.Final.service.impl;

import com.example.tien.Final.Dto.EmployeeDto;
import com.example.tien.Final.Dto.SalaryDto;
import com.example.tien.Final.entity.Employee;
import com.example.tien.Final.entity.Position;
import com.example.tien.Final.entity.Salary;
import com.example.tien.Final.payload.response.EmployeeResponse;
import com.example.tien.Final.payload.response.SalariesResponse;
import com.example.tien.Final.repos.EmployeeRepository;
import com.example.tien.Final.repos.PositionRepository;
import com.example.tien.Final.repos.SalaryRepository;
import com.example.tien.Final.payload.response.GetAllRes;
import com.example.tien.Final.service.ISalaryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class SalaryService implements ISalaryService {
    @Autowired
    private SalaryRepository salaryRepository;
    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private PositionService positionService;

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<SalaryDto> getSalary(){
        List<Salary> salaries=salaryRepository.findAll();
        List<SalaryDto> salaryDtos =new ArrayList<>();
        for (Salary salary : salaries){
            SalaryDto salaryDto = SalaryDto.builder()

//                    .baseSalary(salary.getBaseSalary())
                    .daysWorked(salary.getDaysWorked())
                    .daysOvertime(salary.getDaysOvertime())
                    .positionId(salary.getPosition().getId())

                    .employeeId(salary.getEmployee().getId())
                    .overtimeSalary(salary.getOvertimeSalary())
                    .build();
            salaryDtos.add(salaryDto);
        }
        return salaryDtos;
    }
    public Salary getSalaryById(Long id){
        return salaryRepository.findById(id).orElseThrow(()-> new RuntimeException("Error"));
    }

    @Override
    public SalariesResponse save(SalaryDto salaryDto){
        Position position = positionService.getPositionById(salaryDto.getPositionId());
        Employee employee = employeeRepository.findById(salaryDto.getEmployeeId()).get();
        EmployeeResponse employeeResponse = modelMapper.map(employee, EmployeeResponse.class);
        employeeResponse.setPositionId(employee.getPosition().getId());
        SalariesResponse response = SalariesResponse.builder()
                .employeeResponse(employeeResponse)
                .overtimeSalary(salaryDto.getOvertimeSalary())
                .daysOvertime(salaryDto.getDaysOvertime())
                .daysWorked(salaryDto.getDaysWorked())
                .build();
//        Salary salary = Salary.builder()
//
//                .daysWorked(salaryDto.getDaysWorked())
//                .daysOvertime(salaryDto.getDaysOvertime())
//                .overtimeSalary(salaryDto.getOvertimeSalary())
//                .position(position)
//                .employee(employee)
//                .build();
        Salary salary = modelMapper.map(response, Salary.class);
        salary.setEmployee(employee);
        salary.setPosition(position);
        salaryRepository.save(salary);
        return response;
    }


    @Override
    public List<GetAllRes> SalariesByUser() {
        List<Salary> salaries=salaryRepository.findAll();
        List<GetAllRes> lstall = new ArrayList<>();
        for (Salary salary : salaries){

            GetAllRes getAllRes = new GetAllRes();
            Employee employee = salary.getEmployee();
            BigDecimal baseSalary = positionRepository.findById(employee.getPosition().getId()).orElseThrow(() -> new RuntimeException("Id not found")).getBaseSalary();
            getAllRes.setTotalSalary((Long) ((baseSalary).longValue() * salary.getDaysWorked())+((salary.getOvertimeSalary()).longValue() * salary.getDaysOvertime()));
            EmployeeResponse response = new EmployeeResponse();
            response.setId(employee.getId());
            response.setUsername(employee.getUsername());
            response.setName(employee.getName());
            response.setPositionId(employee.getPosition().getId());

            getAllRes.setEmployeeResponse(response);
            lstall.add(getAllRes);
        }

        return lstall;
    }





























//    @Override
//    public double calculateSalary(EmployeeDto employee){
//        double baseSalary = employee.g  etSalaryDto().getBasicSalary();
//        double totalSalary = baseSalary * employee.getDaysWorked();
//        if (employee.getDaysWorked() > 22){
//            totalSalary += baseSalary * 0.3;
//        }
//        if(employee.getSalaryDto().getOvertimeSalary()){
//            totalSalary += 300000;
//        }
//        return totalSalary;
//    }
}
