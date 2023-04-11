package com.example.tien.Final.payload.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SuccessReponse {
	private String message;
	private Object data;
    private String status;
}
