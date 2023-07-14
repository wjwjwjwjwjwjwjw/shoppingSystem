package shop;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ProductManagement {//管理员 商品管理
    private static final String FILE_PATH = "D://xiazai/test.xlsx";
    public void productM(Scanner scanner) {
        System.out.println("商品管理");
        System.out.println("请选择操作：");
        System.out.println("1. 列出所有商品信息");
        System.out.println("2. 添加商品");
        System.out.println("3. 修改商品");
        System.out.println("4. 删除商品");
        System.out.println("5. 查询商品信息");

        int choice = scanner.nextInt();
        scanner.nextLine(); // 消费掉输入缓冲区中的换行符

        switch (choice) {
            case 1:
                // 在这里完成列出所有商品信息的操作，利用test.xlsx文件的表二进行遍历操作，输出商品信息
                System.out.println("以下是所有商品信息：");
                displayAllProducts();
                break;
            case 2:
                // 在这里完成添加商品的操作，输入商品信息，将其写入test.xlsx文件的表二中
                addProduct(scanner);
                System.out.println("添加商品成功！");
                break;
            case 3:
                //修改指定商品的信息
                System.out.println("请输入要修改的商品编号：");
                int productId = Integer.parseInt(scanner.nextLine());
                System.out.println("请输入要修改的商品信息：");
                updateProduct(productId, scanner);
                System.out.println("修改商品成功！");
                break;
            case 4://删除指定商品
                System.out.print("请输入要删除的商品编号：");
                int productId1 = Integer.parseInt(scanner.nextLine());
                deleteProduct(productId1);
                System.out.println("商品删除成功！");
                break;
            case 5://查询指定商品
                System.out.println("请输入要查询的商品编号：");
                int productId2 = Integer.parseInt(scanner.nextLine());
                searchProduct(productId2);
                break;
            default:
                System.out.println("无效选择，请重新输入！");
        }
    }//管理员 商品管理
    private static void displayAllProducts() {//显示所有商品
        try {
            FileInputStream file = new FileInputStream(FILE_PATH);
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(1); // 第二张表

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
    }//列出商品信息
    private static void addProduct(Scanner scanner) {
        try {
            FileInputStream file = new FileInputStream(FILE_PATH);
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(1); // 第二张表

            // 获取新行的索引，即最后一行的索引加1
            int newRowIdx = sheet.getLastRowNum() + 1;
            Row newRow = sheet.createRow(newRowIdx);

            // 获取商品信息
            System.out.print("请输入商品编号：");
            int productId = Integer.parseInt(scanner.nextLine());

            System.out.print("请输入商品名称：");
            String productName = scanner.nextLine();

            System.out.print("请输入生产厂家：");
            String manufacturer = scanner.nextLine();

            System.out.print("请输入生产日期：");
            String productionDate = scanner.nextLine();

            System.out.print("请输入型号：");
            String model = scanner.nextLine();

            System.out.print("请输入进货价：");
            double purchasePrice = Double.parseDouble(scanner.nextLine());

            System.out.print("请输入零售价格：");
            double retailPrice = Double.parseDouble(scanner.nextLine());

            System.out.print("请输入数量：");
            int quantity = Integer.parseInt(scanner.nextLine());

            // 在新行上创建单元格，并设置对应的值
            Cell productIdCell = newRow.createCell(0); // 商品编号列
            productIdCell.setCellValue(productId);

            Cell productNameCell = newRow.createCell(1); // 商品名称列
            productNameCell.setCellValue(productName);

            Cell manufacturerCell = newRow.createCell(2); // 生产厂家列
            manufacturerCell.setCellValue(manufacturer);

            Cell productionDateCell = newRow.createCell(3); // 生产日期列
            productionDateCell.setCellValue(productionDate);

            Cell modelCell = newRow.createCell(4); // 型号列
            modelCell.setCellValue(model);

            Cell purchasePriceCell = newRow.createCell(5); // 进货价列
            purchasePriceCell.setCellValue(purchasePrice);

            Cell retailPriceCell = newRow.createCell(6); // 零售价格列
            retailPriceCell.setCellValue(retailPrice);

            Cell quantityCell = newRow.createCell(7); // 数量列
            quantityCell.setCellValue(quantity);

            file.close();

            FileOutputStream outFile = new FileOutputStream(FILE_PATH);
            workbook.write(outFile);
            outFile.close();

            System.out.println("商品信息已成功添加！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//添加商品信息

    private static void updateProduct(int productId, Scanner scanner) {//修改商品信息
        try {
            FileInputStream file = new FileInputStream(FILE_PATH);
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(1); // 第二张表

            // 查找操作所需修改的行
            int rowIndex = findProductRowIndex(sheet, productId);

            if (rowIndex != -1) {
                Row row = sheet.getRow(rowIndex);

                System.out.print("请输入新的商品名称：");
                String productName = scanner.nextLine();
                updateCellValue(row, 1, productName);

                System.out.print("请输入新的生产厂家：");
                String manufacturer = scanner.nextLine();
                updateCellValue(row, 2, manufacturer);

                System.out.print("请输入新的生产日期：");
                String productionDate = scanner.nextLine();
                updateCellValue(row, 3, productionDate);

                System.out.print("请输入新的型号：");
                String model = scanner.nextLine();
                updateCellValue(row, 4, model);

                System.out.print("请输入新的进货价：");
                double purchasePrice = Double.parseDouble(scanner.nextLine());
                updateCellValue(row, 5, purchasePrice);

                System.out.print("请输入新的零售价格：");
                double retailPrice = Double.parseDouble(scanner.nextLine());
                updateCellValue(row, 6, retailPrice);

                System.out.print("请输入新的数量：");
                int quantity = Integer.parseInt(scanner.nextLine());
                updateCellValue(row, 7, quantity);

                FileOutputStream outFile = new FileOutputStream(FILE_PATH);
                workbook.write(outFile);
                outFile.close();

                System.out.println("商品信息已成功修改！");
            } else {
                System.out.println("商品编号不存在！");
            }

            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//修改商品信息
    private static int findProductRowIndex(Sheet sheet, int productId) {
        for (Row row : sheet) {
            Cell cell = row.getCell(0); // 商品编号列

            if (cell != null && cell.getCellType() == CellType.NUMERIC && cell.getNumericCellValue() == productId) {
                return row.getRowNum();
            }
        }

        return -1; // 商品编号不存在
    }//找到列
    private static void updateCellValue(Row row, int columnIndex, Object value) {
        Cell cell = row.getCell(columnIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

        if (value instanceof String) {
            cell.setCellValue((String) value);
        } else if (value instanceof Double) {
            cell.setCellValue((Double) value);
        } else if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        }
    }//修改，更新数据，修改商品信息结束

    private static void deleteProduct(int productId) {//删除商品
        try {
            FileInputStream file = new FileInputStream(FILE_PATH);
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(1); // 第二张表

            // 查找操作所需删除的行
            int rowIndex = findProductRowIndex2(sheet, productId);

            if (rowIndex != -1) {
                sheet.removeRow(sheet.getRow(rowIndex));
                System.out.println("商品信息已成功删除！");
            } else {
                System.out.println("商品编号不存在！");
            }

            file.close();

            FileOutputStream outFile = new FileOutputStream(FILE_PATH);
            workbook.write(outFile);
            outFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//删除商品信息
    private static int findProductRowIndex2(Sheet sheet, int productId) {
        for (Row row : sheet) {
            Cell cell = row.getCell(0); // 商品编号列

            if (cell != null && cell.getCellType() == CellType.NUMERIC && cell.getNumericCellValue() == productId) {
                return row.getRowNum();
            }
        }

        return -1; // 商品编号不存在
    }//找到列,删除信息结束

    private static void searchProduct(int productId) {//查询商品
        try {
            FileInputStream file = new FileInputStream(FILE_PATH);
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(1); // 第二张表

            // 查找操作所需查询的行
            int rowIndex = findProductRowIndex(sheet, productId);

            if (rowIndex != -1) {
                Row row = sheet.getRow(rowIndex);

                // 获取商品信息
                String productName = getCellValue(row, 1);
                String manufacturer = getCellValue(row, 2);
                String productionDate = getCellValue(row, 3);
                String model = getCellValue(row, 4);
                String purchasePriceStr = getCellValue(row, 5);
                String retailPriceStr = getCellValue(row, 6);
                double quantity = Double.parseDouble(getCellValue(row, 7));
                // 如果你需要将浮点数转换为整数，可以使用如下方式：
                int quantityInt = (int) quantity;

                // 解析价格值
                double purchasePrice = Double.valueOf(purchasePriceStr);
                double retailPrice = Double.valueOf(retailPriceStr);

                // 显示商品信息
                System.out.println("商品名称：" + productName);
                System.out.println("生产厂家：" + manufacturer);
                System.out.println("生产日期：" + productionDate);
                System.out.println("型号：" + model);
                System.out.println("进货价：" + purchasePrice);
                System.out.println("零售价格：" + retailPrice);
                System.out.println("数量：" + quantity);
            } else {
                System.out.println("商品编号不存在！");
            }

            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//查询商品
    private static int findProductRowIndex3(Sheet sheet, int productId) {
        for (Row row : sheet) {
            Cell cell = row.getCell(0); // 商品编号列

            if (cell != null && cell.getCellType() == CellType.NUMERIC && cell.getNumericCellValue() == productId) {
                return row.getRowNum();
            }
        }

        return -1; // 商品编号不存在
    }//找到列
    private static String getCellValue(Row row, int columnIndex) {
        Cell cell = row.getCell(columnIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

        if (cell.getCellType() == CellType.STRING) {
            return cell.getStringCellValue();
        } else if (cell.getCellType() == CellType.NUMERIC) {
            return String.valueOf(cell.getNumericCellValue());
        } else {
            return "";
        }
    }//得到对应值，查询结束
}
