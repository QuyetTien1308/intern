package com.example.tien.Final.service;

import com.example.tien.Final.Dto.PositionDto;
import com.example.tien.Final.entity.Position;

import java.util.List;

public interface IPositionService {
    List<PositionDto> findAll();

    PositionDto create(PositionDto dto);


    PositionDto update(PositionDto positionDto);


    String delete(Long id);
}
