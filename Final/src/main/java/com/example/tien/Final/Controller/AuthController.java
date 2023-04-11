package com.example.tien.Final.Controller;

import com.example.tien.Final.Dto.EmployeeDto;
import com.example.tien.Final.exception.ErrorParam;
import com.example.tien.Final.exception.SysError;
import com.example.tien.Final.payload.request.LoginRequest;
import com.example.tien.Final.payload.request.SignupRequest;
import com.example.tien.Final.payload.response.ErrorResponse;
import com.example.tien.Final.payload.response.JwtResponse;
import com.example.tien.Final.payload.response.SuccessReponse;
import com.example.tien.Final.repos.RoleRepository;
import com.example.tien.Final.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    IAuthService authService;

    @Autowired
    RoleRepository roleRepository;

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody LoginRequest loginRequest) {
        JwtResponse jwtResponse = authService.signin(loginRequest);
        if (jwtResponse.getUsername() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ErrorResponse(HttpStatus.BAD_REQUEST.name(), new SysError("email-not-found", new ErrorParam()))
            );
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessReponse("success", jwtResponse, HttpStatus.OK.name()));
        }
    }
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest) throws ParseException {
        EmployeeDto employeeDTO = authService.signup(signupRequest);
        if (employeeDTO.getUsername() == null || employeeDTO.getName() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ErrorResponse(HttpStatus.BAD_REQUEST.name(), new SysError("email-already-exists", new ErrorParam("email")))
            );
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessReponse("success",employeeDTO , HttpStatus.OK.name()));
        }
    }
}
