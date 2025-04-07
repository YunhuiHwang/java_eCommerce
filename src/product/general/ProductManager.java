package product.general;

import java.sql.SQLException;
import static util.ScannerUtil.sc;

public class ProductManager {
    public void runProductManager() throws SQLException {
        while (true) {
            System.out.println("===== 상품보기 =====");

            System.out.println("[1.상품조회] [2.상품검색] [3.뒤로가기]");
            System.out.print("메뉴를 선택하세요: ");
            int num = Integer.parseInt(sc.nextLine());
            switch (num) {
                case 1:

                    break;
                case 2:

                    break;
                case 3:

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
