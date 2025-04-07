package user.general;

import java.sql.SQLException;

import static util.ScannerUtil.sc;

public class UserManager {
    public void runUserManager() throws SQLException {
        UserRegister ur = new UserRegister();
        UserLogin ul = new UserLogin();

        while (true) {
            System.out.println("===== 회원관리 및 로그인 =====");
            System.out.println("[1.회원 가입] [2.로그인] [3.회원 정보] [4.뒤로가기]");
            System.out.print("메뉴를 선택하세요: ");
            int num = Integer.parseInt(sc.nextLine());
            switch (num) {
                case 1:
                    ur.userRegister();
                    break;
                case 2:
                    ul.userLogin();
                    break;
                case 3:
                    ul.userPage();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("잘못된 입력입니다.");
                    break;
            }
        }
    }
}
