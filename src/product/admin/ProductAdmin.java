package product.admin;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static util.ScannerUtil.sc;

public class ProductAdmin {
    public void runPAManager() throws SQLException {
        while (true) {
            System.out.println("===== 상품 관리 =====");
            System.out.println("[1.상품등록] [2.상품수정] [3.상품삭제] [4.상품조회] [5.뒤로가기]");
            System.out.print("메뉴를 선택하세요: ");
            int num = Integer.parseInt(sc.nextLine());
            switch (num) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    editProduct();
                    break;
                case 3:
                    deleteProduct();
                    break;
                case 4:
                    viewProduct();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("잘못된 입력입니다.");
                    break;
            }
        }
    }

    public void addProduct() throws SQLException {
        System.out.println("===== 상품 등록 =====");
        System.out.print("상품명: ");
        String name = sc.nextLine();
        System.out.print("상세설명: ");
        String info = sc.nextLine();
        System.out.print("가격: ");
        int price = Integer.parseInt(sc.nextLine());
        System.out.print("재고: ");
        int quantity = Integer.parseInt(sc.nextLine());

        String sql = "INSERT INTO TB-PRODUCT(nm_product, nm_detail_explain, qt_sale_price, qt_stock) VALUES(?,?,?,?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, name);
        pstmt.setString(2, info);
        pstmt.setInt(3, price);
        pstmt.setInt(4, quantity);
    }

    public void editProduct() throws SQLException {
        System.out.println("===== 상품 수정 =====");
    }

    public void deleteProduct() throws SQLException {
        System.out.println("===== 상품 삭제 =====");
    }

    public void viewProduct() throws SQLException {
        System.out.println("===== 상품 조회 =====");
    }
}
