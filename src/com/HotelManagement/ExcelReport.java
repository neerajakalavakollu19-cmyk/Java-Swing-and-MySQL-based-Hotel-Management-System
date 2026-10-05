package com.HotelManagement;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.dynamicconn.Conn1;
import com.sun.rowset.JdbcRowSetImpl;

import javax.sql.rowset.JdbcRowSet;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelReport {

    public static void generateReport(JFrame parent) {

        String propertiesFile =
                "C:\\eclipse_workspace\\com.dynamicconn\\src\\mysql.properties";

        String outputFile =
                "C:\\eclipse_workspace\\com.HotelManagement\\Hotel_Data.xls";

        try {

            FileInputStream fis =
                    new FileInputStream(propertiesFile);

            java.util.Properties properties =
                    new java.util.Properties();

            properties.load(fis);

            fis.close();

            String driver =
                    properties.getProperty("key1");

            String url =
                    properties.getProperty("key2");

            String username =
                    properties.getProperty("key3");

            String password =
                    properties.getProperty("key4");

            JdbcRowSet rowSet =
                    new JdbcRowSetImpl();

            rowSet.setUrl(url);

            rowSet.setUsername(username);

            rowSet.setPassword(password);

            rowSet.setCommand(
                    "SELECT * FROM customer"
            );

            rowSet.setType(
                    ResultSet.TYPE_SCROLL_INSENSITIVE
            );

            rowSet.setConcurrency(
                    ResultSet.CONCUR_READ_ONLY
            );

            Class.forName(driver);

            rowSet.execute();

            HSSFWorkbook workbook =
                    new HSSFWorkbook();

            HSSFSheet sheet =
                    workbook.createSheet(
                            "Customer Data"
                    );

            HSSFRow header =
                    sheet.createRow(0);

            header.createCell(0)
                    .setCellValue("Customer ID");

            header.createCell(1)
                    .setCellValue("Name");

            header.createCell(2)
                    .setCellValue("Phone");

            header.createCell(3)
                    .setCellValue("Email");

            header.createCell(4)
                    .setCellValue("Address");

            int rowNumber = 1;

            while (rowSet.next()) {

                HSSFRow row =
                        sheet.createRow(rowNumber);

                row.createCell(0)
                        .setCellValue(
                                rowSet.getInt("customer_id")
                        );

                row.createCell(1)
                        .setCellValue(
                                rowSet.getString("name")
                        );

                row.createCell(2)
                        .setCellValue(
                                rowSet.getString("phone")
                        );

                row.createCell(3)
                        .setCellValue(
                                rowSet.getString("email")
                        );

                row.createCell(4)
                        .setCellValue(
                                rowSet.getString("address")
                        );

                rowNumber++;
            }

            for (int i = 0; i < 5; i++) {

                sheet.autoSizeColumn(i);
            }

            FileOutputStream fos =
                    new FileOutputStream(outputFile);

            workbook.write(fos);

            fos.close();

            workbook.close();

            rowSet.close();

            JOptionPane.showMessageDialog(
                    parent,
                    "Excel report created successfully!\n\n"
                    + "Location:\n"
                    + outputFile,
                    "Excel Report",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    parent,
                    "Excel report could not be created.\n\n"
                    + e.getMessage(),
                    "Excel Report Error",
                    JOptionPane.ERROR_MESSAGE
            );

            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        generateReport(null);
    }
}