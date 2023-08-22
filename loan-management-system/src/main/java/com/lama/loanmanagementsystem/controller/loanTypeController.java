package com.lama.loanmanagementsystem.controller;

import com.lama.loanmanagementsystem.model.employeeCard;
import com.lama.loanmanagementsystem.model.loanType;
import com.lama.loanmanagementsystem.repository.cardRepository;
import com.lama.loanmanagementsystem.repository.loanTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@RestController
@RequestMapping()
@CrossOrigin("*")
public class loanTypeController {



    @Autowired
    private loanTypeRepository cardRep;

    @GetMapping("/loantypes")
    public ResponseEntity<?> getAllLoans() {
        return new ResponseEntity<>(cardRep.findAll(), HttpStatus.OK);
    }

    @GetMapping("/loantypes/{id}")
    public ResponseEntity<?> getLoanById(@PathVariable(value = "id") String id) {
        Optional<loanType> card = cardRep.findById(id);
        if (card.isPresent()) {
            return new ResponseEntity<>(cardRep.findById(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("card not found", HttpStatus.OK);
        }

    }

    @PostMapping("/loantypes")
    public ResponseEntity<?> createLoan(@RequestBody loanType card) {
        return new ResponseEntity<>(cardRep.save(card), HttpStatus.OK);
    }


    @PutMapping("/loantypes/{id}")
    public ResponseEntity<?> updateLoan(@PathVariable(value = "id") String id, @RequestBody loanType card) {
        Optional<loanType> empCard = cardRep.findById(id);
        if (empCard.isPresent()) {
            empCard.get().setDurationInMonths(card.getDurationInMonths());
            cardRep.save(card);
            return new ResponseEntity<>("card saved", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("card not found", HttpStatus.OK);
        }
    }

    @DeleteMapping("/loantypes/{id}")
    public ResponseEntity<?> deleteLoan(@PathVariable(value = "id") String id) {
        Optional<loanType> empCard = cardRep.findById(id);
        if (empCard.isPresent()) {
            empCard.ifPresent(cardRep::delete);
            return new ResponseEntity<>("card deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("card not found", HttpStatus.OK);
        }
    }
}
