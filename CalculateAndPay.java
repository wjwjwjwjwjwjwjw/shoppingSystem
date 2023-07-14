package shop;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class CalculateAndPay {
    private static final String FILE_PATH = "D://xiazai/test.xlsx";
    public static void calculate(Scanner scanner) {//购物车商品结算
        try (
                FileInputStream file = new FileInputStream(FILE_PATH);
                Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(3); // 获取第四个表格，索引从0开始

            System.out.println("请输入用户账号：");
            String userAccount = scanner.nextLine();

            try (FileOutputStream outputFile = new FileOutputStream(FILE_PATH)) {
                workbook.write(outputFile);
            }

            double totalPrice = 0.0;
            int lastRow = sheet.getLastRowNum();

            for (int rowIndex = 1; rowIndex <= lastRow; rowIndex++) {
                Row currentRow = sheet.getRow(rowIndex);
                Cell accountCell = currentRow.getCell(3); // 第四列为用户账号

                if (accountCell != null && accountCell.getCellType() == CellType.STRING) {
                    String account = accountCell.getStringCellValue();

                    if (account.equals(userAccount)) {
                        Cell priceCell = currentRow.getCell(2); // 第三列为价格
                        Cell quantityCell = currentRow.getCell(1); // 第二列为数量

                        if (priceCell != null && priceCell.getCellType() == CellType.NUMERIC
                                && quantityCell != null && quantityCell.getCellType() == CellType.NUMERIC) {
                            double rowPrice = priceCell.getNumericCellValue();
                            int rowQuantity = (int) quantityCell.getNumericCellValue();

                            totalPrice += rowPrice * rowQuantity;
                        }
                    }
                }
            }

            System.out.println("您的购物车总价为：" + totalPrice);

        } catch (IOException e) {
            System.out.println("添加商品到购物车失败，请重试！");
        }
    }//结算付款
}
