package shop;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;

public class DisplayUserInfo {
    private static final String FILE_PATH = "D://xiazai/test.xlsx";
    public static void display(int userId){
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
                    isUserFound = true;

                    for (Cell dataCell : row) {
                        // 读取每个单元格的值并输出
                        String cellValue = "";

                        switch (dataCell.getCellType()) {
                            case STRING:
                                cellValue = dataCell.getStringCellValue();
                                break;
                            case NUMERIC:
                                cellValue = String.valueOf(dataCell.getNumericCellValue());
                                break;
                            case BOOLEAN:
                                cellValue = String.valueOf(dataCell.getBooleanCellValue());
                                break;
                            case FORMULA:
                                cellValue = dataCell.getCellFormula();
                                break;
                            default:
                                cellValue = "";
                        }

                        System.out.print(cellValue + "\t");
                    }

                    System.out.println(); // 换行
                    break;
                }
            }

            file.close();

            if (!isUserFound) {
                System.out.println("未找到指定的用户信息！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//根据ID查询用户结束
}

