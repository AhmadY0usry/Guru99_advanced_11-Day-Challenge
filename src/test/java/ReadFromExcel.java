import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadFromExcel {

    private FileInputStream getFileInputStream(String path) throws IOException {
        return new FileInputStream(path);
    }
    public List<String[]> getUserDataFromExcel(String excelFilePath, String sheetName) throws IOException {
        try (FileInputStream fis = getFileInputStream(excelFilePath);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            XSSFSheet sheet = workbook.getSheet(sheetName);
            List<String[]> excelDataList = new ArrayList<>();

            // Start iterating from the second row (index 1)
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) {
                    continue;
                }

                String[] rowData = new String[row.getLastCellNum()];
                boolean isRowEmpty = true;

                for (int j = 0; j < row.getLastCellNum(); j++) {
                    Cell cell = row.getCell(j);
                    rowData[j] = (cell == null) ? "" : cell.toString();
                    if (cell != null && !cell.toString().isEmpty()) {
                        isRowEmpty = false;
                    }
                }

                if (!isRowEmpty) {
                    excelDataList.add(rowData);
                }
            }

            return excelDataList;
        }
    }
}
