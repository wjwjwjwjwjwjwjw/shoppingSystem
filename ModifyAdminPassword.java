package shop;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class ModifyAdminPassword {//修改自身管理员密码
    private static final String ADMIN_USERNAME = "admin";
    private static String ADMIN_PASSWORD = "ynuadmin";
    private static final String FILE_PATH = "D://xiazai/test.xlsx";

    public boolean modify(String userAccount, String newAdminPassword) {
        try {
            FileInputStream file = new FileInputStream(FILE_PATH);
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(4); // 第三张表
            boolean isUserFound = false;

            for (Row row : sheet) {
                Cell cell = row.getCell(0); // 用户账号在第一列

                if (cell != null && cell.getCellType() == CellType.STRING && cell.getStringCellValue().equals(userAccount)) {
                    Cell passwordCell = row.getCell(1); // 密码在第二列
                    passwordCell.setCellValue(newAdminPassword);
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

    }
}