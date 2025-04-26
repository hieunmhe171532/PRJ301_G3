/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Dal.DBContext;
import Model.User;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;

/**
 *
 * @author hieum
 */
public class UserDAO extends DBContext {

    public List<User> getAllUser() {
        String sql = "SELECT us.user_id id, us.username uname, us.password pass, us.full_name name, "
                + "us.email email, us.gender gender, us.dob dob, us.created_at createat   FROM Users";
        List<User> l = new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                User user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getBoolean(6), rs.getDate(7), rs.getDate(8));
                l.add(user);
            }
            return l;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserByUserName(String username) {
        String sql = "SELECT us.user_id id, us.username uname, us.password pass, us.full_name name, "
                + "us.email email, us.gender gender, us.dob dob, us.created_at createat   FROM Users us WHERE username = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                User user = new User(rs.getInt("id"), rs.getString("uname"), rs.getString("pass"),
                        rs.getString("name"), rs.getString("email"), rs.getBoolean("gender"), rs.getDate("dob"), rs.getDate("createat"));
                return user;
            }
        } catch (SQLException e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    public boolean createUser(User user) {
        String sql = "INSERT INTO Users (username, password, full_name, email, gender, dob, created_at) "
                + "VALUES (?, ?, ?, ?, ?, ?, GETDATE())";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, user.getUserName());
            stm.setString(2, user.getPassword());
            stm.setString(3, user.getFullName());
            stm.setString(4, user.getEmail());
            stm.setBoolean(5, user.isGender());
            stm.setObject(6, user.getDob());
            stm.executeUpdate();
            return true;
        } catch (SQLException e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    public boolean updateUser(User user) throws Exception {
        String sql = "UPDATE Users SET full_name = ?, email = ?, gender = ? WHERE user_id = ?";
        int rows = 0;
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, user.getFullName());
            stm.setString(2, user.getEmail());
            stm.setBoolean(3, user.isGender());
            stm.setInt(4, user.getUserID());
            rows = stm.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return rows > 0;
    }
    
    public boolean changePassword (int userID, String newPassword) {
        String sql = "UPDATE Users SET password = ? WHERE user_id = ?";
        int rows = 0;
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, newPassword);
            stm.setInt(2, userID);
            rows = stm.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return rows > 0;
    }
}
 