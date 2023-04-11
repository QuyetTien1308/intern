package com.example.tien.Final.Dto;

import com.example.tien.Final.entity.Position;
import com.example.tien.Final.entity.Salary;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;


import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
    private Long id;
    private String username;

    private String password;
    private String name;

    private Long positionId;

//    private SalaryDto salary;
}
