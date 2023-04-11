package com.example.tien.Final.service.impl;

import com.example.tien.Final.Dto.RoleDto;
import com.example.tien.Final.entity.Role;
import com.example.tien.Final.payload.response.RoleResponse;
import com.example.tien.Final.repos.RoleRepository;
import com.example.tien.Final.service.IRoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class RoleService implements IRoleService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RoleRepository roleRepository;
    @Override
    public RoleResponse findAll(){
        try {
            List<Role> roles = roleRepository.findAll();
            List<RoleDto> roleDtos = new ArrayList<>();
            for(Role role : roles){
                RoleDto roleDto = modelMapper.map(role, RoleDto.class);
                roleDtos.add(roleDto);
            }
            RoleResponse roleResponse = new RoleResponse();
            roleResponse.setMessage("Success");
            roleResponse.setRoleDtoList(roleDtos);
            roleResponse.setStatus("Ok");
            return roleResponse;
        }catch (Exception e){
            return new RoleResponse();
        }
    }
    @Override
    public RoleResponse findById(Long id){
        try{
            List<RoleDto> roleDtos = new ArrayList<>();
            Role role = roleRepository.findById(id).orElse(new Role());
            RoleDto roleDto = modelMapper.map(role, RoleDto.class);
            roleDtos.add(roleDto);
            RoleResponse roleResponse = new RoleResponse();
            roleResponse.setMessage("Success");
            roleResponse.setRoleDtoList(roleDtos);
            roleResponse.setStatus("Ok");
            return roleResponse;
        }catch (Exception e){
            return new RoleResponse();
        }
    }
    @Override
    public RoleResponse create(RoleDto roleDto){
        try{
            Role role =modelMapper.map(roleDto, Role.class);
            Role result = roleRepository.save(role);
            RoleResponse response = new RoleResponse();
            if(result!= null){
                List<RoleDto> roleDtos =new ArrayList<>();
                roleDtos.add(roleDto);
                response.setMessage("Success");
                response.setRoleDtoList(roleDtos);
                response.setStatus("Ok");
            }
            return response;
        }catch (Exception e){
            return new RoleResponse();
        }
    }
    @Override
    public RoleResponse updateById(RoleDto roleDto){
        try {
            Role role = roleRepository.findById(roleDto.getId()).orElse(new Role());
            RoleDto dto = new RoleDto();
            if(role != null){
                roleRepository.save(role);
                dto = modelMapper.map(role, RoleDto.class);
            }
            List<RoleDto> roleDtos = new ArrayList<>();
            roleDtos.add(dto);
            RoleResponse response = new RoleResponse();
            response.setMessage("Success");
            response.setRoleDtoList(roleDtos);
            response.setStatus("Ok");
            return response;
        }catch (Exception e){
            return new RoleResponse();
        }
    }
    @Override
    public Boolean deleteById(Long id){
        try{
            roleRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
