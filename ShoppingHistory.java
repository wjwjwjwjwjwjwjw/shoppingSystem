package shop;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class ShoppingHistory {
    private static final String FILE_PATH = "D://xiazai/test.xlsx";
    public static void history(Scanner scanner){//购物车商品结算
        System.out.println("购物历史");
        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(3); // 获取第四个表格，索引从0开始

            System.out.println("请输入用户账号：");
            String userAccount = scanner.nextLine();

            int lastRow = sheet.getLastRowNum();
            boolean found = false;

            for (int rowIndex = 1; rowIndex <= lastRow; rowIndex++) {
                Row currentRow = sheet.getRow(rowIndex);
                Cell accountCell = currentRow.getCell(3); // 第四列为用户账号

                if (accountCell != null && accountCell.getCellType() == CellType.STRING) {
                    String account = accountCell.getStringCellValue();

                    if (account.equals(userAccount)) {
                        found = true;

                        Cell productNameCell = currentRow.getCell(0); // 第一列为商品名称
                        Cell quantityCell = currentRow.getCell(1); // 第二列为数量
                        Cell priceCell = currentRow.getCell(2); // 第三列为价格

                        if (productNameCell != null && productNameCell.getCellType() == CellType.STRING
                                && quantityCell != null && quantityCell.getCellType() == CellType.NUMERIC
                                && priceCell != null && priceCell.getCellType() == CellType.NUMERIC) {
                            String productName = productNameCell.getStringCellValue();
                            int quantity = (int) quantityCell.getNumericCellValue();
                            double price = priceCell.getNumericCellValue();

                            System.out.println("商品名称: " + productName);
                            System.out.println("数量: " + quantity);
                            System.out.println("价格: " + price);
                            System.out.println("--------------------------");
                        }
                    }
                }
            }

            if (!found) {
                System.out.println("未找到与账号匹配的购买记录。");
            }

        } catch (IOException e) {
            System.out.println("读取购物车信息失败，请重试！");
        }
}
}
