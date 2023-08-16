package com.lama.loanmanagementsystem.controller;

import com.lama.loanmanagementsystem.model.userData;
import com.lama.loanmanagementsystem.repository.userRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping
@RestController
public class userController {
    @Autowired
    private userRepository userrep;
    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUser(@PathVariable(value="id") Integer employee_id){
        Optional<userData> user = userrep.findById(employee_id);
        if(user.isPresent()) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        else return new ResponseEntity<>("User doesn't exist with id :"+employee_id, HttpStatus.OK);
    }

    @GetMapping("/users")
    public List<userData> getUsers() {
        return userrep.findAll();
    }

    @PostMapping("/users")
    public userData createUser(@Validated @RequestBody userData user){

        System.out.println(user);
        return userrep.save(user);
    }
    @Transactional
    @PutMapping("/users/{id}")
    public ResponseEntity<String> updateUser( @PathVariable(value = "id") Integer employee_id,
                                              @RequestBody userData user){
        Optional<userData> exists = userrep.findById(employee_id);
        if(exists.isEmpty()){
            return new ResponseEntity<String>("User does not exist",HttpStatus.OK);
        }
        else{
            if(!user.getPassword().isEmpty()){
                exists.get().setPassword(user.getPassword());
            }
            if(!user.getIsAdmin().isEmpty()){
                exists.get().setIsAdmin(user.getIsAdmin());
            }
            return new ResponseEntity<String>(String.format("User id %d updated",employee_id),HttpStatus.OK);
        }
    }
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Integer employee_id){
        boolean exists = userrep.existsById(employee_id);
        if(!exists){
            return new ResponseEntity<String>("User does not exist",HttpStatus.OK);
        }
        else{
            userrep.deleteById(employee_id);
            return new ResponseEntity<String>(String.format("User id %d deleted",employee_id),HttpStatus.OK);
        }

    }
    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<String> checkUser(@RequestBody userData user){
        Optional<userData> emp = userrep.findById(user.getEmployee_id());
        if(emp.isPresent()){
            if(emp.get().getPassword().equals(user.getPassword())){
                return new ResponseEntity<>(emp.get().getIsAdmin(), HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>("invalid password",HttpStatus.OK);
            }
        }
        else{
            return new ResponseEntity<>("user_not_present",HttpStatus.OK);
        }
    }

}
