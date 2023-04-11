package com.example.tien.Final.payload.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {
	private Integer id;
	private String username;
	private Object roles;

	private String access_token;
	private String token_type;
	
}
