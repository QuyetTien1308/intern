package com.example.tien.Final.payload.request;

import com.example.tien.Final.entity.Position;
import com.example.tien.Final.entity.Salary;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
//@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeSaveReq {
    private String username;
    private String password;
    private String name;

    private Long positionId;

}