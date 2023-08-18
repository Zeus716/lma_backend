package com.lama.loanmanagementsystem.controller;

import com.lama.loanmanagementsystem.model.employeeMaster;
import com.lama.loanmanagementsystem.model.itemMaster;
import com.lama.loanmanagementsystem.repository.employeeMasterRepository;
import com.lama.loanmanagementsystem.repository.itemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class EmployeeMasterController {
    @Autowired
    private employeeMasterRepository empRep;
    private itemRepository itemRep;
    @GetMapping("/employees")
    public List<employeeMaster> getAllEmployees(){
        return empRep.findAll();
    }
    @GetMapping("/employees/{id}")
    public ResponseEntity<?> getEmployee(@PathVariable(value = "id") String empId){
        Optional<employeeMaster> employee = empRep.findById(empId);
        if(employee.isPresent()){
            return new ResponseEntity<>(employee, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Employee not found", HttpStatus.OK);
        }
    }
    @PostMapping("/employees/{id}/items")
    public ResponseEntity<?> createItem(@PathVariable(value = "id") String employeeId,@RequestBody itemMaster item){
        Optional<employeeMaster> employee = empRep.findById(employeeId);
        if(employee.isEmpty()){
            return new ResponseEntity<>("employee not found",HttpStatus.OK);
        }
        else{
            List<itemMaster> items = employee.get().getItems();
            items.add(item);
            employee.get().setItems(items);
            empRep.save(employee.get());
//            item.setEmployeeId(employee.get());
//            itemRep.save(item);
            return new ResponseEntity<>(employee.get(),HttpStatus.OK);
        }
    }

    @PostMapping("/employees")
    public ResponseEntity<?> createEmployee(@RequestBody employeeMaster employee){
//        return empRep.save(employee);
        return  new ResponseEntity<>(empRep.save(employee),HttpStatus.OK);
    }
    @PutMapping("/employees/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable(value = "id") String emp_id, @RequestBody employeeMaster emp){
        Optional<employeeMaster> employee = empRep.findById(emp_id);
        if(employee.isEmpty()){
            return new ResponseEntity<>("employee not found",HttpStatus.OK);
        }
        else{
            employee.get().setEmployeeName(emp.getEmployeeName());
            employee.get().setEmployeeDOB(emp.getEmployeeDOB());
            employee.get().setEmployeeDOJ(emp.getEmployeeDOJ());
            employee.get().setEmployeeDesignation(emp.getEmployeeDesignation());
            employee.get().setGender(emp.getGender());
            empRep.save(emp);
            return new ResponseEntity<>(employee,HttpStatus.OK);
        }
    }
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable(value = "id") String emp_id){
        Optional<employeeMaster> employee = empRep.findById(emp_id);
        if(employee.isEmpty()){
            return new ResponseEntity<>("employee not found",HttpStatus.OK);
        }
        else{
            employee.ifPresent(empRep::delete);
            return new ResponseEntity<>("employee deleted",HttpStatus.OK);
        }
    }


}
