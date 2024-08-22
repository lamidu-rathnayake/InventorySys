package JujubesInventoryManager.Report;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class CreateVirticleTable {

    public void writeIntoTheExcel(String filePath, Object[][] data) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Sheet1");

        // Populate the sheet with data from the 2D array
        for (int i = 0; i < data.length; i++) {
            Row row = sheet.createRow(i);
            for (int j = 0; j < data[i].length; j++) {
                Cell cell = row.createCell(j);
                Object value = data[i][j];

                // Determine the type of each element and set the cell value accordingly
                if (value instanceof String) {
                    cell.setCellValue((String) value);
                } else if (value instanceof Integer) {
                    cell.setCellValue((Integer) value);
                } else if (value instanceof Double) {
                    cell.setCellValue((Double) value);
                } else if (value instanceof Boolean) {
                    cell.setCellValue((Boolean) value);
                } else {
                    cell.setCellValue(value.toString());
                }

                //header
                if(i == 0) this.setBold(cell,workbook); 
            }
        }

        // Check if the file already exists, and if so, increment the file name
        File file = new File(filePath);
        if (file.exists()) {
            filePath = this.getUniqueFilePath(filePath);
        }

        // Write the data to the Excel file
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            workbook.write(fos);
            System.out.println("Data written to " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Method to generate a unique file path if the file already exists
    protected String getUniqueFilePath(String filePath) {
        File file = new File(filePath);
        String fileName = file.getName();
        String directory = file.getParent();
        String fileExtension = "";

        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex != -1) {
            fileExtension = fileName.substring(dotIndex);
            fileName = fileName.substring(0, dotIndex);
        }

        int count = 1;
        String newFilePath = filePath;

        while (file.exists()) {
            newFilePath = directory + File.separator + fileName + "_" + count + fileExtension;
            file = new File(newFilePath);
            count++;
        }

        return newFilePath;
    }

    public void setBold(Cell cell,Workbook workbook){
        Font boldFont = workbook.createFont();
        boldFont.setBold(true);
        CellStyle boldStyle = workbook.createCellStyle();
        boldStyle.setFont(boldFont); 
        cell.setCellStyle(boldStyle);
    }

    public static void main(String[] args) {
        Object[][] data = {
            {"Name", "Age", "Salary"},
            {"chamidu rathnayake", 28, 50000.0},
            {"Jane Smith", 34, 60000.0},
            {"Emily Johnson", 22, 45000.0}
        };
        
        String filePath = "resources\\reports\\analysisDoc.xlsx";
        CreateVirticleTable tableCreater = new CreateVirticleTable();
        tableCreater.writeIntoTheExcel(filePath, data);

    }
}
