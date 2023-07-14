package shop;

import java.util.Scanner;

public class PasswordManagement {// 管理员密码管理
    private static   String ADMIN_USERNAME = "admin";
    private static  String ADMIN_PASSWORD = "ynuadmin";
    private static  final String FILE_PATH = "D://xiazai/test.xlsx";
    public void password(Scanner scanner) {
        System.out.println("密码管理");
        System.out.println("请选择操作：");
        System.out.println("1. 修改管理员密码");//进入excel表4，修改对应的管理员账户和密码
        System.out.println("2. 重置用户密码");//需要进入到excel的表三，读取对应的账号，修改其密码

        int choice = scanner.nextInt();
        scanner.nextLine(); // 消费掉输入缓冲区中的换行符

        switch (choice) {
            case 1://实现
                ModifyAdminPassword modifyAdminPassword = new ModifyAdminPassword();
                System.out.print("请输入新的管理员密码：");
                String newAdminPassword = scanner.nextLine();
                boolean isPasswordReset2 = modifyAdminPassword.modify(ADMIN_USERNAME, newAdminPassword);//给excel表中的相应数据修改

                if (isPasswordReset2) {
                    System.out.println("用户密码已重置成功！");
                } else {
                    System.out.println("重置用户密码失败，请检查用户账号！");
                }
                break;
            default:
                System.out.println("无效的选择！");
                break;
            case 2:  //找到excel表中的账号，修改其密码
                System.out.print("请输入要重置密码的用户账号：");
                String userAccount = scanner.nextLine();
                System.out.print("请输入新密码：");
                String newPassword = scanner.nextLine();//输入账号和新密码后
                ResetUserPassword resetUserPassword = new ResetUserPassword();
                boolean isPasswordReset = resetUserPassword.reset(userAccount, newPassword);//给excel表中的相应数据修改

                if (isPasswordReset) {
                    System.out.println("用户密码已重置成功！");
                } else {
                    System.out.println("重置用户密码失败，请检查用户账号！");
                }
                break;
        }
    }

//    private static boolean resetUserPassword(String userAccount, String newPassword) {//写入用户的账号和新密码
//        try {
//            FileInputStream file = new FileInputStream(FILE_PATH);
//            Workbook workbook = new XSSFWorkbook(file);
//            Sheet sheet = workbook.getSheetAt(2); // 第三张表
//            boolean isUserFound = false;
//
//            for (Row row : sheet) {
//                Cell cell = row.getCell(0); // 用户账号在第一列
//
//                if (cell != null && cell.getCellType() == CellType.STRING && cell.getStringCellValue().equals(userAccount)) {
//                    Cell passwordCell = row.getCell(1); // 密码在第二列
//                    passwordCell.setCellValue(newPassword);
//                    isUserFound = true;
//                    break;
//                }
//            }
//
//            file.close();
//
//            if (isUserFound) {
//                FileOutputStream outFile = new FileOutputStream(FILE_PATH);
//                workbook.write(outFile);
//                outFile.close();
//            }
//
//            return isUserFound;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }//管理员管理用户密码结束
}
