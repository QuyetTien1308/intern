package com.example.tien.Final.service;

import com.example.tien.Final.Dto.RoleDto;
import com.example.tien.Final.payload.response.RoleResponse;

public interface IRoleService {
    RoleResponse findAll();

    RoleResponse findById(Long id);

    RoleResponse create(RoleDto roleDto);

    RoleResponse updateById(RoleDto roleDto);

    Boolean deleteById(Long id);
}
