package shop;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Iterator;
public class User {
    private static final String FILE_PATH = "D://xiazai/test.xlsx";

    public void userM(Scanner scanner) {
        System.out.println("用户界面");
        Scanner scanner2 = new Scanner(System.in);
        while (true) {
            System.out.println("欢迎使用购物系统");
            System.out.println("1. 注册");
            System.out.println("2. 登录");
            System.out.println("0. 退出");

            System.out.println("请选择操作：");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    registerUser(scanner);//注册
                    break;
                case 2:
                    Shopping shopping = new Shopping();
                    if (loginUser(scanner)) {//登录
                        shopping.shopM(scanner);
                        //登录成功进入购物界面

                    }
                    break;
                case 0:
                    System.out.println("谢谢使用，再见！");
                    return;
                default:
                    System.out.println("无效的操作，请重新选择！");
                    break;
            }
        }
    }
    private static void registerUser(Scanner scanner) {//注册
        System.out.println("用户注册");
        System.out.print("请输入用户名：");
        String username = scanner.nextLine();

        System.out.print("请输入密码：");
        String password = scanner.nextLine();

        // 将用户名和密码存储到Excel表中
        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(2);

            // 获取最后一行的索引
            int lastRowNum = sheet.getLastRowNum();
            Row row = sheet.createRow(lastRowNum + 1);

            // 在用户名和密码所在的单元格中写入数据
            Cell usernameCell = row.createCell(0);
            usernameCell.setCellValue(username);

            Cell passwordCell = row.createCell(1);
            passwordCell.setCellValue(password);

            // 将修改后的Workbook写回到文件中
            try (FileOutputStream outFile = new FileOutputStream(FILE_PATH)) {
                workbook.write(outFile);
                System.out.println("注册成功！");
            }

        } catch (IOException e) {
            System.out.println("注册失败，请重试！");
        }
    }//用户注册实现

    //private static Workbook getOrCreateWorkbook(String filePath) throws IOException {
//        File file = new File(filePath);
//        Workbook workbook;
//        if (file.exists()) {
//            workbook = WorkbookFactory.create(file);
//        } else {
//            workbook = new XSSFWorkbook();
//        }
//        return workbook;
//    }

    private static boolean loginUser(Scanner scanner) {//登录
        System.out.println("登录页面");
        System.out.println("请输入账号：");
        String account = scanner.nextLine();
        System.out.println("请输入密码：");
        String password = scanner.nextLine();

        // 在Excel表中查找对应的账号和密码
        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(2); // 第三张表

            Iterator<Row> iterator = sheet.iterator();

            while (iterator.hasNext()) {
                Row row = iterator.next();

                // 获取当前行的账号和密码
                Cell accountCell = row.getCell(0);
                String storedAccount = getCellStringValue(accountCell);

                Cell passwordCell = row.getCell(1);
                String storedPassword = getCellStringValue(passwordCell);

                // 检查账号和密码是否匹配
                if (storedAccount.equals(account) && storedPassword.equals(password)) {
                    System.out.println("登录成功！");
                    return true;
                }
            }

            System.out.println("账号或密码错误，请重新登录！");
            return false;

        } catch (IOException e) {
            System.out.println("登录失败，请重试！");
            return false;
        }
    }//用户登录

    private static String getCellStringValue(Cell cell) {//用户登录结束
        if (cell == null) {
            return "";
        }

        DataFormatter dataFormatter = new DataFormatter();
        return dataFormatter.formatCellValue(cell);
    }//用户登录结束
}
