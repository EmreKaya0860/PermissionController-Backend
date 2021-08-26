package com.example.permissionControl.Functions;

import com.example.permissionControl.DB.App;
import com.example.permissionControl.Persons.employees;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class employeeFunctions extends App{


    public List<employees> getemployees(){
        String SQL = "SELECT * FROM employees ORDER BY id ASC";

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
                employee.setPosition(rs.getString("position"));
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
            findEmployee.setPosition(rs.getString("position"));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return findEmployee;
    }

    public employees insertEmployee(employees employee) {
        String SQL = "INSERT INTO employees(id,name,email,phone, city,gender,status, department,started_date,manager_id,position) "
                + "VALUES(?,?,?,?,?, ?,?,?, ?,?,?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setInt(1,(countEmployees()+1));
            pstmt.setString(2, employee.getName());
            pstmt.setString(3, employee.getEmail());
            pstmt.setString(4, employee.getPhone());
            pstmt.setString(5, employee.getCity());
            pstmt.setString(6, employee.getGender());
            pstmt.setString(7, employee.getStatus());
            pstmt.setString(8, employee.getDepartment());
            pstmt.setDate(9,   employee.getStarted_date());
            pstmt.setInt(10,   employee.getManager_id());
            pstmt.setString(11,employee.getPosition());
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return employee;
    }

    public Integer countEmployees() throws SQLException {
        String SQL = "SELECT countemployees()";

        Connection conn = connect();
        Statement stmt = conn.createStatement();

        ResultSet rs = stmt.executeQuery(SQL);
        rs.next();


        return rs.getInt("countemployees");
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

    public employees updateEmployees(employees employee) throws SQLException {
        String SQL = "UPDATE employees SET "+
                "email = ?, " +
                "phone = ?, " +
                "city = ?, " +
                "status = ?, " +
                "department = ?,  "+
                "position = ?, "+
                "manager_id = ? "+
                "WHERE id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setString(1, employee.getEmail());
            pstmt.setString(2, employee.getPhone());
            pstmt.setString(3, employee.getCity());
            pstmt.setString(4, employee.getStatus());
            pstmt.setString(5, employee.getDepartment());
            pstmt.setString(6, employee.getPosition());
            pstmt.setInt(7,employee.getManager_id());
            pstmt.setInt(8,employee.getId());
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return findEmployeeByID(employee.getId());

    }

}
