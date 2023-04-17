package com.example.tien.Final.Controller;

import com.example.tien.Final.Dto.EmployeeDto;
import com.example.tien.Final.exception.ErrorParam;
import com.example.tien.Final.exception.SysError;
import com.example.tien.Final.payload.request.UpdateRoleRequest;
import com.example.tien.Final.payload.response.ErrorResponse;
import com.example.tien.Final.payload.response.SuccessReponse;
import com.example.tien.Final.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    public IEmployeeService employeeService;
    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<EmployeeDto> findAllEmployee(){
        return employeeService.findAll();
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findById(@PathVariable("id") Long id){
        return ResponseEntity.ok(new SuccessReponse("Success", employeeService.findOne(id), HttpStatus.OK.name()));
    }
    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public EmployeeDto addEmployee(@RequestBody EmployeeDto employeeDto ){
        return employeeService.create(employeeDto);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public EmployeeDto update(@RequestBody EmployeeDto dto, @PathVariable("id") Long id){
        dto.setId(id);
        return employeeService.update(dto);
    }
    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String delete(@PathVariable("id") Long id){
        return employeeService.delete(id);
    }
    @PutMapping("/update-role")
    public ResponseEntity<?> updateRole(@RequestBody UpdateRoleRequest request){
        if(employeeService.updateRole(request)){
            return ResponseEntity.ok("Success");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            new ErrorResponse(HttpStatus.BAD_REQUEST.name(), new SysError("Employee id not found", new ErrorParam()))
        );
    }
    @GetMapping("/see-salary-employees")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> seeSalary(){
        return employeeService.seeSalaryEmployees();
    }
}
