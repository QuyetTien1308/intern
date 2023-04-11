package com.example.tien.Final.service.impl;

import com.example.tien.Final.Dto.PositionDto;
import com.example.tien.Final.entity.Position;
import com.example.tien.Final.repos.EmployeeRepository;
import com.example.tien.Final.repos.PositionRepository;

import com.example.tien.Final.service.IPositionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PositionService implements IPositionService {
    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<PositionDto> findAll(){
        List<PositionDto> positionDtos = new ArrayList<>();
        List<Position> positions = positionRepository.findAll();
        for(Position position : positions){
            PositionDto dto = modelMapper.map(position, PositionDto.class);
            positionDtos.add(dto);
        }
        return positionDtos;

    }
    @Override
    public PositionDto create(PositionDto dto){
        Position position = modelMapper.map(dto, Position.class);
        Position result =positionRepository.save(position);
        return modelMapper.map(result, PositionDto.class);
    }


    public Position getPositionById(Long id){
        return positionRepository.findById(id).orElseThrow(()-> new RuntimeException("Error"));
    }
    @Override
    public PositionDto update(PositionDto positionDto){
        try{
            Position position = positionRepository.findById(positionDto.getId()).orElse(null);
            if(position == null){
                return null;
            }
            if (positionDto.getName() != null) {
                position.setName(positionDto.getName());
            }
            if (positionDto.getBaseSalary() != null) {
                position.setBaseSalary(positionDto.getBaseSalary());
            }
//            position.setName(positionDto.getName());
//            position.setBaseSalary(positionDto.getBaseSalary());
            positionRepository.save(position);

            return modelMapper.map(position, PositionDto.class);
        }catch (Exception e){
            return null;
        }
    }
    @Override
    public String delete(Long id){
        positionRepository.deleteById(id);
        return "Delete Success";
    }

}
