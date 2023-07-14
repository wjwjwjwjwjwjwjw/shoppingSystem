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

public class RemoveProductFromCart {
    private static final String FILE_PATH = "D://xiazai/test.xlsx";
    public static void remove(Scanner scanner) {//移除购物车中的商品
        System.out.println("请输入商品名称：");
        String productName = scanner.nextLine();

        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(3); // 获取第四张表，索引从0开始

            int lastRow = sheet.getLastRowNum(); // 获取最后一行的索引

            for (int rowIndex = 0; rowIndex <= lastRow; rowIndex++) {
                Row row = sheet.getRow(rowIndex);

                if (row != null) {
                    Cell cell = row.getCell(0); // 假设商品名称在第一列，根据实际情况调整索引

                    if (cell != null && cell.getStringCellValue().equals(productName)) {
                        sheet.removeRow(row);
                        break;
                    }
                }
            }

            try (FileOutputStream outputFile = new FileOutputStream(FILE_PATH)) {
                workbook.write(outputFile);
            }

            System.out.println("商品已成功从购物车移除！");

        } catch (IOException e) {
            System.out.println("从购物车移除商品失败，请重试！");
        }
    }//删除购物车中的商品结束
}
