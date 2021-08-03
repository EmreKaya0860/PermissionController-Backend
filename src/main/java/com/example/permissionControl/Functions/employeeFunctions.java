package com.example.permissionControl.Functions;

import com.example.permissionControl.DB.App;
import com.example.permissionControl.Persons.employees;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class employeeFunctions extends App{


    public List<employees> getemployees(){
        String SQL = "SELECT * FROM employees";
        List<employees> employeesList = new ArrayList<>();


        try (Connection conn = connect();
             Statement stmt = conn.createStatement())
        {
            ResultSet rs = stmt.executeQuery(SQL);
            while(rs.next()){
                employees employee = new employees();
                employee.setId(rs.getInt("id"));
                employee.setName(rs.getString("name"));
                employee.setEmail(rs.getString("email"));
                employee.setPhone(rs.getString("phone"));
                employee.setCity(rs.getString("city"));
                employee.setGender(rs.getString("gender"));
                employee.setStatus(rs.getString("status"));
                employee.setDepartment(rs.getString("department"));
                employee.setStarted_date(rs.getDate("started_date"));
                employee.setManager_id(rs.getInt("manager_id"));
                employeesList.add(employee);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return employeesList;
    }
    public employees findEmployeeByID(Integer employeeID) throws SQLException {
        String SQL = "SELECT * FROM employees WHERE id=?";
        employees findEmployee = new employees();
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setInt(1, employeeID);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            findEmployee.setId(rs.getInt("id"));
            findEmployee.setName(rs.getString("name"));
            findEmployee.setEmail(rs.getString("email"));
            findEmployee.setPhone(rs.getString("phone"));
            findEmployee.setCity(rs.getString("city"));
            findEmployee.setGender(rs.getString("gender"));
            findEmployee.setStatus(rs.getString("status"));
            findEmployee.setDepartment(rs.getString("department"));
            findEmployee.setStarted_date(rs.getDate("started_date"));
            findEmployee.setManager_id(rs.getInt("manager_id"));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return findEmployee;
    }

    public employees insertEmployee(employees employee) {
        String SQL = "INSERT INTO employees(name,email,phone,city,gender,status,department,started_date,manager_id) "
                + "VALUES(?,?,?,?,?,?,?,?,?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setString(1, employee.getName());
            pstmt.setString(2, employee.getEmail());
            pstmt.setString(3, employee.getPhone());
            pstmt.setString(4, employee.getCity());
            pstmt.setString(5, employee.getGender());
            pstmt.setString(6, employee.getStatus());
            pstmt.setString(7, employee.getDepartment());
            pstmt.setDate(8,   employee.getStarted_date());
            pstmt.setInt(9, employee.getManager_id());
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return employee;
    }

    public int deleteemployees(int ID){
        String SQL = "DELETE FROM employees WHERE id=?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setInt(1, ID);

            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ID;
    }

}
