package shop;

import java.util.Scanner;

public class ShoppingSystem {//初始菜单界面
    private Scanner scanner;

    public ShoppingSystem() {
        scanner = new Scanner(System.in);
    }

    public void start() {         //菜单界面
        System.out.println("欢迎使用购物系统！");
        System.out.println("请选择身份：");
        System.out.println("1. 管理员");
        System.out.println("2. 用户");
        System.out.println("3. 退出");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1://进入管理员系统
                Admin admin = new Admin();
                admin.adminLogin(scanner);
                break;
            case 2://进入用户系统
                User user = new User();
                user.userM(scanner);
                break;
            case 3://退出
                System.out.println("谢谢使用，再见！");
                break;
            default:
                System.out.println("无效选择，请重新运行程序！");
                break;
        }

        scanner.close();
    }
}
