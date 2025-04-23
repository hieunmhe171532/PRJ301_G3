/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Dal.DBContext;
import Model.Admin;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.sql.*;
/**
 *
 * @author hieum
 */
public class AdminDAO extends DBContext{
    
    
    public Admin getAdminByUserName(String username){
        String sql = "Select ad.admin_id id , ad.username uname, ad.password pass, ad.full_name name, ad.email email, ad.created_at createdat from Admins ad where username = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, sql);
            ResultSet rs = stm.executeQuery();
            
            if (rs.next()) {
                Admin admin = new Admin(rs.getInt("ID"), rs.getString("uname"), rs.getString("pass"), 
                        rs.getString("name"), rs.getString("email"), rs.getDate("createdat"));
                return admin;
            } 
        } catch (SQLException e) {
            Logger.getLogger(AdminDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }
}
