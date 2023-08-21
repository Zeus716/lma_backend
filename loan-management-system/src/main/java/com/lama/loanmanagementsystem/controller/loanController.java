package com.lama.loanmanagementsystem.controller;

import com.lama.loanmanagementsystem.model.loanMaster;
import com.lama.loanmanagementsystem.repository.loanRepository;
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
public class loanController {
    @Autowired
    public loanRepository loanRep;

    @GetMapping("/loans/{id}")
    public ResponseEntity<?> getLoans(@PathVariable(value = "id") String loan_id) {
        Optional<loanMaster> loan = loanRep.findById(loan_id);
        if (loan.isPresent()) {
            return new ResponseEntity<>(loan, HttpStatus.OK);
        } else return new ResponseEntity<>("User doesn't exist with id :" + loan_id, HttpStatus.OK);
    }

    @PostMapping("/loans")
    public ResponseEntity<?> createLoans(@RequestBody loanMaster loan){
        loanMaster createdLoan = loanRep.save(loan);
        return new ResponseEntity<>(createdLoan,HttpStatus.OK);
//         return loanRep.save(loan);
    }
    @PutMapping("/loans/{id}") // resets values if not provided nned to fix
    public ResponseEntity<?> updateLoans(@PathVariable(value="id") String loan_id,
                                         @RequestBody loanMaster loan){
        Optional<loanMaster> loanexists = loanRep.findById(loan_id);
        if (loanexists.isEmpty()) {
            return new ResponseEntity<>("loan id not found",HttpStatus.OK);
        }
        else{
            if(!loan.getLoanType().isEmpty()){
                loanexists.get().setLoanType(loan.getLoanType());
            }
            if(!loan.getDurationInMonths().equals(0)){
                loanexists.get().setDurationInMonths(loan.getDurationInMonths());
            }
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
