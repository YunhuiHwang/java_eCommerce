package final_e_commerce.user;

import final_e_commerce.user.Users;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static final_e_commerce.util.ScannerUtil.sc;

public class UserService {
    private final UserDAO userDAO;

    public UserService(Connection conn) {
        this.userDAO = new UserDAO(conn);
    }

    public Users userLogin() throws SQLException {
        System.out.println("===== 로그인 =====");
        while (true) {
            System.out.print("아이디(이메일): ");
            String id = sc.nextLine();

            Users user = userDAO.findByEmail(id);
            if (user == null) {
                System.out.println("가입된 이메일이 아닙니다.");
                continue;
            }

            System.out.print("비밀번호: ");
            String pw = sc.nextLine();

            if (!user.getPassword().equals(pw)) {
                System.out.println("비밀번호가 틀렸습니다.");
                continue;
            }

            System.out.println(user.getName() + "님 환영합니다.");
            return user;
        }
    }

    public void userRegister() throws SQLException {
        System.out.println("===== 회원 가입 =====");
        System.out.println("[1.일반회원가입] [2.관리자회원가입]");
        String type = sc.nextLine().equals("2") ? "20" : "10";

        String id;
        while (true) {
            System.out.print("아이디(이메일): ");
            id = sc.nextLine();
            if (!id.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                System.out.println("이메일 형식이 아닙니다.");
                continue;
            }
            if (userDAO.findByEmail(id) != null) {
                System.out.println("이미 등록된 이메일입니다.");
                return;
            }
            break;
        }

        String pw;
        while (true) {
            System.out.print("비밀번호: ");
            pw = sc.nextLine();
            if (!pw.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{5,15}$")) {
                System.out.println("비밀번호는 영문자와 숫자를 포함해야 하며, 5~15자리여야 합니다.");
                continue;
            }
            break;
        }

        System.out.print("이름: ");
        String name = sc.nextLine();
        System.out.print("전화번호: ");
        String phone = sc.nextLine();

        Users user = new Users();
        user.setId(id);
        user.setPassword(pw);
        user.setName(name);
        user.setPhone(phone);
        user.setUserType(type);
        user.setStatus("ST01");

        int result = userDAO.insertUser(user);
        if (result > 0) {
            System.out.println("회원가입이 완료되었습니다.");
        } else {
            System.out.println("[오류] 회원가입 실패");
        }
    }

    public void showUserList() throws SQLException {
        List<Users> list = userDAO.selectAllUsers();
        System.out.println("===== 전체 회원 목록 =====");
        for (Users u : list) {
            System.out.printf("ID: %s | 이름: %s | 전화: %s | 유형: %s | 상태: %s\n",
                    u.getId(), u.getName(), u.getPhone(), u.getUserType(), u.getStatus());
        }
    }

    public void runMyPage(Users user) throws SQLException {
        while (true) {
            System.out.println("===== 마이페이지 =====");
            System.out.println("[1.회원정보 수정] [2.비밀번호 변경] [3.탈퇴 요청] [4.뒤로가기]");
            System.out.print("선택: ");
            String input = sc.nextLine();
            switch (input) {
                case "1":
                    updateUserInfo(user);
                    break;
                case "2":
                    updateUserPassword(user);
                    break;
                case "3":
                    deactivateUser(user);
                    return;
                case "4":
                    return;
                default:
                    System.out.println("잘못된 입력입니다.");
            }
        }
    }

    private void updateUserInfo(Users user) throws SQLException {
        System.out.print("새 이름: ");
        String name = sc.nextLine();
        System.out.print("새 전화번호: ");
        String phone = sc.nextLine();
        int result = userDAO.updateUserInfo(user.getId(), name, phone);
        if (result > 0) {
            user.setName(name);
            user.setPhone(phone);
            System.out.println("회원정보가 수정되었습니다.");
        } else {
            System.out.println("회원정보 수정 실패했습니다.");
        }
    }

    private void updateUserPassword(Users user) throws SQLException {
        System.out.print("현재 비밀번호: ");
        String currentPw = sc.nextLine();
        if (!currentPw.equals(user.getPassword())) {
            System.out.println("비밀번호가 일치하지 않습니다.");
            return;
        }
        System.out.print("새 비밀번호: ");
        String newPw = sc.nextLine();
        int result = userDAO.updatePassword(user.getId(), newPw);
        if (result > 0) {
            user.setPassword(newPw);
            System.out.println("비밀번호가 변경되었습니다.");
        } else {
            System.out.println("비밀번호 변경 실패했습니다.");
        }
    }

    private void deactivateUser(Users user) throws SQLException {
        System.out.print("정말 탈퇴하시겠습니까? (Y/N): ");
        String confirm = sc.nextLine();
        if (confirm.equalsIgnoreCase("Y")) {
            int result = userDAO.updateStatus(user.getId(), "ST00");
            if (result > 0) {
                System.out.println("탈퇴 요청이 완료되었습니다.");
            } else {
                System.out.println("탈퇴 요청 실패했습니다.");
            }
        }
    }
}