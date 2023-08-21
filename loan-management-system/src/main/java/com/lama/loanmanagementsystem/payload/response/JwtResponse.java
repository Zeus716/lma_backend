package com.lama.loanmanagementsystem.payload.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponse {
	  private String token;
	  private String type = "Bearer";
	  private String id;
	  private List<String> roles;

	  public JwtResponse(String accessToken, String id, List<String> roles) {
	    this.token = accessToken;
	    this.id = id;
	    this.roles = roles;
	  }
}
