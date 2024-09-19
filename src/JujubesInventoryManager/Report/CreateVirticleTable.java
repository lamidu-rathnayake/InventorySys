package JujubesInventoryManager.Report;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class CreateVirticleTable {

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
                    if (value == null) {
                        cell.setCellValue("No record");
                    } else if (value instanceof String) {
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

    // Method to generate a unique file path if the file already exists
    protected String getUniqueFilePath(String filePath) {
        try{
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
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
            return null;
        }
            
    }

    public void setBold(Cell cell,Workbook workbook){
        Font boldFont = workbook.createFont();
        boldFont.setBold(true);
        CellStyle boldStyle = workbook.createCellStyle();
        boldStyle.setFont(boldFont); 
        cell.setCellStyle(boldStyle);
    }

    public static void main(String[] args) {
        
        
        // String filePath = "resources\\reports\\analysisDoc.xlsx";
        // CreateVirticleTable tableCreater = new CreateVirticleTable();
        // tableCreater.writeIntoTheExcel(filePath, data);

    }
}
