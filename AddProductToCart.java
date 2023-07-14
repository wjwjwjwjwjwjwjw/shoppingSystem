package shop;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class AddProductToCart {
    private static final String FILE_PATH = "D://xiazai/test.xlsx";
    public static void add(Scanner scanner) {//添加购物车商品
        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(3); // 获取第四个表格，索引从0开始

            System.out.println("请输入商品名称：");
            String productName = scanner.nextLine();

            System.out.println("请输入购买数量：");
            int quantity = Integer.parseInt(scanner.nextLine());

            System.out.println("请输入购买价格：");
            double price = Double.parseDouble(scanner.nextLine());

            System.out.println("请输入用户账号：");
            String userAccount = scanner.nextLine();

            Row row = sheet.createRow(sheet.getLastRowNum() + 1); // 创建新行

            int cellIndex = 0;
            Cell cell = row.createCell(cellIndex++);
            cell.setCellValue(productName);

            cell = row.createCell(cellIndex++);
            cell.setCellValue(quantity);

            cell = row.createCell(cellIndex++);
            cell.setCellValue(price);

            cell = row.createCell(cellIndex);
            cell.setCellValue(userAccount);

            try (FileOutputStream outputFile = new FileOutputStream(FILE_PATH)) {
                workbook.write(outputFile);
            }

            System.out.println("商品已成功添加到购物车！");

        } catch (IOException e) {
            System.out.println("添加商品到购物车失败，请重试！");
        }
    }//用户添加成功
}
