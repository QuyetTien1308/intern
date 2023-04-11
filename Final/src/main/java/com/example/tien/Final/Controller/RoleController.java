package com.example.tien.Final.Controller;

import com.example.tien.Final.Dto.RoleDto;
import com.example.tien.Final.exception.SysError;
import com.example.tien.Final.payload.response.ErrorResponse;
import com.example.tien.Final.payload.response.RoleResponse;
import com.example.tien.Final.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/roles")
public class RoleController {
    @Autowired
    private IRoleService roleService;
    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findAll(){
        RoleResponse response = roleService.findAll();
        if(response != null){
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("False");
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id){
        RoleResponse response = roleService.findById(id);
        if(response != null){
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("False");
    }
    @PostMapping
    public ResponseEntity<?> createRole(@RequestBody RoleDto roleDto){
        RoleResponse response = roleService.create(roleDto);
        if(response != null){
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("False");
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@RequestBody RoleDto roleDto, @PathVariable("id") Long id){
        roleDto.setId(id);
        RoleResponse response = roleService.updateById(roleDto);
        if(response != null){
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("Bad Request",new SysError()));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id){
        if(roleService.deleteById(id)){
            return ResponseEntity.ok("Success");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("False");
    }
}
