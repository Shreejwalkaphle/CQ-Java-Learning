package com.example.Integration2.Controllers;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FolderNamesToExcel {
    public static void main(String[] args) {
        // Path to the "documents" folder
        String folderPath = "C:\\Users\\ShreejwalKaphle\\OneDrive - compliancequest.com\\Documents\\sqx office\\sqx\\src\\objects";

        // Create a new Excel workbook
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Folder Names");

            // Get a list of folders
            File folder = new File(folderPath);
            File[] listOfFolders = folder.listFiles(File::isDirectory);

            if (listOfFolders != null) {
                int rowNum = 0;
                for (File subFolder : listOfFolders) {
                    Row row = sheet.createRow(rowNum++);
                    Cell cell = row.createCell(0);
                    cell.setCellValue(subFolder.getName());
                }

                // Write the workbook content to a file
                try (FileOutputStream outputStream = new FileOutputStream("folder_names.xlsx")) {
                    workbook.write(outputStream);
                }

                System.out.println("Folder names written to Excel file successfully.");
            } else {
                System.out.println("No folders found in the specified directory.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
