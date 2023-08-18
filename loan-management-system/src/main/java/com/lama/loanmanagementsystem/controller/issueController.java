package com.lama.loanmanagementsystem.controller;

import com.lama.loanmanagementsystem.model.employeeIssue;
import com.lama.loanmanagementsystem.repository.issueRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import java.util.Date;
import java.util.Optional;


@RequestMapping
@RestController
public class issueController {
    private issueRepository issueRep;
    @GetMapping("/issues")
    public ResponseEntity<?> getAllIssues(){
        return new ResponseEntity<>(issueRep.findAll(), HttpStatus.OK);
    }


    @GetMapping("/issues/{issue_id}")
    public ResponseEntity<?> getIssuebyId(@PathVariable(value = "issue_id") String issueId){
        Optional<employeeIssue> issue = issueRep.findById(issueId);
        if(issue.isPresent()){
            return new ResponseEntity<>(issue,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("issue",HttpStatus.OK);
        }

    }
    @PostMapping("/issues")
    public ResponseEntity<?> createIssue(@RequestBody employeeIssue issue){
        return new ResponseEntity<>(issueRep.save(issue),HttpStatus.OK);
    }
    @PutMapping("/issues/{id}")
    public ResponseEntity<?> updateIssue(@PathVariable(value = "id") String issueId, @RequestBody employeeIssue issue){
        Optional<employeeIssue> exists = issueRep.findById(issueId);
        if(exists.isPresent()){
            exists.get().setIssueDate(issue.getIssueDate());
            exists.get().setEmployeeId(issue.getEmployeeId());
            exists.get().setItemId(issue.getItemId());
            exists.get().setReturnDate(issue.getReturnDate());
            issueRep.save(exists.get());
            return new ResponseEntity<>(exists.get(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Issue id not present", HttpStatus.OK);
        }

    }


}
