package com.lama.loanmanagementsystem.controller;

import com.lama.loanmanagementsystem.model.EmployeeMaster;
import com.lama.loanmanagementsystem.model.LoanMaster;
import com.lama.loanmanagementsystem.model.LoanType;
import com.lama.loanmanagementsystem.repository.EmployeeMasterRepository;
import com.lama.loanmanagementsystem.repository.LoanRepository;
import com.lama.loanmanagementsystem.repository.LoanTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping
@RestController
@CrossOrigin("*")

public class LoanController {
    @Autowired
    public LoanRepository loanRep;
    @Autowired
    public EmployeeMasterRepository empRep;
    @Autowired
    public LoanTypeRepository loanTypeRep;

    @GetMapping("/loans/{id}")
    public ResponseEntity<?> getLoans(@PathVariable(value = "id") String loan_id) {
        Optional<LoanMaster> loan = loanRep.findById(loan_id);
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

    @PutMapping("/loans/{id}/employees/{employee_id}")
    public ResponseEntity<?> updateLoans(@PathVariable(value="id") String loan_id,@PathVariable(value = "employee_id")String employeeId,
                                         @RequestBody LoanMaster loan){
        Optional<LoanMaster> loanexists = loanRep.findById(loan_id);
        if (loanexists.isEmpty()) {
            return new ResponseEntity<>("loan id not found",HttpStatus.OK);
        }
        else{
            Optional<EmployeeMaster> employee =empRep.findById(employeeId);
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
        Optional<LoanType> loanexists = loanTypeRep.findById(loanTypeId);
        if (loanexists.isEmpty()) {
            return new ResponseEntity<>("loan id not found",HttpStatus.OK);
        }
        else{
            LoanMaster loan = new LoanMaster(loanexists.get());
//            loan.setLoanType(loanexists.get());
            loanRep.save(loan);
            return new ResponseEntity<>(loan,HttpStatus.OK);
        }
    }
    @GetMapping("employees/{emp_id}/loans")
    public ResponseEntity<?> getAllLoansbyEmployee(@PathVariable(value = "emp_id")String employeeId){
        Optional<EmployeeMaster> employee =empRep.findById(employeeId);
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
        Optional<EmployeeMaster> employee = empRep.findById(employeeId);
        if(employee.isEmpty()){
            return new ResponseEntity<>("employee not found", HttpStatus.OK);
        }
        else{
            Optional<LoanType> loanexists = loanTypeRep.findById(loanTypeId);
            if (loanexists.isEmpty()) {
                return new ResponseEntity<>("loan id not found",HttpStatus.OK);
            }
            else{
                LoanMaster loan = new LoanMaster(loanexists.get(),employee.get());
//                loan.setLoanType(loanexists.get());
//                loan.setEmployee(employee.get());
                loanRep.save(loan);
                return new ResponseEntity<>(loan,HttpStatus.OK);
            }
        }

    }


    @PutMapping("/loans/{id}/loanType/{loanType_id}") // resets values if not provided nned to fix
    public ResponseEntity<?> updateLoansLoanType(@PathVariable(value="id") String loan_id,@PathVariable(value = "loanType_id")String loanTypeId,
                                         @RequestBody LoanMaster loan){
        Optional<LoanMaster> loanexists = loanRep.findById(loan_id);
        if (loanexists.isEmpty()) {
            return new ResponseEntity<>("loan id not found",HttpStatus.OK);
        }
        else{
            Optional<LoanType> loanType =loanTypeRep.findById(loanTypeId);
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
        Optional<LoanMaster> loanexists = loanRep.findById(loan_id);
        if (loanexists.isEmpty()) {
            return new ResponseEntity<>("Loan not found",HttpStatus.OK);
        }
        else{
            loanexists.ifPresent(loanRep::delete);
            return new ResponseEntity<>("Loan deleted", HttpStatus.OK);
        }
    }
}
