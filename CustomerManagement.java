package shop;

import java.util.Scanner;

public class CustomerManagement {
    private static final String FILE_PATH = "D://xiazai/test.xlsx";
    public void customerM(Scanner scanner) {
        System.out.println("客户管理");
        System.out.println("请选择操作：");
        System.out.println("1. 列出所有用户信息");  //进入excel表中的表一，遍历出所有用户信息
        System.out.println("2. 删除用户信息"); //根据ID对表一中的相应数据进行删除
        System.out.println("3. 查询用户信息");//根据ID或者用户名对表一中的相应数据进行显示

        int choice = scanner.nextInt();
        scanner.nextLine(); // 消费掉输入缓冲区中的换行符

        switch (choice) {
            case 1:
                // 在这里完成列出所有用户信息的操作，利用test.xlsx文件进行遍历操作，输出用户信息
                System.out.println("以下是所有用户信息：");
                DisplayAllUsers displayAllUsers = new DisplayAllUsers();
                displayAllUsers.display();
                break;
            case 2://删除用户
                System.out.print("请输入要删除用户信息的ID：");
                DeleteUserInfo deleteUserInfo = new DeleteUserInfo();
                int userId = Integer.parseInt(scanner.nextLine());
                boolean isUserDeleted = deleteUserInfo.delete(userId);

                if (isUserDeleted) {
                    System.out.println("指定用户信息已成功删除！");
                } else {
                    System.out.println("删除用户信息失败，请检查ID是否正确！");
                }
                break;
            case 3://查询用户
                System.out.print("请输入要查询用户信息的ID：");
                DisplayUserInfo displayUserInfo= new DisplayUserInfo();
                int userId1 = Integer.parseInt(scanner.nextLine());
                displayUserInfo.display(userId1);
                break;
            default:
                System.out.println("无效选择，请重新输入！");
        }
    }//管理员 用户管理
//    private static void displayAllUsers() {//遍历第一张表，显示所有用户信息
//        try {
//            FileInputStream file = new FileInputStream(FILE_PATH);
//            Workbook workbook = new XSSFWorkbook(file);
//            Sheet sheet = workbook.getSheetAt(0); // 第一张表
//
//            for (Row row : sheet) {
//                for (Cell cell : row) {
//                    // 读取每个单元格的值并输出
//                    String cellValue = "";
//                    switch (cell.getCellType()) {
//                        case STRING:
//                            cellValue = cell.getStringCellValue();
//                            break;
//                        case NUMERIC:
//                            if (DateUtil.isCellDateFormatted(cell)) {
//                                Date date = cell.getDateCellValue();
//                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//                                cellValue = dateFormat.format(date);
//                            } else {
//                                double numericValue = cell.getNumericCellValue();
//                                long phoneNumber = (long) numericValue;
//                                cellValue = String.valueOf(phoneNumber);
//                            }
//                            break;
//                        case BOOLEAN:
//                            cellValue = String.valueOf(cell.getBooleanCellValue());
//                            break;
//                        case FORMULA:
//                            cellValue = cell.getCellFormula();
//                            break;
//                        default:
//                            cellValue = "";
//                    }
//                    System.out.print(cellValue + "\t");
//                }
//                System.out.println(); // 换行
//            }
//
//            file.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }//显示用户信息结束   还有点问题，输出的格式不太对

//    private static boolean deleteUserInfo(int userId) {//根据ID删除用户，打开excel扎到第一张表，找到相应ID，进行删除
//        try {
//            FileInputStream file = new FileInputStream(FILE_PATH);
//            Workbook workbook = new XSSFWorkbook(file);
//            Sheet sheet = workbook.getSheetAt(0); // 第一张表
//            boolean isUserFound = false;
//
//            for (Row row : sheet) {
//                if (row.getRowNum() == 0) { // 跳过标题行
//                    continue;
//                }
//
//                Cell cell = row.getCell(0); // ID在第一列
//
//                if (cell != null && cell.getCellType() == CellType.NUMERIC && cell.getNumericCellValue() == userId) {
//                    sheet.removeRow(row);
//                    isUserFound = true;
//                    break;
//                }
//            }
//
//            file.close();
//
//            if (isUserFound) {
//                FileOutputStream outFile = new FileOutputStream(FILE_PATH);
//                workbook.write(outFile);
//                outFile.close();
//            }
//
//            return isUserFound;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }//根据ID删除用户结束

//    private static void displayUserInfo(int userId) {//根据用户ID查询用户
//        try {
//            FileInputStream file = new FileInputStream(FILE_PATH);
//            Workbook workbook = new XSSFWorkbook(file);
//            Sheet sheet = workbook.getSheetAt(0); // 第一张表
//            boolean isUserFound = false;
//
//            for (Row row : sheet) {
//                if (row.getRowNum() == 0) { // 跳过标题行
//                    continue;
//                }
//
//                Cell cell = row.getCell(0); // ID在第一列
//
//                if (cell != null && cell.getCellType() == CellType.NUMERIC && cell.getNumericCellValue() == userId) {
//                    isUserFound = true;
//
//                    for (Cell dataCell : row) {
//                        // 读取每个单元格的值并输出
//                        String cellValue = "";
//
//                        switch (dataCell.getCellType()) {
//                            case STRING:
//                                cellValue = dataCell.getStringCellValue();
//                                break;
//                            case NUMERIC:
//                                cellValue = String.valueOf(dataCell.getNumericCellValue());
//                                break;
//                            case BOOLEAN:
//                                cellValue = String.valueOf(dataCell.getBooleanCellValue());
//                                break;
//                            case FORMULA:
//                                cellValue = dataCell.getCellFormula();
//                                break;
//                            default:
//                                cellValue = "";
//                        }
//
//                        System.out.print(cellValue + "\t");
//                    }
//
//                    System.out.println(); // 换行
//                    break;
//                }
//            }
//
//            file.close();
//
//            if (!isUserFound) {
//                System.out.println("未找到指定的用户信息！");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }//根据ID查询用户结束
}
