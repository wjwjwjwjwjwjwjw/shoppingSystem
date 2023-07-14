package shop;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;
public class Shopping {
    private static final String FILE_PATH = "D://xiazai/test.xlsx";
    public void shopM(Scanner scanner) {
        while (true) {
            System.out.println("购物界面");
            System.out.println("1. 浏览所有商品");
            System.out.println("2. 将商品加入购物车");
            System.out.println("3. 将商品移除购物车");
            System.out.println("4. 修改购买商品的数量");
            System.out.println("5. 进行购物车商品数量价格计算并付款");
            System.out.println("6. 购物历史");
            System.out.println("0. 返回上一级");

            System.out.println("请选择操作：");
            int choice = Integer.parseInt(scanner.nextLine());

            // 根据选择执行相应的操作
            switch (choice) {
                case 1:
                    BrowseProducts browseProducts = new BrowseProducts();
                    browseProducts.browse();//浏览商品
                    break;
                case 2:
                    AddProductToCart addProductToCart = new AddProductToCart();
                    addProductToCart.add(scanner);//将商品加入购物车
                    break;
                case 3:
                    RemoveProductFromCart removeProductFromCart = new RemoveProductFromCart();
                    removeProductFromCart.remove(scanner);//将商品移除购物车
                    break;
                case 4:
                    ModifyProductQuantity modifyProductQuantity = new ModifyProductQuantity();
                    modifyProductQuantity.modify(scanner);//修改购物车中商品的数量
                    break;
                case 5:
                    CalculateAndPay calculateAndPay = new CalculateAndPay();
                    calculateAndPay.calculate(scanner);//结算，付款
                    break;
                case 6:
                    ShoppingHistory shoppingHistory = new ShoppingHistory();
                    shoppingHistory.history(scanner);//购物历史
                    break;
                case 0:
                    return;
                default:
                    System.out.println("无效的操作，请重新选择！");
                    break;
            }
        }
    }//购物菜单界面

//    private static void browseProducts() {//浏览所有商品
//        System.out.println("所有商品：");
//
//        try (FileInputStream file = new FileInputStream(FILE_PATH);
//             Workbook workbook = new XSSFWorkbook(file)) {
//
//            Sheet sheet = workbook.getSheet("Sheet2"); // 第二张表
//
//            Iterator<Row> iterator = sheet.iterator();
//
//            while (iterator.hasNext()) {
//                Row row = iterator.next();
//
//                Cell codeCell = row.getCell(0);
//                Cell nameCell = row.getCell(1);
//                Cell manufacturerCell = row.getCell(2);
//                Cell dateCell = row.getCell(3);
//                Cell modelCell = row.getCell(4);
//                Cell purchasePriceCell = row.getCell(5);
//                Cell retailPriceCell = row.getCell(6);
//                Cell quantityCell = row.getCell(7);
//
//                String code = getCellStringValue2(codeCell);
//                String name = getCellStringValue2(nameCell);
//                String manufacturer = getCellStringValue2(manufacturerCell);
//                String dateString = getCellStringValue2(dateCell);
//                String model = getCellStringValue2(modelCell);
//                double purchasePrice = getNumericCellValue(purchasePriceCell);
//                double retailPrice = getNumericCellValue(retailPriceCell);
//                int quantity = (int) getNumericCellValue(quantityCell);
//
//                Date date = null;
//                try {
//                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//                    date = dateFormat.parse(dateString);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//                System.out.println("商品编号：" + code);
//                System.out.println("商品名称：" + name);
//                System.out.println("生产厂家：" + manufacturer);
//                System.out.println("生产日期：" + formatDate(date));
//                System.out.println("型号：" + model);
//                System.out.println("进货价：" + purchasePrice);
//                System.out.println("零售价格：" + retailPrice);
//                System.out.println("数量：" + quantity);
//                System.out.println("----------------");
//            }
//
//        } catch (IOException e) {
//            System.out.println("浏览商品失败，请重试！");
//        }
//    }//浏览商品
//    private static String getCellStringValue2(Cell cell) {
//        if (cell == null) {
//            return "";
//        }
//
//        if (cell.getCellType() == CellType.STRING) {
//            return cell.getStringCellValue();
//        } else if (cell.getCellType() == CellType.NUMERIC) {
//            if (DateUtil.isCellDateFormatted(cell)) {
//                Date date = cell.getDateCellValue();
//                return formatDate(date);
//            } else {
//                DataFormatter dataFormatter = new DataFormatter();
//                return dataFormatter.formatCellValue(cell);
//            }
//        } else if (cell.getCellType() == CellType.FORMULA) {
//            if (DateUtil.isCellDateFormatted(cell)) {
//                Date date = cell.getDateCellValue();
//                return formatDate(date);
//            } else {
//                return cell.getCellFormula();
//            }
//        } else {
//            return "";
//        }
//    }
//    private static double getNumericCellValue(Cell cell) {
//        if (cell == null) {
//            return 0.0;
//        }
//
//        if (cell.getCellType() == CellType.NUMERIC) {
//            return cell.getNumericCellValue();
//        } else {
//            return 0.0;
//        }
//    }
//    private static String formatDate(Date date) {
//        if (date == null) {
//            return "";
//        }
//
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        return dateFormat.format(date);
//    }//浏览商品结束

//    private static void addProductToCart(Scanner scanner) {//添加购物车商品
//        try (FileInputStream file = new FileInputStream(FILE_PATH);
//             Workbook workbook = new XSSFWorkbook(file)) {
//
//            Sheet sheet = workbook.getSheetAt(3); // 获取第四个表格，索引从0开始
//
//            System.out.println("请输入商品名称：");
//            String productName = scanner.nextLine();
//
//            System.out.println("请输入购买数量：");
//            int quantity = Integer.parseInt(scanner.nextLine());
//
//            System.out.println("请输入购买价格：");
//            double price = Double.parseDouble(scanner.nextLine());
//
//            System.out.println("请输入用户账号：");
//            String userAccount = scanner.nextLine();
//
//            Row row = sheet.createRow(sheet.getLastRowNum() + 1); // 创建新行
//
//            int cellIndex = 0;
//            Cell cell = row.createCell(cellIndex++);
//            cell.setCellValue(productName);
//
//            cell = row.createCell(cellIndex++);
//            cell.setCellValue(quantity);
//
//            cell = row.createCell(cellIndex++);
//            cell.setCellValue(price);
//
//            cell = row.createCell(cellIndex);
//            cell.setCellValue(userAccount);
//
//            try (FileOutputStream outputFile = new FileOutputStream(FILE_PATH)) {
//                workbook.write(outputFile);
//            }
//
//            System.out.println("商品已成功添加到购物车！");
//
//        } catch (IOException e) {
//            System.out.println("添加商品到购物车失败，请重试！");
//        }
//    }//用户添加成功

//    private static int findUserRowIndex(Sheet sheet, String userAccount) {
//        int lastRow = sheet.getLastRowNum(); // 获取最后一行的索引
//
//        for (int rowIndex = 0; rowIndex <= lastRow; rowIndex++) {
//            Row row = sheet.getRow(rowIndex);
//
//            if (row != null) {
//                Cell cell = row.getCell(0); // 假设用户账号在第一列，根据实际情况调整索引
//
//                if (cell != null) {
//                    if (cell.getCellType() == CellType.STRING && cell.getStringCellValue().equals(userAccount)) {
//                        return rowIndex;
//                    } else if (cell.getCellType() == CellType.NUMERIC && String.valueOf((int) cell.getNumericCellValue()).equals(userAccount)) {
//                        return rowIndex;
//                    }
//                }
//            }
//        }
//
//        return -1; // 返回 -1 表示未找到用户账号
//    }//添加至购物车结束


//    private static void removeProductFromCart(Scanner scanner) {//移除购物车中的商品
//        System.out.println("请输入商品名称：");
//        String productName = scanner.nextLine();
//
//        try (FileInputStream file = new FileInputStream(FILE_PATH);
//             Workbook workbook = new XSSFWorkbook(file)) {
//
//            Sheet sheet = workbook.getSheetAt(3); // 获取第四张表，索引从0开始
//
//            int lastRow = sheet.getLastRowNum(); // 获取最后一行的索引
//
//            for (int rowIndex = 0; rowIndex <= lastRow; rowIndex++) {
//                Row row = sheet.getRow(rowIndex);
//
//                if (row != null) {
//                    Cell cell = row.getCell(0); // 假设商品名称在第一列，根据实际情况调整索引
//
//                    if (cell != null && cell.getStringCellValue().equals(productName)) {
//                        sheet.removeRow(row);
//                        break;
//                    }
//                }
//            }
//
//            try (FileOutputStream outputFile = new FileOutputStream(FILE_PATH)) {
//                workbook.write(outputFile);
//            }
//
//            System.out.println("商品已成功从购物车移除！");
//
//        } catch (IOException e) {
//            System.out.println("从购物车移除商品失败，请重试！");
//        }
//    }//删除购物车中的商品结束

//    private static void modifyProductQuantity(Scanner scanner) {//进入第四张表，找到对应的名称，找到数量，修改
//        System.out.println("请输入商品名称：");
//        String productName = scanner.nextLine();
//
//        System.out.println("请输入修改后的商品数量：");
//        int newQuantity = Integer.parseInt(scanner.nextLine());
//
//        try (FileInputStream file = new FileInputStream(FILE_PATH);
//             Workbook workbook = new XSSFWorkbook(file)) {
//
//            Sheet sheet = workbook.getSheetAt(3); // 获取第四张表，索引从0开始
//
//            int lastRow = sheet.getLastRowNum(); // 获取最后一行的索引
//
//            for (int rowIndex = 0; rowIndex <= lastRow; rowIndex++) {
//                Row row = sheet.getRow(rowIndex);
//
//                if (row != null) {
//                    Cell cell = row.getCell(0); // 假设商品名称在第一列，根据实际情况调整索引
//
//                    if (cell != null && cell.getStringCellValue().equals(productName)) {
//                        Cell quantityCell = row.getCell(1); // 假设商品数量在第二列，根据实际情况调整索引
//                        if (quantityCell == null) {
//                            quantityCell = row.createCell(1);
//                        }
//                        quantityCell.setCellValue(newQuantity);
//                        break;
//                    }
//                }
//            }
//
//            try (FileOutputStream outputFile = new FileOutputStream(FILE_PATH)) {
//                workbook.write(outputFile);
//            }
//
//            System.out.println("商品数量已成功修改！");
//
//        } catch (IOException e) {
//            System.out.println("修改商品数量失败，请重试！");
//        }
//    }//修改购物车商品数量

//    private static void calculateAndPay(Scanner scanner) {//购物车商品结算
//        try (
//                FileInputStream file = new FileInputStream(FILE_PATH);
//                Workbook workbook = new XSSFWorkbook(file)) {
//
//            Sheet sheet = workbook.getSheetAt(3); // 获取第四个表格，索引从0开始
//
//            System.out.println("请输入用户账号：");
//            String userAccount = scanner.nextLine();
//
//            try (FileOutputStream outputFile = new FileOutputStream(FILE_PATH)) {
//                workbook.write(outputFile);
//            }
//
//            double totalPrice = 0.0;
//            int lastRow = sheet.getLastRowNum();
//
//            for (int rowIndex = 1; rowIndex <= lastRow; rowIndex++) {
//                Row currentRow = sheet.getRow(rowIndex);
//                Cell accountCell = currentRow.getCell(3); // 第四列为用户账号
//
//                if (accountCell != null && accountCell.getCellType() == CellType.STRING) {
//                    String account = accountCell.getStringCellValue();
//
//                    if (account.equals(userAccount)) {
//                        Cell priceCell = currentRow.getCell(2); // 第三列为价格
//                        Cell quantityCell = currentRow.getCell(1); // 第二列为数量
//
//                        if (priceCell != null && priceCell.getCellType() == CellType.NUMERIC
//                                && quantityCell != null && quantityCell.getCellType() == CellType.NUMERIC) {
//                            double rowPrice = priceCell.getNumericCellValue();
//                            int rowQuantity = (int) quantityCell.getNumericCellValue();
//
//                            totalPrice += rowPrice * rowQuantity;
//                        }
//                    }
//                }
//            }
//
//            System.out.println("您的购物车总价为：" + totalPrice);
//
//        } catch (IOException e) {
//            System.out.println("添加商品到购物车失败，请重试！");
//        }
//    }//结算付款

//    private static void shoppingHistory(Scanner scanner) {//购物历史
//        System.out.println("购物历史");
//        try (FileInputStream file = new FileInputStream(FILE_PATH);
//             Workbook workbook = new XSSFWorkbook(file)) {
//
//            Sheet sheet = workbook.getSheetAt(3); // 获取第四个表格，索引从0开始
//
//            System.out.println("请输入用户账号：");
//            String userAccount = scanner.nextLine();
//
//            int lastRow = sheet.getLastRowNum();
//            boolean found = false;
//
//            for (int rowIndex = 1; rowIndex <= lastRow; rowIndex++) {
//                Row currentRow = sheet.getRow(rowIndex);
//                Cell accountCell = currentRow.getCell(3); // 第四列为用户账号
//
//                if (accountCell != null && accountCell.getCellType() == CellType.STRING) {
//                    String account = accountCell.getStringCellValue();
//
//                    if (account.equals(userAccount)) {
//                        found = true;
//
//                        Cell productNameCell = currentRow.getCell(0); // 第一列为商品名称
//                        Cell quantityCell = currentRow.getCell(1); // 第二列为数量
//                        Cell priceCell = currentRow.getCell(2); // 第三列为价格
//
//                        if (productNameCell != null && productNameCell.getCellType() == CellType.STRING
//                                && quantityCell != null && quantityCell.getCellType() == CellType.NUMERIC
//                                && priceCell != null && priceCell.getCellType() == CellType.NUMERIC) {
//                            String productName = productNameCell.getStringCellValue();
//                            int quantity = (int) quantityCell.getNumericCellValue();
//                            double price = priceCell.getNumericCellValue();
//
//                            System.out.println("商品名称: " + productName);
//                            System.out.println("数量: " + quantity);
//                            System.out.println("价格: " + price);
//                            System.out.println("--------------------------");
//                        }
//                    }
//                }
//            }
//
//            if (!found) {
//                System.out.println("未找到与账号匹配的购买记录。");
//            }
//
//        } catch (IOException e) {
//            System.out.println("读取购物车信息失败，请重试！");
//        }
//        // 查看购物历史，包括时间和购买清单
//        // ...
//    }//购物历史
}
