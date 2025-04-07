package user.general;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import static util.ScannerUtil.sc;

public class UserRegister {

    public void userRegister() throws SQLException {
        System.out.println("===== 회원 가입 =====");
        String id;
        while (true) {
            System.out.print("아이디(이메일): ");
            id = sc.nextLine();

            if (!id.matches("^[A-Za-z0-9]+@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)+$")) {
                System.out.println("아이디는 이메일 형식으로 입력되어야 합니다.");
                continue;
            }
            String sql = "SELECT id_user FROM TB_USER WHERE id_user = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println("이미 가입된 아이디입니다.");
                rs.close();
                pstmt.close();
                return;
            }
            rs.close();
            pstmt.close();
            break;
        }
        String pw;
        while (true) {
            System.out.print("비밀번호: ");
            pw = sc.nextLine();

            if (!pw.matches("^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]{5,15}$")) {
                System.out.println("비밀번호는 영문자 및 숫자를 각각 1개 이상 포함해야 합니다.(5~15자리)");
                continue;
            }
            break;
        }
        System.out.print("이름: ");
        String name = sc.nextLine();
        System.out.print("전화번호: ");
        String phone = sc.nextLine();

        String sql = "INSERT INTO TB-USER(id-user, nm_user, nm_paswd, no_mobile, nm_email, st_status, cd_user_type, da_first_date) VALUES(?,?,?,?,?,?,?,?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, id);
        pstmt.setString(2, name);
        pstmt.setString(3, pw);
        pstmt.setString(4, phone);
        pstmt.setString(5, id);
        pstmt.setString(6, name);
        pstmt.setString(7, phone);
        pstmt.setDate(8, java.sql.Date.valueOf(LocalDate.now()));

        int rows = pstmt.executeUpdate();
        System.out.println("회원가입 되었습니다.");
        pstmt.close();
    }
}
