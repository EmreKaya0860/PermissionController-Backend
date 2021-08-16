package com.example.permissionControl.controllers;

import com.example.permissionControl.Functions.managerFunctions;
import com.example.permissionControl.Persons.managers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/managers")
public class managersController {

    @PostMapping
    public ResponseEntity<?> addNewManagers(@RequestBody managers manager) throws SQLException {
        managerFunctions insertMen = new managerFunctions();
        return ResponseEntity.ok(insertMen.insertManager(manager));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getManagerById(@PathVariable Integer id){

        try {
            managerFunctions find = new managerFunctions();
            return ResponseEntity.ok(find.findManagerByID(id));
        } catch (Exception e){
            return ResponseEntity.status(404).body(null);
        }

    }

    @GetMapping
    @CrossOrigin(origins = "http://localhost:3000")
    public List<managers> getmanagers()
    {
        managerFunctions app = new managerFunctions();

        return app.getmanagers();
    }

    @DeleteMapping("/del={id}")
    public managers deleteManagers(@PathVariable Integer id) throws SQLException {
        managerFunctions delM = new managerFunctions();
        delM.deletemanagers(id);
        return delM.findManagerByID(id);
    }

    @PutMapping("/{id}")
    public managers updateManagers(@PathVariable("id") Integer id, @RequestBody managers manager) throws SQLException {
        managerFunctions upM = new managerFunctions();
        manager.setId(id);
        return upM.updateManagers(manager);
    }


}
