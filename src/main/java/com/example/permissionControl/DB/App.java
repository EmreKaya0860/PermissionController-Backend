package com.example.permissionControl.DB;

import com.example.permissionControl.Persons.persons;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class App {



    private Connection connect() {
         final String url = "jdbc:postgresql://localhost/permissioncontroller";
         final String user = "postgres";
         final String password = "14024emre";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }

    public List<persons> getPersons(){
        String SQL = "SELECT * FROM persons";
        List<persons> personsList = new ArrayList<>();


        try (Connection conn = connect();
             Statement stmt = conn.createStatement())
        {
            ResultSet rs = stmt.executeQuery(SQL);
            while(rs.next()){
                persons person = new persons();
                person.setId(rs.getInt("id"));
                person.setName(rs.getString("name"));
                person.setEmail(rs.getString("email"));
                person.setPhone(rs.getString("phone"));
                person.setCity(rs.getString("city"));
                person.setGender(rs.getString("gender"));
                personsList.add(person);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return personsList;
    }
    public persons findPersonByID(Integer personID) throws SQLException {
        String SQL = "SELECT * FROM persons WHERE id=?";
        persons findPerson = new persons();
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setInt(1, personID);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            findPerson.setId(rs.getInt("id"));
            findPerson.setName(rs.getString("name"));
            findPerson.setEmail(rs.getString("email"));
            findPerson.setPhone(rs.getString("phone"));
            findPerson.setCity(rs.getString("city"));
            findPerson.setGender(rs.getString("gender"));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return findPerson;
    }

    public persons insertPerson(persons person) {
        String SQL = "INSERT INTO persons(id,name,email,phone,city,gender) "
                + "VALUES(?,?,?,?,?,?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setInt(1,person.getId());
            pstmt.setString(2, person.getName());
            pstmt.setString(3, person.getEmail());
            pstmt.setString(4, person.getPhone());
            pstmt.setString(5, person.getCity());
            pstmt.setString(6, person.getGender());
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return person;
    }

    public int deletePersons(int ID){
        String SQL = "DELETE FROM persons WHERE id=?";
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
