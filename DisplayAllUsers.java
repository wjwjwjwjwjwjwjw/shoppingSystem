package shop;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class DisplayAllUsers {
    private static final String FILE_PATH = "D://xiazai/test.xlsx";
    public void display() {
        try {
            FileInputStream file = new FileInputStream(FILE_PATH);
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0); // 第一张表

            for (Row row : sheet) {
                for (Cell cell : row) {
                    // 读取每个单元格的值并输出
                    String cellValue = "";
                    switch (cell.getCellType()) {
                        case STRING:
                            cellValue = cell.getStringCellValue();
                            break;
                        case NUMERIC:
                            if (DateUtil.isCellDateFormatted(cell)) {
                                Date date = cell.getDateCellValue();
                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                cellValue = dateFormat.format(date);
                            } else {
                                double numericValue = cell.getNumericCellValue();
                                long phoneNumber = (long) numericValue;
                                cellValue = String.valueOf(phoneNumber);
                            }
                            break;
                        case BOOLEAN:
                            cellValue = String.valueOf(cell.getBooleanCellValue());
                            break;
                        case FORMULA:
                            cellValue = cell.getCellFormula();
                            break;
                        default:
                            cellValue = "";
                    }
                    System.out.print(cellValue + "\t");
                }
                System.out.println(); // 换行
            }

            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
