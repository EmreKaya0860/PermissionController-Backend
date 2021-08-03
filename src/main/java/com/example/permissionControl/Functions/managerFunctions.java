package com.example.permissionControl.Functions;

import com.example.permissionControl.DB.App;
import com.example.permissionControl.Persons.managers;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class managerFunctions extends App {


    public List<managers> getmanagers(){
        String SQL = "SELECT * FROM managers";
        List<managers> managersList = new ArrayList<>();


        try (Connection conn = connect();
             Statement stmt = conn.createStatement())
        {
            ResultSet rs = stmt.executeQuery(SQL);
            while(rs.next()){
                managers manager = new managers();
                manager.setId(rs.getInt("id"));
                manager.setName(rs.getString("name"));
                manager.setEmail(rs.getString("email"));
                manager.setPhone(rs.getString("phone"));
                manager.setCity(rs.getString("city"));
                manager.setGender(rs.getString("gender"));
                manager.setStatus(rs.getString("status"));
                manager.setDepartment(rs.getString("department"));
                manager.setStarted_date(rs.getDate("started_date"));
                managersList.add(manager);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return managersList;
    }
    public managers findManagerByID(Integer managerID) throws SQLException {
        String SQL = "SELECT * FROM managers WHERE id=?";
        managers findManager = new managers();
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setInt(1, managerID);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            findManager.setId(rs.getInt("id"));
            findManager.setName(rs.getString("name"));
            findManager.setEmail(rs.getString("email"));
            findManager.setPhone(rs.getString("phone"));
            findManager.setCity(rs.getString("city"));
            findManager.setGender(rs.getString("gender"));
            findManager.setStatus(rs.getString("status"));
            findManager.setDepartment(rs.getString("department"));
            findManager.setStarted_date(rs.getDate("started_date"));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return findManager;
    }

    public managers insertManager(managers manager) {
        String SQL = "INSERT INTO managers(name,email,phone,city,gender,status,department,started_date) "
                + "VALUES(?,?,?,?,?,?,?,?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setString(1, manager.getName());
            pstmt.setString(2, manager.getEmail());
            pstmt.setString(3, manager.getPhone());
            pstmt.setString(4, manager.getCity());
            pstmt.setString(5, manager.getGender());
            pstmt.setString(6, manager.getStatus());
            pstmt.setString(7, manager.getDepartment());
            pstmt.setDate(8,   manager.getStarted_date());
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return manager;
    }

    public int deletemanagers(int ID){
        String SQL = "DELETE FROM managers WHERE id=?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setInt(1, ID);

            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ID;
    }

    public managers updateManagers(managers manager) throws SQLException {
        String SQL = "UPDATE managers SET "+
                     "email = ?, " +
                     "phone = ?, " +
                     "city = ?, " +
                     "status = ?, " +
                     "department = ?  "+
                     "WHERE id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setString(1, manager.getEmail());
            pstmt.setString(2, manager.getPhone());
            pstmt.setString(3, manager.getCity());
            pstmt.setString(4, manager.getStatus());
            pstmt.setString(5, manager.getDepartment());
            pstmt.setInt(6,manager.getId());
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

       return findManagerByID(manager.getId());

    }


}
