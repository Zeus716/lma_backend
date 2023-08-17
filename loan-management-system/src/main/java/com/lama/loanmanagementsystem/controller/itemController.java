package com.lama.loanmanagementsystem.controller;

import com.lama.loanmanagementsystem.model.employeeIssue;
import com.lama.loanmanagementsystem.model.itemMaster;
import com.lama.loanmanagementsystem.repository.itemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

}
