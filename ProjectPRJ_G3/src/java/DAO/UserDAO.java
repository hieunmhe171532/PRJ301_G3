/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Dal.DBContext;
import Model.User;
import java.sql.*;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hieum
 */
public class UserDAO extends DBContext{
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
    
    
}
