package shop;

import java.util.Scanner;

public class Admin {//管理员系统
    private static  String ADMIN_USERNAME = "admin";
    private static  String ADMIN_PASSWORD = "ynuadmin";
    private static final String FILE_PATH = "D://xiazai/test.xlsx";

    public void adminLogin(Scanner scanner) {//管理员登录
        System.out.println("管理员登录");
        AdminLogin adminLogin = new AdminLogin();
        System.out.print("请输入用户名：");
        String username = scanner.nextLine();
        System.out.print("请输入密码：");
        String password = scanner.nextLine();
        //adminLogin.login(username,password);

        boolean isPasswordReset = adminLogin.login(username,password);//给excel表中的相应数据修改

        if (isPasswordReset) {
            System.out.println("用户登录成功！");
            adminMenu(scanner);
        } else {
            System.out.println("用户登录失败，请重新登录！");
        }
    }

    private void adminMenu(Scanner scanner) {//登录成功后的管理员菜单界面
        // 管理员菜单的代码
        while (true) {
            System.out.println("\n管理员界面");//菜单界面
            System.out.println("请选择操作：");
            System.out.println("1. 密码管理");
            System.out.println("2. 客户管理");
            System.out.println("3. 商品管理");
            System.out.println("4. 返回");

            int choice = scanner.nextInt();
            scanner.nextLine(); // 消费掉输入缓冲区中的换行符

            if (choice == 4) {//退出
                break;
            }

            switch (choice) {
                case 1://密码管理
                    PasswordManagement passwordManagement = new PasswordManagement();
                    passwordManagement.password(scanner);//密码管理
                    break;
                case 2://客户管理
                    CustomerManagement customerManagement =new CustomerManagement();
                    customerManagement.customerM(scanner);//客户管理
                    break;
                case 3://商品管理
                    ProductManagement productManagement =new ProductManagement();
                    productManagement.productM(scanner);//商品管理
                    break;
                default:
                    System.out.println("无效选择，请重新输入！");
            }
        }
    }
}
