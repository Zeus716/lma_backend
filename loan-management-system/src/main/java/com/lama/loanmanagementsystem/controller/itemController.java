package com.lama.loanmanagementsystem.controller;

import com.lama.loanmanagementsystem.model.employeeIssue;
import com.lama.loanmanagementsystem.model.employeeMaster;
import com.lama.loanmanagementsystem.model.itemMaster;
import com.lama.loanmanagementsystem.repository.employeeMasterRepository;
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
    @Autowired
    private employeeMasterRepository empRep;

    @GetMapping("/items")
    public ResponseEntity<?> getAllItems(){
        return new ResponseEntity<>(itemrep.findAll(), HttpStatus.OK);
    }
    @PostMapping("/items")
    public ResponseEntity<?>  createItems(@RequestBody itemMaster item){
        return new ResponseEntity<>(itemrep.save(item),HttpStatus.OK);
    }
    @PostMapping("/employees/{id}/items")
    public ResponseEntity<?> updateItemEmployee(@PathVariable(value="id") String employeeId, @RequestBody itemMaster item){
        Optional<employeeMaster> employee = empRep.findById(employeeId);
        if(employee.isEmpty()){
            return new ResponseEntity<>("employee not present",HttpStatus.OK);
        }
        else{
            item.setEmployee(employee.get());
            itemrep.save(item);
            return new ResponseEntity<>(item,HttpStatus.OK);
        }
    }
    @GetMapping("employees/{id}/items")
    public ResponseEntity<?> getAllItemsofEmployee(@PathVariable(value = "id")String employeeId){
        Optional<employeeMaster> employee =empRep.findById(employeeId);
        if(employee.isEmpty()){
            return new ResponseEntity<>("employee not present",HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(itemrep.findByEmployee_EmployeeId(employeeId),HttpStatus.OK);
        }
    }

//    @PutMapping("/items/{item_id}/employee")
//    public ResponseEntity<?> addEmployeeToItem(@PathVariable(value = "item_id") String itemId,@RequestBody employeeMaster employee){
//        Optional<itemMaster> item = itemrep.findById(itemId);
//        if(item.isEmpty()){
//            return new ResponseEntity<>("item not found",HttpStatus.OK);
//        }
//        else{
//            itemMaster item1 = item.get();
//            item1.setEmployeeId(employee);
//            itemrep.save(item1);
//            return new ResponseEntity<>(item1,HttpStatus.OK);
//        }
//    }
//    @GetMapping("/items/{emp_id}")
//    public ResponseEntity<?> getItemsByempId(@PathVariable(value = "emp_id") String empId){
//        return new ResponseEntity<>(itemrep.findByEmployeeId_EmployeeId(empId),HttpStatus.OK);
//    }
    @DeleteMapping("/items/{item_id}")
    public ResponseEntity<?> deleteItem(@PathVariable(value = "item_id") String itemId){
        Optional<itemMaster> item = itemrep.findById(itemId);
        if(item.isEmpty()){
            return new ResponseEntity<>("item not found",HttpStatus.OK);
        }
        else{
            item.ifPresent(itemrep::delete);
            return new ResponseEntity<>("item deleted",HttpStatus.OK);
        }
    }
}
