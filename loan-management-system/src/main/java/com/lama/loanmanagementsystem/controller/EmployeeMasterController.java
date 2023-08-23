package com.lama.loanmanagementsystem.controller;

import com.lama.loanmanagementsystem.model.ERole;
//import com.lama.loanmanagementsystem.model.employeeIssue;
import com.lama.loanmanagementsystem.model.EmployeeMaster;
import com.lama.loanmanagementsystem.model.Role;
import com.lama.loanmanagementsystem.model.UserData;
import com.lama.loanmanagementsystem.repository.EmployeeMasterRepository;
//import com.lama.loanmanagementsystem.repository.issueRepository;
import com.lama.loanmanagementsystem.repository.ItemRepository;
import com.lama.loanmanagementsystem.repository.RoleRepository;
import com.lama.loanmanagementsystem.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping
@CrossOrigin("*")
//checked all working
public class EmployeeMasterController {
    @Autowired
    private EmployeeMasterRepository empRep;
    @Autowired
    private ItemRepository itemRep;
    
    @Autowired
    private UserRepository userRep;
    
    @Autowired
    private RoleRepository roleRep;
    
    @Autowired
	PasswordEncoder encoder;

    @GetMapping("/employees")
    public List<EmployeeMaster> getAllEmployees(){
        return empRep.findAll();
    }


    @GetMapping("/employees/{id}")
    public ResponseEntity<?> getEmployee(@PathVariable(value = "id") String empId){
        Optional<EmployeeMaster> employee = empRep.findById(empId);
        if(employee.isPresent()){
            return new ResponseEntity<>(employee, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Employee not found", HttpStatus.OK);
        }
    }


    @PostMapping("/employees")
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeMaster employee){
    	empRep.save(employee);
    	Set<Role> roles = new HashSet();
    	Optional<Role> userRole = roleRep.findByName(ERole.ROLE_USER);
    	roles.add(userRole.get());
    	UserData user = new UserData(employee.getEmployeeId(), encoder.encode("abcd"), roles);
    	userRep.save(user);
//        return empRep.save(employee);
        return  new ResponseEntity<>(employee, HttpStatus.OK);
    }


    @PutMapping("/employees/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable(value = "id") String emp_id, @RequestBody EmployeeMaster emp){
        Optional<EmployeeMaster> employee = empRep.findById(emp_id);
        if(employee.isEmpty()){
            return new ResponseEntity<>("employee not found",HttpStatus.OK);
        }
        else{
            employee.get().setEmployeeName(emp.getEmployeeName());
            employee.get().setEmployeeDOB(emp.getEmployeeDOB());
            employee.get().setEmployeeDOJ(emp.getEmployeeDOJ());
            employee.get().setEmployeeDesignation(emp.getEmployeeDesignation());
            employee.get().setGender(emp.getGender());
            employee.get().setEmployeeDepartment(emp.getEmployeeDepartment());
            empRep.save(employee.get());
            return new ResponseEntity<>(employee.get(),HttpStatus.OK);
        }
    }


    @DeleteMapping("/employees/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable(value = "id") String emp_id){
        Optional<EmployeeMaster> employee = empRep.findById(emp_id);
        if(employee.isEmpty()){
            return new ResponseEntity<>("employee not found",HttpStatus.OK);
        }
        else{
            employee.ifPresent(empRep::delete);
            return new ResponseEntity<>("employee deleted",HttpStatus.OK);
        }
    }


}
