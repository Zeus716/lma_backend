package com.lama.loanmanagementsystem.payload.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
	private String employeeId;

	private String password;
}