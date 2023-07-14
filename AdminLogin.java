package shop;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class AdminLogin {
    private static  final String FILE_PATH = "D://xiazai/test.xlsx";
    public boolean login(String username,String password) {
        try {
            // 创建工作簿对象
            FileInputStream file = new FileInputStream(FILE_PATH);
            Workbook workbook = new XSSFWorkbook(file);

            // 获取第五张表
            Sheet sheet = workbook.getSheetAt(4);
            boolean isUserFound = false;

            // 获取第一行
            Row row = sheet.getRow(0);

            // 获取第一列和第二列的单元格
            Cell usernameCell = row.getCell(0);
            Cell passwordCell = row.getCell(1);

            // 获取单元格的值
            String username1 = usernameCell.getStringCellValue();
            String password1 = passwordCell.getStringCellValue();


            // 验证账户和密码
            if (username1.equals(username) && password1.equals(password)) {
                isUserFound = true;
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
