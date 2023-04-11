package com.example.tien.Final.Dto;

import com.example.tien.Final.entity.Employee;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PositionDto {
    private Long id;
    private String name;
    private BigDecimal baseSalary;

}
