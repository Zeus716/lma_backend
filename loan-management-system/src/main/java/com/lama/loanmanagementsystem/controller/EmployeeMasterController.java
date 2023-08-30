package com.lama.loanmanagementsystem.controller;

import com.lama.loanmanagementsystem.model.*;
//import com.lama.loanmanagementsystem.model.employeeIssue;
import com.lama.loanmanagementsystem.repository.*;
//import com.lama.loanmanagementsystem.repository.issueRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.ZoneId;
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
    private LoanRepository loanRep;
    @Autowired
    private ItemRepository itemRep;
    
    @Autowired
    private UserRepository userRep;
    
    @Autowired
    private RoleRepository roleRep;
    
    @Autowired
	PasswordEncoder encoder;
    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    @GetMapping("/user_endpoint/employees")
    @GetMapping("/employees")
    public List<EmployeeMaster> getAllEmployees(){
        return empRep.findAll();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/employees/{id}")
    public ResponseEntity<?> getEmployee(@Valid @PathVariable(value = "id") String empId){
        Optional<EmployeeMaster> employee = empRep.findById(empId);
        if(employee.isPresent()){
            return new ResponseEntity<>(employee, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Employee not found", HttpStatus.OK);
        }
    }

//    @PreAuthorize("hasRole('ROLE_USER')")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/employees")
    public ResponseEntity<?> createEmployee(@Valid @RequestBody EmployeeMaster employee){
        LocalDate dob = employee.getEmployeeDOB().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate doj = employee.getEmployeeDOJ().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        if(!dob.plusYears(18).isBefore(doj)){
            return new ResponseEntity<>("employee must be atleast 18 years old",HttpStatus.OK) ;
        }
    	empRep.save(employee);
    	Set<Role> roles = new HashSet();
    	Optional<Role> userRole = roleRep.findByName(ERole.ROLE_USER);
    	roles.add(userRole.get());
    	UserData user = new UserData(employee.getEmployeeId(), encoder.encode("abcd"), roles);
    	userRep.save(user);
//        return empRep.save(employee);
        return  new ResponseEntity<>(employee, HttpStatus.OK);
    }


//    @PreAuthorize("hasRole('ROLE_USER')")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/employees/{id}")
    public ResponseEntity<?> updateEmployee(@Valid @PathVariable(value = "id") String emp_id,
                                            @Valid @RequestBody EmployeeMaster emp){
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

//    @PreAuthorize("hasRole('ROLE_USER')")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<?> deleteEmployee(@Valid @PathVariable(value = "id") String emp_id){
        List<LoanMaster> loans = loanRep.findByEmployee_EmployeeId(emp_id);
        for(LoanMaster loan:loans){
            loan.setEmployee(null);
            loanRep.delete(loan);
        }
        List<ItemMaster> items = itemRep.findByEmployee_EmployeeId(emp_id);
        for(ItemMaster item: items){
            item.setEmployee(null);
//            itemRep.delete(item);

        }

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
