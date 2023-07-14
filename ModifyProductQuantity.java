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

public class ModifyProductQuantity {
    private static final String FILE_PATH = "D://xiazai/test.xlsx";
    public static void modify(Scanner scanner) {//进入第四张表，找到对应的名称，找到数量，修改
        System.out.println("请输入商品名称：");
        String productName = scanner.nextLine();

        System.out.println("请输入修改后的商品数量：");
        int newQuantity = Integer.parseInt(scanner.nextLine());

        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(3); // 获取第四张表，索引从0开始

            int lastRow = sheet.getLastRowNum(); // 获取最后一行的索引

            for (int rowIndex = 0; rowIndex <= lastRow; rowIndex++) {
                Row row = sheet.getRow(rowIndex);

                if (row != null) {
                    Cell cell = row.getCell(0); // 假设商品名称在第一列，根据实际情况调整索引

                    if (cell != null && cell.getStringCellValue().equals(productName)) {
                        Cell quantityCell = row.getCell(1); // 假设商品数量在第二列，根据实际情况调整索引
                        if (quantityCell == null) {
                            quantityCell = row.createCell(1);
                        }
                        quantityCell.setCellValue(newQuantity);
                        break;
                    }
                }
            }

            try (FileOutputStream outputFile = new FileOutputStream(FILE_PATH)) {
                workbook.write(outputFile);
            }

            System.out.println("商品数量已成功修改！");

        } catch (IOException e) {
            System.out.println("修改商品数量失败，请重试！");
        }
    }//修改购物车商品数量
}
