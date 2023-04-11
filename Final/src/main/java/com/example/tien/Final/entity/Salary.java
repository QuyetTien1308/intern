package com.example.tien.Final.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "salary_tbl")
public class Salary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal overtimeSalary;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_id")
    private Position position;
    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    @Column(name = "days_worked")
    private int daysWorked;

    @Column(name = "days_overtime")
    private int daysOvertime;

}
