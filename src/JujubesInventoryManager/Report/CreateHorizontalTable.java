package JujubesInventoryManager.Report;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CreateHorizontalTable extends CreateVirticleTable{

    @Override
    public void writeIntoTheExcel(String filePath, List<Object[]> rows) {
        try{
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Sheet1");

            // Populate the sheet with data from the 2D array
            int i = 0;
            for (Object[] currentRow : rows) {
                Row row = sheet.createRow(i);
                int j = 0;
                for (Object item : currentRow) {
                    Cell cell = row.createCell(j);
                    Object value = item;

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
                    if(j == 0) this.setBold(cell,workbook);

                    j++;
                }
                i++;
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
        catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
        
    }

    public static void main(String[] args) {
         
                
        // String filePath = "resources\\reports\\analysisDoc.xlsx";
        // CreateVirticleTable tableCreater = new CreateHorizontalTable();
        // tableCreater.writeIntoTheExcel(filePath, data);

    }
}
