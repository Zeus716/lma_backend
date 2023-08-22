// <<<<<<< itemissuerelationship
// //package com.lama.loanmanagementsystem.controller;
// //
// //import com.lama.loanmanagementsystem.model.employeeIssue;
// //import com.lama.loanmanagementsystem.model.employeeMaster;
// //import com.lama.loanmanagementsystem.repository.employeeMasterRepository;
// //import com.lama.loanmanagementsystem.repository.issueRepository;
// //import com.lama.loanmanagementsystem.repository.itemRepository;
// //import org.springframework.beans.factory.annotation.Autowired;
// //import org.springframework.http.HttpStatus;
// //import org.springframework.http.ResponseEntity;
// //import org.springframework.stereotype.Controller;
// //import org.springframework.stereotype.Repository;
// //import org.springframework.web.bind.annotation.*;
// //
// //import javax.persistence.Id;
// //import java.util.Date;
// //import java.util.Optional;
// //
// //
// //@RequestMapping
// //@RestController
// //public class issueController {
// //    @Autowired
// //    private issueRepository issueRep;
// //    @Autowired
// //    private employeeMasterRepository empRep;
// //    @Autowired
// //    private itemRepository itemRep;
// //
// //    @GetMapping("/issues")
// //    public ResponseEntity<?> getAllIssues(){
// //        return new ResponseEntity<>(issueRep.findAll(), HttpStatus.OK);
// =======
// package com.lama.loanmanagementsystem.controller;

// import com.lama.loanmanagementsystem.model.employeeIssue;
// import com.lama.loanmanagementsystem.model.employeeMaster;
// import com.lama.loanmanagementsystem.repository.employeeMasterRepository;
// import com.lama.loanmanagementsystem.repository.issueRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Controller;
// import org.springframework.stereotype.Repository;
// import org.springframework.web.bind.annotation.*;

// import javax.persistence.Id;
// import java.util.Date;
// import java.util.Optional;


// @RequestMapping
// @RestController
// @CrossOrigin("*")

// public class issueController {
//     @Autowired
//     private issueRepository issueRep;
//     @Autowired
//     private employeeMasterRepository empRep;

//     @GetMapping("/issues")
//     public ResponseEntity<?> getAllIssues(){
//         return new ResponseEntity<>(issueRep.findAll(), HttpStatus.OK);
//     }


//     @GetMapping("/issues/{issue_id}")
//     public ResponseEntity<?> getIssuebyId(@PathVariable(value = "issue_id") String issueId){
//         Optional<employeeIssue> issue = issueRep.findById(issueId);
//         if(issue.isPresent()){
//             return new ResponseEntity<>(issue,HttpStatus.OK);
//         }
//         else{
//             return new ResponseEntity<>("issue",HttpStatus.OK);
//         }

//     }
// //    @PostMapping("/issues")
// //    public ResponseEntity<?> createIssue(@RequestBody employeeIssue issue){
// //        return new ResponseEntity<>(issueRep.save(issue),HttpStatus.OK);
// >>>>>>> master
// //    }
// //
// //    @GetMapping("/issues/{issue_id}/items")
// //    public ResponseEntity<?> getAllItems(@PathVariable(value = "issue_id")String issueId){
// //        Optional<employeeIssue> issue = issueRep.findById(issueId);
// //        if(issue.isEmpty()){
// //            return new ResponseEntity<>("issue not found",HttpStatus.OK);
// //        }
// //        else{
// //            employeeMaster empId = issue.get().getEmployeeId();
// //            return new ResponseEntity<>(itemRep.findByEmployee_EmployeeId(empId.getEmployeeId()),HttpStatus.OK);
// //        }
// //    }
// //    @GetMapping("/issues/{issue_id}")
// //    public ResponseEntity<?> getIssuebyId(@PathVariable(value = "issue_id") String issueId){
// //        Optional<employeeIssue> issue = issueRep.findById(issueId);
// //        if(issue.isPresent()){
// //            return new ResponseEntity<>(issue,HttpStatus.OK);
// //        }
// //        else{
// //            return new ResponseEntity<>("issue",HttpStatus.OK);
// //        }
// //
// //    }
// ////    @PostMapping("/issues")
// ////    public ResponseEntity<?> createIssue(@RequestBody employeeIssue issue){
// ////        return new ResponseEntity<>(issueRep.save(issue),HttpStatus.OK);
// ////    }
// //    @PostMapping("employees/{id}/issues")
// //    public ResponseEntity<?> createIssue(@PathVariable(value = "id") String employeeId, @RequestBody employeeIssue issue){
// //        Optional<employeeMaster> employee = empRep.findById(employeeId);
// //        if(employee.isPresent()){
// //            issue.setEmployeeId(employee.get());
// //            issueRep.save(issue);
// //            return new ResponseEntity<>(issue,HttpStatus.OK);
// //        }
// //        else{
// //            return new ResponseEntity<>("employee not present",HttpStatus.OK);
// //        }
// //    }
// ////    @GetMapping("/issues/items")
// //    @PutMapping("/issues/{id}")
// //    public ResponseEntity<?> updateIssue(@PathVariable(value = "id") String issueId, @RequestBody employeeIssue issue){
// //        Optional<employeeIssue> exists = issueRep.findById(issueId);
// //        if(exists.isPresent()){
// //            exists.get().setIssueDate(issue.getIssueDate());
// //            exists.get().setEmployeeId(issue.getEmployeeId());
// ////            exists.get().setItemId(issue.getItemId());
// //            exists.get().setReturnDate(issue.getReturnDate());
// //            issueRep.save(exists.get());
// //            return new ResponseEntity<>(exists.get(), HttpStatus.OK);
// //        }
// //        else{
// //            return new ResponseEntity<>("Issue id not present", HttpStatus.OK);
// //        }
// //
// //    }
// //
// //
// //}
