package shop;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class DeleteUserInfo {
    private static final String FILE_PATH = "D://xiazai/test.xlsx";
    public static boolean delete(int userId) {
        try {
            FileInputStream file = new FileInputStream(FILE_PATH);
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0); // 第一张表
            boolean isUserFound = false;

            for (Row row : sheet) {
                if (row.getRowNum() == 0) { // 跳过标题行
                    continue;
                }

                Cell cell = row.getCell(0); // ID在第一列

                if (cell != null && cell.getCellType() == CellType.NUMERIC && cell.getNumericCellValue() == userId) {
                    sheet.removeRow(row);
                    isUserFound = true;
                    break;
                }
            }

            file.close();

            if (isUserFound) {
                FileOutputStream outFile = new FileOutputStream(FILE_PATH);
                workbook.write(outFile);
                outFile.close();
            }

            return isUserFound;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }//根据ID删除用户结束
}
