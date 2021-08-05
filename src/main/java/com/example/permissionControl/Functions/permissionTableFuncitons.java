package com.example.permissionControl.Functions;

import com.example.permissionControl.DB.App;
import com.example.permissionControl.Persons.permissionTable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class permissionTableFuncitons extends App {

    public List<permissionTable> getpermissionTable() throws SQLException {
        String SQL = "SELECT * FROM permission_table ORDER BY id ASC";

        List<permissionTable> permissionTableList = new ArrayList<>();

        try (Connection conn = connect();
             Statement stmt = conn.createStatement())
        {
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()){
                permissionTable permission = new permissionTable();
                permission.setId(rs.getInt("id"));
                permission.setStatus(rs.getString("status"));
                permission.setTaken_by(rs.getInt("taken_by"));
                permission.setConfirm_by(rs.getInt("confirm_by"));
                permission.setStart_date(rs.getDate("start_date"));
                permission.setFinish_date(rs.getDate("finish_date"));
                permissionTableList.add(permission);
            }
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }

        return permissionTableList;
    }
    public permissionTable findPermissionTableID(Integer permissionTableID) throws SQLException {
        String SQL = "SELECT * FROM permission_table WHERE id=?";
        permissionTable findPermission = new permissionTable();
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setInt(1, permissionTableID);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            findPermission.setId(rs.getInt("id"));
            findPermission.setStatus(rs.getString("status"));
            findPermission.setTaken_by(rs.getInt("taken_by"));
            findPermission.setConfirm_by(rs.getInt("confirm_by"));
            findPermission.setStart_date(rs.getDate("start_date"));
            findPermission.setFinish_date(rs.getDate("finish_date"));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return findPermission;
    }

    public permissionTable insertPermissionTable(permissionTable permission) {
        String SQL = "INSERT INTO permission_table(status,start_date,finish_date,taken_by,confirm_by) "
                + "VALUES(?,?,?,?,?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setString(1, permission.getStatus());
            pstmt.setDate(2, permission.getStart_date());
            pstmt.setDate(3, permission.getFinish_date());
            pstmt.setInt(4, permission.getTaken_by());
            pstmt.setInt(5, permission.getConfirm_by());
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return permission;
    }

    public int deletpermissionTable(int ID){
        String SQL = "DELETE FROM permission_table WHERE id=?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setInt(1, ID);

            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ID;
    }

    public permissionTable updatePermissionTable(permissionTable permissionTable) throws SQLException {
        String SQL = "UPDATE permission_table SET "+
                "status = ?, " +
                "start_date = ?, " +
                "finish_date = ?, " +
                "taken_by = ?, " +
                "confirm_by = ?,  "+
                "WHERE id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setString(1, permissionTable.getStatus());
            pstmt.setDate(2, permissionTable.getStart_date());
            pstmt.setDate(3, permissionTable.getFinish_date());
            pstmt.setInt(4, permissionTable.getTaken_by());
            pstmt.setInt(5, permissionTable.getConfirm_by());
            pstmt.setInt(6,permissionTable.getId());
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return findPermissionTableID(permissionTable.getId());

    }

}
