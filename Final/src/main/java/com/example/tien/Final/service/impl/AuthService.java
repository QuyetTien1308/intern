package com.example.tien.Final.service.impl;

import com.example.tien.Final.Dto.EmployeeDto;
import com.example.tien.Final.config.jwt.JwtUtils;
import com.example.tien.Final.entity.Employee;
import com.example.tien.Final.entity.Position;
import com.example.tien.Final.entity.Role;
import com.example.tien.Final.payload.request.LoginRequest;
import com.example.tien.Final.payload.request.SignupRequest;
import com.example.tien.Final.payload.response.JwtResponse;
import com.example.tien.Final.repos.EmployeeRepository;
import com.example.tien.Final.repos.PositionRepository;
import com.example.tien.Final.repos.RoleRepository;
import com.example.tien.Final.service.IAuthService;
import com.example.tien.Final.service.userdetail.UserDetailsImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class AuthService implements IAuthService {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    PositionRepository positionRepository;
    @Autowired
    EmployeeRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public JwtResponse signin(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
                .collect(Collectors.toList());
        JwtResponse response = new JwtResponse();;
        response.setId(Integer.parseInt(userDetails.getId().toString()));
        response.setUsername(userDetails.getUsername());
        response.setRoles(roles);
        response.setAccess_token(token);
        response.setToken_type("Bearer");
        return response;
    }
    @Override
    public EmployeeDto signup(SignupRequest request) throws ParseException {
        EmployeeDto employeeDTO = new EmployeeDto();
        if (userRepository.existsByUsername(request.getUsername())) {
            return employeeDTO;
        }
        else {
            Employee user = new Employee();
            Position position = positionRepository.findById(request.getPosition()).get();
            user.setUsername(request.getUsername());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setName(request.getName());
            user.setPosition(position);
            Role roles = roleRepository.findByName(request.getRole()).get();
            if(roles != null) {
                user.setRoles(Collections.singleton(roles));
            }else {
                Role role = new Role();
                role.setName("ROLE_USER");
                user.setRoles(Collections.singleton(role));
            }
            userRepository.save(user);
            employeeDTO = modelMapper.map(user, EmployeeDto.class);
            employeeDTO.setPositionId(position.getId());
        }
        return employeeDTO;
    }
}
