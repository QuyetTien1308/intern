package com.example.tien.Final.service;

import com.example.tien.Final.Dto.EmployeeDto;
import com.example.tien.Final.payload.request.LoginRequest;
import com.example.tien.Final.payload.request.SignupRequest;
import com.example.tien.Final.payload.response.JwtResponse;

import java.text.ParseException;

public interface IAuthService {
    JwtResponse signin(LoginRequest request);

    EmployeeDto signup(SignupRequest request) throws ParseException;
}
