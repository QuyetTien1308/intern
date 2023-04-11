package com.example.tien.Final.exception;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class SysError {

	private String code;
	private ErrorParam errorParams;


}
