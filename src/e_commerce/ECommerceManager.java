package e_commerce;

import product.general.ProductManager;
import user.general.UserManager;
import java.sql.SQLException;

import static util.ScannerUtil.sc;

public class ECommerceManager {
    public static void main(String[] args) throws SQLException {
        UserManager um = new UserManager();
        ProductManager pm = new ProductManager();
        while (true) {
            System.out.println("===== yuni mall =====");
            System.out.println("[1.회원/로그인] [2.상품보기] [3.종료]");
            System.out.print("메뉴를 선택하세요: ");
            int num = Integer.parseInt(sc.nextLine());
            switch (num) {
                case 1:
                    um.runUserManager();
                    break;
                case 2:
                    pm.runProductManager();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("잘못된 입력입니다.");
                    break;
            }
        }
    }
}
