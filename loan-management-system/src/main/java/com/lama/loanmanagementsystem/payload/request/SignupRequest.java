package com.lama.loanmanagementsystem.payload.request;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {
	private Set<String> role;

	private String password;
	
	SignupRequest(String password) {
		this.password = password;
	}
}
