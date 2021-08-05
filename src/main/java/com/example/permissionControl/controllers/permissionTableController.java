package com.example.permissionControl.controllers;


import com.example.permissionControl.Functions.permissionTableFuncitons;
import com.example.permissionControl.Persons.permissionTable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/permissionTable")
public class permissionTableController {

    @GetMapping
    public List<permissionTable> getPermissionTable() throws SQLException {
        permissionTableFuncitons permissionTable = new permissionTableFuncitons();
        return permissionTable.getpermissionTable();
    }

    @GetMapping("/{id}")
    public permissionTable getPermissionByID(@PathVariable Integer id) throws SQLException {

        permissionTableFuncitons permissionTable = new permissionTableFuncitons();
        return permissionTable.findPermissionTableID(id);
    }

    @PostMapping
    public ResponseEntity<?> addNewPermissionTable(@RequestBody permissionTable permissionTable) throws SQLException {
        permissionTableFuncitons insertPT = new permissionTableFuncitons();
        return ResponseEntity.ok(insertPT.insertPermissionTable(permissionTable));
    }

    @DeleteMapping("/del={id}")
    public permissionTable deletePermissionTable(@PathVariable Integer id) throws SQLException {
        permissionTableFuncitons delPT = new permissionTableFuncitons();
        delPT.deletpermissionTable(id);
        return delPT.findPermissionTableID(id);
    }

    @PutMapping("/{id}")
    public permissionTable updatePermissionTable(@PathVariable("id") Integer id, @RequestBody permissionTable permissionTable) throws SQLException {
        permissionTableFuncitons upPT = new permissionTableFuncitons();
        permissionTable.setId(id);
        return upPT.updatePermissionTable(permissionTable);
    }

}
