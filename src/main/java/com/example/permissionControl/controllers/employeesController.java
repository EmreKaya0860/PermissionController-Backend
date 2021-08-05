package com.example.permissionControl.controllers;


import com.example.permissionControl.Functions.employeeFunctions;
import com.example.permissionControl.Persons.employees;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class employeesController {


    @PostMapping
    public ResponseEntity<?> addNewEmployees(@RequestBody employees employee) throws SQLException {
        employeeFunctions insertEmp = new employeeFunctions();
        return ResponseEntity.ok(insertEmp.insertEmployee(employee));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable Integer id){

        try {
            employeeFunctions find = new employeeFunctions();
            return ResponseEntity.ok(find.findEmployeeByID(id));
        } catch (Exception e){
            return ResponseEntity.status(404).body(null);
        }

    }

    @GetMapping
    public List<employees> getemployees()
    {
        employeeFunctions app = new employeeFunctions();

        return app.getemployees();
    }

    @DeleteMapping("/del={id}")
    public employees deleteemployees(@PathVariable Integer id) throws SQLException {
        employeeFunctions delE = new employeeFunctions();
        delE.deleteemployees(id);
        return delE.findEmployeeByID(id);
    }

    @PutMapping("/{id}")
    public employees updateEmployees(@PathVariable("id") Integer id, @RequestBody employees employee) throws SQLException {
        employeeFunctions upE = new employeeFunctions();
        employee.setId(id);
        return upE.updateEmployees(employee);
    }

}
