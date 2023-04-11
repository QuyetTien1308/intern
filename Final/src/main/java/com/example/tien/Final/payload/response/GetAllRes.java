package com.example.tien.Final.payload.response;

import com.example.tien.Final.Dto.EmployeeDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
//@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllRes {
 private EmployeeDto employeeDto;

 private Long TotalSalary;

}