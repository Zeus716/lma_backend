package com.lama.loanmanagementsystem.controller;

import com.lama.loanmanagementsystem.model.userData;
import com.lama.loanmanagementsystem.repository.userRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping
@RestController
public class userController {
    @Autowired
    private userRepository userrep;
    @GetMapping("/users/{id}")
    public ResponseEntity<Optional<userData>> getUser(@PathVariable(value="id") Integer employee_id){
        Optional<userData> user = userrep.findById(employee_id);
        if(user.isPresent()) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/users")
    public userData Create_user(@Validated @RequestBody userData user){
        return userrep.save(user);
    }
    @PostMapping("/login")
    public ResponseEntity<String> check_user(@RequestBody userData user){
        Optional<userData> emp = userrep.findById(user.getEmployee_id());
        if(emp.isPresent()){
            return new ResponseEntity<>(user.getIsAdmin(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("user_not_present",HttpStatus.NOT_FOUND);
        }
    }

}
