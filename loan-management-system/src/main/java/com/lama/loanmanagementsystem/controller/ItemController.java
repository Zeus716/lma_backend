package com.lama.loanmanagementsystem.controller;

import com.lama.loanmanagementsystem.model.EmployeeMaster;
import com.lama.loanmanagementsystem.model.ItemMaster;
import com.lama.loanmanagementsystem.model.LoanMaster;
import com.lama.loanmanagementsystem.model.LoanType;
import com.lama.loanmanagementsystem.repository.EmployeeMasterRepository;
import com.lama.loanmanagementsystem.repository.ItemRepository;
import com.lama.loanmanagementsystem.repository.LoanRepository;
import com.lama.loanmanagementsystem.repository.LoanTypeRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

@RequestMapping
@RestController
@CrossOrigin("*")

public class ItemController {
    @Autowired
    private ItemRepository itemrep ;
    @Autowired
    private EmployeeMasterRepository empRep;
    @Autowired
    private LoanTypeRepository loanTypeRep;
    @Autowired
    private LoanRepository loanRep;


    @GetMapping("/items")
    public ResponseEntity<?> getAllItems(){
        return new ResponseEntity<>(itemrep.findAll(), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/items")
    public ResponseEntity<?>  createItems(@Valid @RequestBody ItemMaster item){
        Optional<LoanType> loans =loanTypeRep.findByLoanTypeIs(item.getItemCategory());
        if(loans.isEmpty()){
            return new ResponseEntity<>("item category doesn't exist",HttpStatus.OK);
        }
        item.setIssueStatus('N');
        return new ResponseEntity<>(itemrep.save(item),HttpStatus.OK);
    }

//    @PostMapping("/employees/{id}/items")
//    public ResponseEntity<?> createItemEmployee(@Valid @PathVariable(value="id") String employeeId,
//                                                @Valid @RequestBody ItemMaster item) throws ParseException {
//        Optional<EmployeeMaster> employee = empRep.findById(employeeId);
//        if(employee.isEmpty()){
//            return new ResponseEntity<>("employee not present",HttpStatus.OK);
//        }
//        else{
//            item.setEmployee(employee.get());
//            item.setIssueStatus('T');
//            LocalDate date = LocalDate.now();
//            Date today = java.sql.Date.valueOf(date);
//            Optional<LoanType> loans =loanTypeRep.findByLoanTypeIs(item.getItemCategory());
//            if(loans.isEmpty()){
//                return new ResponseEntity<>("item category doesn't exist",HttpStatus.OK);
//            }
////            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//            item.setIssueDate(today);
//            Integer numMonths = loans.get().getDurationInMonths();
//            LocalDate returnDate = LocalDate.now().plusMonths(numMonths);
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//            String formatted = returnDate.format(formatter);
//            Date finalDate = new SimpleDateFormat("yyyy-MM-dd").parse(formatted);
//            item.setReturnDate(finalDate);
//            LoanMaster loan = new LoanMaster(loans.get(),employee.get());
////            LoanMaster loan = new LoanMaster(item.getItemId(),loans.get(),employee.get());
//            loanRep.save(loan);
//            itemrep.save(item);
//            return new ResponseEntity<>(item,HttpStatus.OK);
//        }
//    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("items/{item_id}")
    public ResponseEntity<?> updateItem(@PathVariable(value = "item_id") @Valid String itemId,
                                        @Valid @RequestBody ItemMaster itemNew){
        Optional<ItemMaster> item = itemrep.findById(itemId);
        if(item.isEmpty()){
            return new ResponseEntity<>("item not present",HttpStatus.OK);
        }
        else{
            item.get().setItemCategory(itemNew.getItemCategory());
            item.get().setItemDescription(itemNew.getItemDescription());
            item.get().setItemMake(itemNew.getItemMake());
            item.get().setIssueStatus(itemNew.getIssueStatus());
            item.get().setItemValuation(itemNew.getItemValuation());
            item.get().setIssueDate(itemNew.getIssueDate());
            item.get().setReturnDate(itemNew.getReturnDate());
            item.get().setItemUrl(itemNew.getItemUrl());
            itemrep.save(item.get());
            return new ResponseEntity<>(item.get(),HttpStatus.OK);
        }
    }

    @PutMapping("/items/{item_id}/employees/{employee_id}")
    public ResponseEntity<?> updateItemEmployee(@PathVariable(value = "item_id") @Valid String itemId,
                                                @PathVariable(value = "employee_id") @Valid String employeeId) throws ParseException {
        Optional<ItemMaster> item = itemrep.findById(itemId);
        if(item.isEmpty()){
            return new ResponseEntity<>("item not present",HttpStatus.OK);
        }

        else{

            Optional<EmployeeMaster> employee  =empRep.findById(employeeId);
            if(employee.isPresent()){
                item.get().setEmployee(employee.get());
                item.get().setIssueStatus('T');
                LocalDate date = LocalDate.now();
                Date today = java.sql.Date.valueOf(date);
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//                item.get().setIssueDate(today);
                Optional<LoanType> loans =loanTypeRep.findByLoanTypeIs(item.get().getItemCategory());
                if(loans.isEmpty()){
                    return new ResponseEntity<>("item category doesn't exist",HttpStatus.OK);
                }
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                item.get().setIssueDate(today);
                Integer numMonths = loans.get().getDurationInMonths();
                LocalDate returnDate = LocalDate.now().plusMonths(numMonths);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String formatted = returnDate.format(formatter);
                Date finalDate = new SimpleDateFormat("yyyy-MM-dd").parse(formatted);
                item.get().setReturnDate(finalDate);
//                LoanMaster loan = new LoanMaster(loans.get(),employee.get());
                LoanMaster loan = new LoanMaster(item.get().getItemId(),loans.get(),employee.get());
                loanRep.save(loan);
                itemrep.save(item.get());
                return new ResponseEntity<>(item.get(),HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>("employee not present",HttpStatus.OK);
            }

        }
    }

    @GetMapping("items/available")
    public ResponseEntity<?> getAvailableItems(){
        return new ResponseEntity<>(itemrep.findByIssueStatusIs('N'),HttpStatus.OK);

    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("employees/{id}/items")
    public ResponseEntity<?> getAllItemsofEmployee(@PathVariable(value = "id") @Valid String employeeId){
        Optional<EmployeeMaster> employee =empRep.findById(employeeId);
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
    public ResponseEntity<?> deleteItem(@PathVariable(value = "item_id") @Valid String itemId){
        Optional<ItemMaster> item = itemrep.findById(itemId);
        if(item.isEmpty()){
            return new ResponseEntity<>("item not found",HttpStatus.OK);
        }
        else{
            item.get().setEmployee(null);
//            Optional<LoanMaster> loan = loanRep.findById(item.get().getItemId());
            loanRep.deleteById(item.get().getItemId());
            item.ifPresent(itemrep::delete);

            return new ResponseEntity<>("item deleted",HttpStatus.OK);
        }
    }
}
