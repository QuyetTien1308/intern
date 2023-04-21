package com.example.tien.Final.payload.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalariesResponse {

    private EmployeeResponse employeeResponse;
    private int daysWorked;
    private int daysOvertime;
    private BigDecimal overtimeSalary;

}
