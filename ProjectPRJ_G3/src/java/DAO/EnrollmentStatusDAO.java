package DAO;

import Dal.DBContext;
import Model.EnrollmentStatus;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentStatusDAO extends DBContext {

    // Lấy danh sách tất cả trạng thái enrollment
    public List<EnrollmentStatus> getAllStatuses() {
        List<EnrollmentStatus> list = new ArrayList<>();
        String sql = "SELECT status_id, status_name FROM EnrollmentStatus";
        
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                EnrollmentStatus es = new EnrollmentStatus(
                    rs.getInt("status_id"),
                    rs.getString("status_name")
                );
                list.add(es);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return list;
    }

    // Lấy trạng thái theo ID
    public EnrollmentStatus getStatusById(int id) {
        String sql = "SELECT status_id, status_name FROM EnrollmentStatus WHERE status_id = ?";
        
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return new EnrollmentStatus(
                    rs.getInt("status_id"),
                    rs.getString("status_name")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }
}
