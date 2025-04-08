package final_e_commerce.user;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private final Connection conn;

    public UserDAO(Connection conn) {
        this.conn = conn;
    }

    public int insertUser(Users user) throws SQLException {
        String sql = "INSERT INTO TB_USER (id_user, nm_user, nm_paswd, no_mobile, cd_user_type, st_status, da_first_date) " +
                "VALUES (?, ?, ?, ?, ?, ?, SYSDATE)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getId());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getPhone());
            pstmt.setString(5, user.getUserType());
            pstmt.setString(6, user.getStatus());
            return pstmt.executeUpdate();
        }
    }

    public Users findByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM TB_USER WHERE id_user = ? AND st_status = 'ST01'";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Users user = new Users();
                    user.setId(rs.getString("id_user"));
                    user.setName(rs.getString("nm_user"));
                    user.setPassword(rs.getString("nm_paswd"));
                    user.setPhone(rs.getString("no_mobile"));
                    user.setUserType(rs.getString("cd_user_type"));
                    user.setStatus(rs.getString("st_status"));
                    return user;
                }
            }
        }
        return null;
    }

    public List<Users> selectAllUsers() throws SQLException {
        List<Users> list = new ArrayList<>();
        String sql = "SELECT * FROM TB_USER ORDER BY da_first_date DESC";
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Users user = new Users();
                user.setId(rs.getString("id_user"));
                user.setName(rs.getString("nm_user"));
                user.setPhone(rs.getString("no_mobile"));
                user.setUserType(rs.getString("cd_user_type"));
                user.setStatus(rs.getString("st_status"));
                list.add(user);
            }
        }
        return list;
    }

    public int updateStatus(String id, String newStatus) throws SQLException {
        String sql = "UPDATE TB_USER SET st_status = ? WHERE id_user = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newStatus);
            pstmt.setString(2, id);
            return pstmt.executeUpdate();
        }
    }

    public int updateUserInfo(String id, String name, String phone) throws SQLException {
        String sql = "UPDATE TB_USER SET nm_user = ?, no_mobile = ? WHERE id_user = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, phone);
            pstmt.setString(3, id);
            return pstmt.executeUpdate();
        }
    }

    public int updatePassword(String id, String newPassword) throws SQLException {
        String sql = "UPDATE TB_USER SET nm_paswd = ? WHERE id_user = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newPassword);
            pstmt.setString(2, id);
            return pstmt.executeUpdate();
        }
    }
}
