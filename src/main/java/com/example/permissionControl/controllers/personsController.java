package com.example.permissionControl.controllers;

import com.example.permissionControl.DB.App;
import com.example.permissionControl.Persons.persons;
import com.example.permissionControl.personsRepository.personsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import javax.xml.ws.Response;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/persons")
public class personsController {

    @Autowired
    private personsRepository perRepo;

    @PostMapping
    public ResponseEntity<?> addNewPerson(@RequestBody persons person1) throws SQLException {
        App insertP = new App();
        return ResponseEntity.ok(insertP.insertPerson(person1));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPersonById(@PathVariable Integer id){

        try {
            App find = new App();
            return ResponseEntity.ok(find.findPersonByID(id));
        } catch (Exception e){
            return ResponseEntity.status(404).body(null);
        }

    }

    @GetMapping
    public List<persons> getPersons(
           @RequestParam(name = "_page", defaultValue = "1") Integer pageNum,
           @RequestParam(name = "_limit", defaultValue = "10") Integer pageSize)
    {
        App app = new App();
        Pageable p = PageRequest.of(pageNum-1,pageSize);

        return app.getPersons();
    }

    @DeleteMapping
    public persons deletePersons(
            @RequestParam(name = "_del", defaultValue = "102") Integer id) throws SQLException {
        App delP = new App();
        delP.deletePersons(id);
        return delP.findPersonByID(id);
    }

}
