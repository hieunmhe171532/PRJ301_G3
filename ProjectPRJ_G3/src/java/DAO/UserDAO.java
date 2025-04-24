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

/**
 *
 * @author hieum
 */
public class UserDAO extends DBContext{
    
    public List<User> getAllUser() {
        String sql = "SELECT us.user_id id, us.username uname, us.password pass, us.full_name name, "
                + "us.email email, us.gender gender, us.dob dob, us.created_at createat   FROM Users";
        List<User> l = new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()) {
                User user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getBoolean(6), rs.getDate(7), rs.getDate(8));
                l.add(user);
            }
            return l;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public User getUserByUserName(String username){
        String sql = "SELECT us.user_id id, us.username uname, us.password pass, us.full_name name, "
                + "us.email email, us.gender gender, us.dob dob, us.created_at createat   FROM Users us WHERE username = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            
            if (rs. next()) {
                User user = new User(rs.getInt("id"), rs.getString("uname"), rs.getString("pass"), 
                        rs.getString("name"), rs.getString("email"), rs.getBoolean("gender"), rs.getDate("dob"), rs.getDate("createat"));
                return user;
            }
        } catch (SQLException e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }
    
//    public User checkAccount(String us, String pass) {
//        String sql = "select * from Users where username=? AND password=?";
//        try {
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setString(1, us);
//            ps.setString(2, pass);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                User user = new User(rs.getInt("id"), rs.getString("uname"), rs.getString("pass"), 
//                        rs.getString("name"), rs.getString("email"), rs.getBoolean("gender"), rs.getDate("dob"), rs.getDate("createat"));
//                ps.close();
//                rs.close();
//                return user;
//            }
//            ps.close();
//            rs.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public boolean addAccount(String username, String password, String fullname, String email, String phone, String address) {
//        String sql = "insert into Users(id, uname, pass, name, email, gender, dob, createat, role) values (?, ?, ?, ?, ?, ?, 2)";
//        try {
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setString(1, username);
//            ps.setString(2, password);
//            ps.setString(3, fullname);
//            ps.setString(4, email);
//            ps.setString(5, phone);
//            ps.setString(6, address);
//            ps.executeUpdate();
//            ps.close();
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }    
}