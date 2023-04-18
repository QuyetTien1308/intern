package com.example.tien.Final.Dto;

import javax.persistence.Column;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SalaryDto {

    private Long employeeId;
    private int daysWorked;
    private int daysOvertime;
    private BigDecimal overtimeSalary;
    private Long positionId;

}
