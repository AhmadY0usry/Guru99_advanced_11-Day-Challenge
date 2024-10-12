import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

public class SaveInExcel {

    public void saveSearchResultInExcel(String userEmail,String UserPass, String OrderID, String filePath) {
        // Create a new workbook and sheet for the Excel file
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("User Data");

        // Add header row (optional)
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("User Email");
        headerRow.createCell(1).setCellValue("Password");
        headerRow.createCell(2).setCellValue("Order Id");
        // Loop to get product names and prices and write them to the Excel sheet
        for (int i = 0; i < 1; i++) {
            // Create a new row for each product
            Row row = sheet.createRow(i + 1);  // Start at row 1, as row 0 is the header
            row.createCell(0).setCellValue(userEmail);  // Add user email
            row.createCell(1).setCellValue(UserPass);    // Add User password
            row.createCell(2).setCellValue(OrderID); // Add Order ID
        }
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);  // Write before closing
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                workbook.close();  // Ensure workbook is closed
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
