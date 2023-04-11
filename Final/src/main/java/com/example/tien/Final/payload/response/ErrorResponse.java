package com.example.tien.Final.payload.response;


import com.example.tien.Final.exception.SysError;
import lombok.*;

@Getter
@Setter
//@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

	private String message;
	private SysError sysError;


}
