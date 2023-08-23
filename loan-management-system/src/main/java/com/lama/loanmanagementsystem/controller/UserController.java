package com.lama.loanmanagementsystem.controller;

import com.lama.loanmanagementsystem.model.UserData;
import com.lama.loanmanagementsystem.repository.RoleRepository;
import com.lama.loanmanagementsystem.repository.UserRepository;
import com.lama.loanmanagementsystem.security.jwt.JwtUtils;
import com.lama.loanmanagementsystem.security.services.UserDetailsImpl;
import com.lama.loanmanagementsystem.model.ERole;
import com.lama.loanmanagementsystem.model.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;

import com.lama.loanmanagementsystem.payload.request.LoginRequest;
import com.lama.loanmanagementsystem.payload.request.SignupRequest;
import com.lama.loanmanagementsystem.payload.response.JwtResponse;
import com.lama.loanmanagementsystem.payload.response.MessageResponse;

@RequestMapping
@RestController
@CrossOrigin("*")

public class UserController {
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

    @Autowired
    private UserRepository userrep;
    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUser(@PathVariable(value="id") String employeeId){
        Optional<UserData> user = userrep.findById(employeeId);
        if(user.isPresent()) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        else return new ResponseEntity<>("User doesn't exist with id :"+employeeId, HttpStatus.OK);
    }

    @GetMapping("/users")
    public List<UserData> getUsers() {
        return userrep.findAll();
    }

    @PostMapping("/users")
    public ResponseEntity<?> createUser(@Validated @RequestBody SignupRequest signUpRequest){
		// Create new user's account
		UserData user = new UserData(signUpRequest.getId(), encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userrep.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
    @Transactional
    @PutMapping("/users/{id}")
    public ResponseEntity<String> updateUser( @PathVariable(value = "id") String employee_id,
                                              @RequestBody UserData user){
        Optional<UserData> exists = userrep.findById(employee_id);
        if(exists.isEmpty()){
            return new ResponseEntity<String>("User does not exist",HttpStatus.OK);
        }
        else{
                exists.get().setPassword(user.getPassword());
            return new ResponseEntity<String>(String.format("User id %d updated",employee_id),HttpStatus.OK);
        }
    }
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") String employeeId){
        boolean exists = userrep.existsById(employeeId);
        if(!exists){
            return new ResponseEntity<String>("User does not exist",HttpStatus.OK);
        }
        else{
            userrep.deleteById(employeeId);
            return new ResponseEntity<String>(String.format("User id %d deleted",employeeId),HttpStatus.OK);
        }

    }
    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<?> checkUser(@RequestBody LoginRequest loginRequest){
    	Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmployeeId(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(
				new JwtResponse(jwt, userDetails.getId(), roles));
    	
//    	Optional<userData> emp = userrep.findById(user.getEmployeeId());
//        if(emp.isPresent()){
//            if(emp.get().getPassword().equals(user.getPassword())){
//                return new ResponseEntity<>(HttpStatus.OK);
//            }
//            else{
//                return new ResponseEntity<>("invalid password",HttpStatus.OK);
//            }
//        }
//        else{
//            return new ResponseEntity<>("user_not_present",HttpStatus.OK);
//        }
    }

}