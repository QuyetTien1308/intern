package com.example.tien.Final.payload.request;

import lombok.Data;

@Data
public class SignupRequest {

	private String username;
	
	private String password;
	private String name;
	private Long position;
	private String role;

}
