package com.lama.loanmanagementsystem.controller;

import com.lama.loanmanagementsystem.model.employeeCard;
import com.lama.loanmanagementsystem.repository.cardRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.font.OpenType;
import java.util.Optional;

@RestController
@RequestMapping()
public class cardController {

    @Autowired
    private cardRepository cardRep;
    @GetMapping("/cards")
    public ResponseEntity<?> getAllLoans(){
        return new ResponseEntity<>(cardRep.findAll(), HttpStatus.OK);
    }
    @GetMapping("/cards/{id}")
    public ResponseEntity<?> getLoanById(@PathVariable(value="id") String id){
        Optional<employeeCard> card = cardRep.findById(id);
        if(card.isPresent()){
            return new ResponseEntity<>(cardRep.findById(id),HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("card not found", HttpStatus.OK);
        }

    }
    @PostMapping("/cards")
    public ResponseEntity<?> createLoan(@RequestBody employeeCard card){
        return new ResponseEntity<>(cardRep.save(card),HttpStatus.OK);
    }

    @PutMapping("/cards/{id}")
    public ResponseEntity<?> updateLoan(@PathVariable(value = "id") String id,@RequestBody employeeCard card){
        Optional<employeeCard> empCard = cardRep.findById(id);
        if(empCard.isPresent()){
            empCard.get().setIssueDate(card.getIssueDate());
            empCard.get().setApprovalStatus(card.getApprovalStatus());
            cardRep.save(card);
            return new ResponseEntity<>("card saved", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("card not found",HttpStatus.OK);
        }
    }
    @DeleteMapping("/cards/{id}")
    public ResponseEntity<?> deleteLoan(@PathVariable(value = "id") String id){
        Optional<employeeCard> empCard = cardRep.findById(id);
        if(empCard.isPresent()){
            empCard.ifPresent(cardRep::delete);
            return new ResponseEntity<>("card deleted", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("card not found",HttpStatus.OK);
        }
    }
}
