/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UserController;

/**
 *
 * @author admin
 */
public class Validate {

    public static String checkFullName(String fullName) {
        if (fullName == null || fullName.trim().isEmpty()) {
            return "Full name cannot be empty!";
        }
        // Kiểm tra nếu có số, ký tự đặc biệt, và yêu cầu có ít nhất một chữ hoa
        if (!fullName.matches("^(?=.*[A-Z])[A-Za-z ]+$")) {
            return "Full name must contain at least one uppercase letter and no special characters!";
        }
        // Loại bỏ khoảng trắng thừa
        fullName = fullName.replaceAll("\\s+", " ").trim();
        return null; // Không có lỗi
    }

    public static String checkUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            return "Username cannot be empty!";
        }
        if (username.length() < 4 || username.length() > 16) {
            return "Username must be between 4 and 16 characters!";
        }
        // Kiểm tra không chứa ký tự đặc biệt và khoảng trắng
        if (!username.matches("^[A-Za-z0-9]+$")) {
            return "Username can only contain letters and numbers, no spaces or special characters!";
        }
        // Loại bỏ khoảng trắng thừa
        username = username.trim();
        return null; // Không có lỗi
    }

    public static String checkEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return "Email cannot be empty!";
        }
        // Kiểm tra định dạng email
        if (!email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")) {
            return "Invalid email format!";
        }
        return null; // Không có lỗi
    }

    public static String checkPassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            return "Password cannot be empty!";
        }
        if (password.length() < 4 || password.length() > 16) {
            return "Password must be between 4 and 16 characters!";
        }
        if (!password.matches(".*[A-Za-z].*") || !password.matches(".*[0-9].*")) {
            return "Password must contain both letters and numbers!";
        }
        return null; // Không có lỗi
    }

    public static String checkConfirmPassword(String password, String confirmPassword) {
        if (confirmPassword == null || confirmPassword.trim().isEmpty()) {
            return "Confirm password cannot be empty!";
        }
        if (!confirmPassword.equals(password)) {
            return "Confirm password does not match the password!";
        }
        return null; // Không có lỗi
    }

    // Check current password (for ChangePassword)
    public static String checkCurrentPassword(String currentPassword, String correctPassword) {
        if (currentPassword == null || currentPassword.trim().isEmpty()) {
            return "Current password cannot be empty!";
        }
        if (!currentPassword.equals(correctPassword)) {
            return "Current password is incorrect!";
        }
        return null;
    }

    // Check new password for ChangePassword
    public static String checkNewPassword(String newPassword) {
        if (newPassword == null || newPassword.trim().isEmpty()) {
            return "New password cannot be empty!";
        }
        if (newPassword.length() < 4 || newPassword.length() > 16) {
            return "New password must be between 4 and 16 characters!";
        }
        if (!newPassword.matches(".*[A-Za-z].*") || !newPassword.matches(".*[0-9].*")) {
            return "New password must contain both letters and numbers!";
        }
        return null;
    }

    // Check confirm new password for ChangePassword
    public static String checkConfirmNewPassword(String newPassword, String confirmNewPassword) {
        if (confirmNewPassword == null || confirmNewPassword.trim().isEmpty()) {
            return "Confirm new password cannot be empty!";
        }
        if (!confirmNewPassword.equals(newPassword)) {
            return "Confirm new password does not match the new password!";
        }
        return null;
    }    
}
