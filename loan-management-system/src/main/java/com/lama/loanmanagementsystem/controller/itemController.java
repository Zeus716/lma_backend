package com.lama.loanmanagementsystem.controller;

import com.lama.loanmanagementsystem.model.employeeIssue;
import com.lama.loanmanagementsystem.model.employeeMaster;
import com.lama.loanmanagementsystem.model.itemMaster;
import com.lama.loanmanagementsystem.repository.itemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping
@RestController
public class itemController {
    @Autowired
    private itemRepository itemrep ;

    @GetMapping("/items")
    public ResponseEntity<?> getAllItems(){
        return new ResponseEntity<>(itemrep.findAll(), HttpStatus.OK);
    }
    @PostMapping("/items")
    public ResponseEntity<?>  createItems(@RequestBody itemMaster item){
        return new ResponseEntity<>(itemrep.save(item),HttpStatus.OK);
    }

    @GetMapping("/items/{emp_id}")
    public ResponseEntity<?> getItemsByempId(@PathVariable(value = "emp_id") String empId){
        return new ResponseEntity<>(itemrep.findByEmployeeId_EmployeeId(empId),HttpStatus.OK);
    }
    @DeleteMapping("/items/{item_id}")
    public ResponseEntity<?> deleteItem(@PathVariable(value = "item_id") String itemId){
        Optional<itemMaster> item = itemrep.findById(itemId);
        if(item.isEmpty()){
            return new ResponseEntity<>("employee not found",HttpStatus.OK);
        }
        else{
            item.ifPresent(itemrep::delete);
            return new ResponseEntity<>("employee deleted",HttpStatus.OK);
        }
    }
}
