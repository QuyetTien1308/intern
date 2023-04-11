package com.example.tien.Final.entity;

import javax.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "position_tbl")
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(nullable = false)
    private BigDecimal baseSalary;
    @OneToMany(mappedBy = "position")
    private List<Employee> employees;

}
