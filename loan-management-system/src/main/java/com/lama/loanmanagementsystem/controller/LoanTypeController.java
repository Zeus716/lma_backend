package com.lama.loanmanagementsystem.controller;

import com.lama.loanmanagementsystem.model.LoanType;
import com.lama.loanmanagementsystem.repository.LoanTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@RestController
@RequestMapping()
@CrossOrigin("*")

//checked all working
public class LoanTypeController {

    @Autowired
    private LoanTypeRepository cardRep;

    @GetMapping("/loantypes")
    public ResponseEntity<?> getAllLoans() {
        return new ResponseEntity<>(cardRep.findAll(), HttpStatus.OK);
    }

    @GetMapping("/loantypes/{id}")
    public ResponseEntity<?> getLoanById(@PathVariable(value = "id") String id) {
        Optional<LoanType> card = cardRep.findById(id);
        if (card.isPresent()) {
            return new ResponseEntity<>(cardRep.findById(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("card not found", HttpStatus.OK);
        }

    }

    @PostMapping("/loantypes")
    public ResponseEntity<?> createLoan(@RequestBody LoanType card) {
        return new ResponseEntity<>(cardRep.save(card), HttpStatus.OK);
    }


    @PutMapping("/loantypes/{id}")
    public ResponseEntity<?> updateLoan(@PathVariable(value = "id") String id, @RequestBody LoanType card) {
        Optional<LoanType> empCard = cardRep.findById(id);
        if (empCard.isPresent()) {
            empCard.get().setDurationInMonths(card.getDurationInMonths());
            empCard.get().setLoanType(card.getLoanType());
            cardRep.save(empCard.get());
            return new ResponseEntity<>(empCard.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("card not found", HttpStatus.OK);
        }
    }

    @DeleteMapping("/loantypes/{id}")
    public ResponseEntity<?> deleteLoan(@PathVariable(value = "id") String id) {
        Optional<LoanType> empCard = cardRep.findById(id);
        if (empCard.isPresent()) {
            empCard.ifPresent(cardRep::delete);
            return new ResponseEntity<>("card deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("card not found", HttpStatus.OK);
        }
    }
}
