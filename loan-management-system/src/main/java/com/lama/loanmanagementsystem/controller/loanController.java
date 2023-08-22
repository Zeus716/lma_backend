package com.lama.loanmanagementsystem.controller;

import com.lama.loanmanagementsystem.model.employeeMaster;
import com.lama.loanmanagementsystem.model.loanMaster;
import com.lama.loanmanagementsystem.model.loanType;
import com.lama.loanmanagementsystem.repository.employeeMasterRepository;
import com.lama.loanmanagementsystem.repository.loanRepository;
import com.lama.loanmanagementsystem.repository.loanTypeRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping
@RestController
@CrossOrigin("*")

public class loanController {
    @Autowired
    public loanRepository loanRep;
    @Autowired
    public employeeMasterRepository empRep;
    @Autowired
    public loanTypeRepository loanTypeRep;

    @GetMapping("/loans/{id}")
    public ResponseEntity<?> getLoans(@PathVariable(value = "id") String loan_id) {
        Optional<loanMaster> loan = loanRep.findById(loan_id);
        if (loan.isPresent()) {
            return new ResponseEntity<>(loan, HttpStatus.OK);
        } else return new ResponseEntity<>("User doesn't exist with id :" + loan_id, HttpStatus.OK);
    }

//    @PostMapping("/loans")
//    public ResponseEntity<?> createLoans(@RequestBody loanMaster loan){
//        loanMaster createdLoan = loanRep.save(loan);
//        return new ResponseEntity<>(createdLoan,HttpStatus.OK);
////         return loanRep.save(loan);
//    }

    @PutMapping("/loans/{id}/employees/{employee_id}") // resets values if not provided nned to fix
    public ResponseEntity<?> updateLoans(@PathVariable(value="id") String loan_id,@PathVariable(value = "employee_id")String employeeId,
                                         @RequestBody loanMaster loan){
        Optional<loanMaster> loanexists = loanRep.findById(loan_id);
        if (loanexists.isEmpty()) {
            return new ResponseEntity<>("loan id not found",HttpStatus.OK);
        }
        else{
            Optional<employeeMaster> employee =empRep.findById(employeeId);
            if(employee.isEmpty()){
                return new ResponseEntity<>("employee id not found",HttpStatus.OK);
            }
            loanexists.get().setEmployee(employee.get());
            loanRep.save(loanexists.get());
            return new ResponseEntity<>(loanexists,HttpStatus.OK);
        }
    }

    @PostMapping("/loans/loantype/{loanType_id}") // resets values if not provided nned to fix
    public ResponseEntity<?> createLoansLoanType(@PathVariable(value = "loanType_id")String loanTypeId
                                                 ){
        Optional<loanType> loanexists = loanTypeRep.findById(loanTypeId);
        if (loanexists.isEmpty()) {
            return new ResponseEntity<>("loan id not found",HttpStatus.OK);
        }
        else{
            loanMaster loan = new loanMaster(loanexists.get());
//            loan.setLoanType(loanexists.get());
            loanRep.save(loan);
            return new ResponseEntity<>(loan,HttpStatus.OK);
        }
    }
    @GetMapping("employees/{emp_id}/loans")
    public ResponseEntity<?> getAllLoansbyEmployee(@PathVariable(value = "emp_id")String employeeId){
        Optional<employeeMaster> employee =empRep.findById(employeeId);
        if(employee.isEmpty()){
            return new ResponseEntity<>("employee doesn't exist",HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(loanRep.findByEmployee_EmployeeId(employeeId),HttpStatus.OK);
        }
    }

//    @PostMapping("loans/employee/{emp_id}")
//    public ResponseEntity<?> createLoansbyEmployee(@PathVariable(value = "emp_id")String employeeId){
//        Optional<employeeMaster> employee = empRep.findById(employeeId);
//        if(employee.isEmpty()){
//            return new ResponseEntity<>("employee not found", HttpStatus.OK);
//        }
//        else{
//            loanMaster loan = new loanMaster(UUID.randomUUID().toString(),,employee.get());
////            loan.setEmployee(employee.get());
//            loanRep.save(loan);
//            return new ResponseEntity<>(loan,HttpStatus.OK);
//        }
//    }
    @PostMapping("loans/employees/{emp_id}/loantype/{id}")
    public ResponseEntity<?> createLoans(@PathVariable(value = "emp_id")String employeeId,@PathVariable(value = "id")String loanTypeId){
        Optional<employeeMaster> employee = empRep.findById(employeeId);
        if(employee.isEmpty()){
            return new ResponseEntity<>("employee not found", HttpStatus.OK);
        }
        else{
            Optional<loanType> loanexists = loanTypeRep.findById(loanTypeId);
            if (loanexists.isEmpty()) {
                return new ResponseEntity<>("loan id not found",HttpStatus.OK);
            }
            else{
                loanMaster loan = new loanMaster(loanexists.get(),employee.get());
//                loan.setLoanType(loanexists.get());
//                loan.setEmployee(employee.get());
                loanRep.save(loan);
                return new ResponseEntity<>(loan,HttpStatus.OK);
            }
        }

    }


    @PutMapping("/loans/{id}/loanType/{loanType_id}") // resets values if not provided nned to fix
    public ResponseEntity<?> updateLoansLoanType(@PathVariable(value="id") String loan_id,@PathVariable(value = "loanType_id")String loanTypeId,
                                         @RequestBody loanMaster loan){
        Optional<loanMaster> loanexists = loanRep.findById(loan_id);
        if (loanexists.isEmpty()) {
            return new ResponseEntity<>("loan id not found",HttpStatus.OK);
        }
        else{
            Optional<loanType> loanType =loanTypeRep.findById(loanTypeId);
            if(loanType.isEmpty()){
                return new ResponseEntity<>("loan not found",HttpStatus.OK);
            }
            loanexists.get().setLoanType(loanType.get());
            loanRep.save(loanexists.get());
            return new ResponseEntity<>(loanexists,HttpStatus.OK);
        }
    }

    @GetMapping("/loans")
    public ResponseEntity<?> getAllLoans(){
        return new ResponseEntity<>(loanRep.findAll(),HttpStatus.OK);
    }

    @DeleteMapping("/loans/{id}")
    public ResponseEntity<?> deleteLoan(@PathVariable(value = "id") String loan_id){
        Optional<loanMaster> loanexists = loanRep.findById(loan_id);
        if (loanexists.isEmpty()) {
            return new ResponseEntity<>("Loan not found",HttpStatus.OK);
        }
        else{
            loanexists.ifPresent(loanRep::delete);
            return new ResponseEntity<>("Loan deleted", HttpStatus.OK);
        }
    }
}
