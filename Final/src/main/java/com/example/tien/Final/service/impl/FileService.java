package com.example.tien.Final.service.impl;

import com.example.tien.Final.Dto.EmployeeDto;
import com.example.tien.Final.entity.Employee;
import com.example.tien.Final.entity.Position;
import com.example.tien.Final.repos.EmployeeRepository;
import com.example.tien.Final.repos.FileReponsitory;
import com.example.tien.Final.repos.PositionRepository;
import com.example.tien.Final.service.IFileService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class FileService implements IFileService {
    @Autowired
    private FileReponsitory fileReponsitory;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private ModelMapper modelMapper;

    //private final String FILENAME = "D:\\Downloads\\Managerment\\Final\\engineers.txt";
    private final String FILENAME = "static/engineers.txt";
    @Override
    public List<EmployeeDto> getAllEngineers(MultipartFile file) throws IOException {
        List<EmployeeDto> employees = new ArrayList<>();

        Scanner scanner = new Scanner(file.getInputStream());
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] tokens = line.split(",");
            EmployeeDto employee = new EmployeeDto();
            employee.setId(Long.parseLong(tokens[0]));
            employee.setUsername(tokens[1]);
            employee.setPassword(tokens[2]);
            employee.setName(tokens[3]);
            Position position = new Position();
            position.setId(Long.parseLong(tokens[4]));
            employee.setPositionId(position.getId());

            employees.add((employee));
        }
        scanner.close();
        return employees;
    }
    @Override
    public EmployeeDto save(EmployeeDto employeeDto) {
        Position position = positionRepository.findById(employeeDto.getPositionId()).get();

//        Attendance attendance = attendanceService.getAttendanceById(userDto.getAttendanceId());
        Employee user = Employee.builder()
                .username(employeeDto.getUsername())
                .password(employeeDto.getPassword())
                .name(employeeDto.getName())
                .position(position)
//                .attendance(attendance)
                .build();
        Employee employee =  employeeRepository.save(user);
        return modelMapper.map(employee, EmployeeDto.class);
    }
//    @Override
//    public void save(List<Employee> employees){
//        for (Employee employee : employees){
//            String positionId = employee.getPosition().getId();
//            Position position = positionRepository.findById(positionId);
//            if(position == null){
//                position = new Position();
//                position.setId(positionId);
//                positionRepository.save(position);
//            }
//            employee.setPosition(position.getId());
//        }
//        employeeRepository.save(employee);
//    }


    @Override
    public List<EmployeeDto> readEngineersFromFile(){
        List<EmployeeDto> employeeList = new ArrayList<>();

        try {
            File file = new File(FILENAME);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();

                String[] tokens = line.split(",");
//                Long id = Long.parseLong(tokens[0]);
//                String username = tokens[1];
//                String password = (tokens[2]);
//                String name = tokens[3];

                EmployeeDto employee = new EmployeeDto();
                employee.setUsername(tokens[1]);
                employee.setPassword(tokens[2]);
                employee.setName(tokens[3]);


                Position position = new Position();
                position.setId(Long.parseLong(tokens[4]));
                employee.setPositionId(position.getId());

                employeeList.add(employee);
            }
            scanner.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return employeeList;
    }

    @Override
    public List<EmployeeDto> readEngineersFromFiles(MultipartFile file) throws IOException{
        List<EmployeeDto> employeeList = new ArrayList<>();

        try {

            Scanner scanner = new Scanner(file.getInputStream());
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();

                String[] tokens = line.split(",");
//                Long id = Long.parseLong(tokens[0]);
//                String username = tokens[1];
//                String password = (tokens[2]);
//                String name = tokens[3];

                EmployeeDto employee = new EmployeeDto();
                employee.setId(Long.parseLong(tokens[0]));
                employee.setUsername(tokens[1]);
                employee.setPassword(tokens[2]);
                employee.setName(tokens[3]);


                Position position = new Position();
                position.setId(Long.parseLong(tokens[4]));
                employee.setPositionId(position.getId());

                employeeList.add(employee);
            }
            scanner.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return employeeList;
    }

}
