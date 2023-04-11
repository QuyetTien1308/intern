package com.example.tien.Final.payload.response;

import com.example.tien.Final.Dto.RoleDto;
import lombok.*;
import org.springframework.context.annotation.Bean;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleResponse {
    private String message;
    private List<RoleDto> roleDtoList;
    private String status;
}
