package shop;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

public class BrowseProducts {
    private static final String FILE_PATH = "D://xiazai/test.xlsx";
    public static void browse() {//浏览所有商品
        System.out.println("所有商品：");

        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheet("Sheet2"); // 第二张表

            Iterator<Row> iterator = sheet.iterator();

            while (iterator.hasNext()) {
                Row row = iterator.next();

                Cell codeCell = row.getCell(0);
                Cell nameCell = row.getCell(1);
                Cell manufacturerCell = row.getCell(2);
                Cell dateCell = row.getCell(3);
                Cell modelCell = row.getCell(4);
                Cell purchasePriceCell = row.getCell(5);
                Cell retailPriceCell = row.getCell(6);
                Cell quantityCell = row.getCell(7);

                String code = getCellStringValue2(codeCell);
                String name = getCellStringValue2(nameCell);
                String manufacturer = getCellStringValue2(manufacturerCell);
                String dateString = getCellStringValue2(dateCell);
                String model = getCellStringValue2(modelCell);
                double purchasePrice = getNumericCellValue(purchasePriceCell);
                double retailPrice = getNumericCellValue(retailPriceCell);
                int quantity = (int) getNumericCellValue(quantityCell);

                Date date = null;
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    date = dateFormat.parse(dateString);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                System.out.println("商品编号：" + code);
                System.out.println("商品名称：" + name);
                System.out.println("生产厂家：" + manufacturer);
                System.out.println("生产日期：" + formatDate(date));
                System.out.println("型号：" + model);
                System.out.println("进货价：" + purchasePrice);
                System.out.println("零售价格：" + retailPrice);
                System.out.println("数量：" + quantity);
                System.out.println("----------------");
            }

        } catch (IOException e) {
            System.out.println("浏览商品失败，请重试！");
        }
    }//浏览商品
    private static String getCellStringValue2(Cell cell) {
        if (cell == null) {
            return "";
        }

        if (cell.getCellType() == CellType.STRING) {
            return cell.getStringCellValue();
        } else if (cell.getCellType() == CellType.NUMERIC) {
            if (DateUtil.isCellDateFormatted(cell)) {
                Date date = cell.getDateCellValue();
                return formatDate(date);
            } else {
                DataFormatter dataFormatter = new DataFormatter();
                return dataFormatter.formatCellValue(cell);
            }
        } else if (cell.getCellType() == CellType.FORMULA) {
            if (DateUtil.isCellDateFormatted(cell)) {
                Date date = cell.getDateCellValue();
                return formatDate(date);
            } else {
                return cell.getCellFormula();
            }
        } else {
            return "";
        }
    }
    private static double getNumericCellValue(Cell cell) {
        if (cell == null) {
            return 0.0;
        }

        if (cell.getCellType() == CellType.NUMERIC) {
            return cell.getNumericCellValue();
        } else {
            return 0.0;
        }
    }
    private static String formatDate(Date date) {
        if (date == null) {
            return "";
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }//浏览商品结束
}
