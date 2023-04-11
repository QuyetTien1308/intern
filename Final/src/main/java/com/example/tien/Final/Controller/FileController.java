package com.example.tien.Final.Controller;

import com.example.tien.Final.Dto.EmployeeDto;
import com.example.tien.Final.entity.Employee;
import com.example.tien.Final.service.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/file")
public class FileController {
    @Autowired
    public IFileService fileService;

   /* @GetMapping
    public List<EmployeeDto> getAllEngineers(@RequestPart(name = '')) throws IOException {
        return fileService.getAllEngineers();
    }*/
    @PostMapping
    public ResponseEntity<?> saveUsersFromFiles(@RequestPart(name = "file")MultipartFile file) throws IOException {
        // Đọc dữ liệu từ file .txt và chuyển đổi sang định dạng của model
        List<EmployeeDto> employeeList = fileService.readEngineersFromFiles(file);

        // Lưu dữ liệu vào database
        for (EmployeeDto employeeDto : employeeList) {

            fileService.save(employeeDto);
        }
        return ResponseEntity.status(HttpStatus.OK).body(fileService.readEngineersFromFiles(file));
    }
}
