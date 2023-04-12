package com.example.tien.Final.service.impl;

import com.example.tien.Final.Dto.EmployeeDto;
import com.example.tien.Final.config.security.SecurityUtils;
import com.example.tien.Final.entity.Employee;
import com.example.tien.Final.entity.Position;
import com.example.tien.Final.entity.Role;
import com.example.tien.Final.entity.Salary;
import com.example.tien.Final.payload.request.UpdateRoleRequest;
import com.example.tien.Final.payload.response.GetAllRes;
import com.example.tien.Final.repos.EmployeeRepository;
import com.example.tien.Final.repos.PositionRepository;
import com.example.tien.Final.repos.RoleRepository;
import com.example.tien.Final.repos.SalaryRepository;
import com.example.tien.Final.service.IEmployeeService;
import com.example.tien.Final.service.ISalaryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.*;

@Service
public class EmployeeService implements IEmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository ;
    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private PositionService positionService;
    @Autowired
    SalaryRepository salaryRepository;
    @Autowired
    private ISalaryService salaryService;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<EmployeeDto> findAll(){
        List<EmployeeDto> employeeDtos = new ArrayList<>();
        List<Employee> employees = employeeRepository.findAll();
        //Position position = positionRepository.getPositionById(dto.getPositionId());
        for (Employee employee : employees ){
            //employee.setPosition(position);
            EmployeeDto dtos = modelMapper.map(employee, EmployeeDto.class);
            dtos.setPositionId(employee.getPosition().getId());
            employeeDtos.add(dtos);
        }
        return employeeDtos;
    }
    @Override
    public EmployeeDto findOne(Long id){
        try {
            Employee employee = employeeRepository.findById(id).orElse(null);
            EmployeeDto dto = modelMapper.map(employee, EmployeeDto.class);
            dto.setPositionId(employee.getPosition().getId());
            return dto;
        }catch (Exception e){
            return null;
        }
    }
    @Override
    public EmployeeDto create(EmployeeDto dto){
        Position position = positionRepository.findOneById(dto.getPositionId());
        Employee employee = modelMapper.map(dto, Employee.class);
        employee.setPosition(position);
        Employee result = employeeRepository.save(employee);

        EmployeeDto dtos = modelMapper.map(result, EmployeeDto.class);
        dtos.setPositionId(result.getPosition().getId());
        return modelMapper.map(dtos, EmployeeDto.class);

    }
    @Override
    public EmployeeDto update(EmployeeDto employeeDto){
        try{
            Employee employee = employeeRepository.findById(employeeDto.getId()).orElse(null);
            Position position = positionRepository.findOneById(employeeDto.getPositionId());
            if(employee == null){
                return null;
            }
            if (employeeDto.getUsername() != null) {
                employee.setUsername(employeeDto.getUsername());
            }
            if (employeeDto.getPassword() != null) {
                employee.setPassword(employeeDto.getPassword());
            }
            if (employeeDto.getName() != null) {
                employee.setName(employeeDto.getName());
            }
            if (position != null) {
                employee.setPosition(position);
            }
//            employee.setUsername(employeeDto.getUsername());
//            employee.setPassword(employee.getPassword());
//            employee.setName(employeeDto.getName());
//            employee.setPosition(position);

            Employee result = employeeRepository.save(employee);
            EmployeeDto dto = modelMapper.map(result, EmployeeDto.class);
            dto.setPositionId(result.getPosition().getId());

            return modelMapper.map(dto, EmployeeDto.class);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public String delete(Long id){
        employeeRepository.deleteById(id);
        return "Delete Success";
    }
    @Override
    public Double getEmployeePayroll(Long id) {
        Salary salaryDb = salaryRepository.findByEmployeeId(id);
        Employee employee = employeeRepository.findById(id).orElseThrow(()-> new RuntimeException("Error"));
        BigDecimal baseSalary = positionRepository.findById(employee.getPosition().getId()).get().getBaseSalary();
        Double salary = 0.00;
//        Double amountOfSalaryIn1Day = ( baseSalary,salaryDb.getOvertimeSalary()).doubleValue();
        salary = (double) ((baseSalary).doubleValue() * salaryDb.getDaysWorked())+(salaryDb.getOvertimeSalary()).doubleValue();
        return salary;
    }
    @Override
    public Boolean updateRole(UpdateRoleRequest request){
        try{
            Employee employee =  employeeRepository.findOneById(request.getId());
            if(employee == null){
                return false;
            }else {
                Set<Role> roles = employee.getRoles();
                Role role = roleRepository.findByName(request.getRole()).get();
                if(ObjectUtils.isEmpty(role)){

                    roles.add(role);
                }
                else {
                    role.setName(request.getRole());
                    roles.clear();
                    roles.add(role);
                    employee.setRoles(roles);
                }
                Employee result = employeeRepository.save(employee);
                return result != null;





            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public ResponseEntity<?> seeSalaryEmployees() {
        try {
            List<GetAllRes> lstSalaries = salaryService.SalariesByUser();
            Comparator<GetAllRes> comparator = Comparator.comparing(GetAllRes::getTotalSalary).reversed();
            Collections.sort(lstSalaries, comparator);
            Iterator<GetAllRes> iterator = lstSalaries.iterator();
            while (iterator.hasNext()) {
                GetAllRes getAllRes = iterator.next();
                if (getAllRes.getEmployeeDto().getId() ==  SecurityUtils.getPrincipal().getId()) {
                    iterator.remove();
                    break;
                }
            }
            return ResponseEntity.ok(lstSalaries);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }

    }
}
