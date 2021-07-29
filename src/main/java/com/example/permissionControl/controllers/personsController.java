package com.example.permissionControl.controllers;

import com.example.permissionControl.Persons.persons;
import com.example.permissionControl.personsRepository.personsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/persons")
public class personsController {

    @Autowired
    private personsRepository perRepo;

    @PostMapping
    public ResponseEntity<?> addNewPerson(@RequestBody persons person1){
        perRepo.save(person1);
        return ResponseEntity.ok(person1);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPersonById(@PathVariable Integer id){

        try {
            persons person = perRepo.findById(id).get();
            return ResponseEntity.ok(person);
        } catch (Exception e){
            return ResponseEntity.status(404).body(null);
        }

    }

    @GetMapping
    public Iterable<persons> getPersons(
           @RequestParam(name = "_page", defaultValue = "1") Integer pageNum,
           @RequestParam(name = "_limit", defaultValue = "10") Integer pageSize)
    {

        Pageable p = PageRequest.of(pageNum-1,pageSize);

        return perRepo.findAll(p).getContent();
    }


}
