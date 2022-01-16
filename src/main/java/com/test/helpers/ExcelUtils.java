package com.test.helpers;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
    private static ExcelUtils instance;
    private static XSSFSheet ExcelWSheet;
    private static XSSFWorkbook ExcelWBook;
    private static XSSFCell Cell;
    private static XSSFRow Row;
    // Get sheet row num and column num
    private ExcelUtils(){

    }

    public static ExcelUtils getInstance(){
        if(instance==null) {
            synchronized (ExcelUtils.class) {
                if (instance == null) {
                    instance = new ExcelUtils();
                }
            }
        }
        return instance;
    }
    public int getSheetRowNum(String fileName,String sheetName) throws IOException{

        FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"/testData/"+fileName);
        ExcelWBook = new XSSFWorkbook(fs);
        ExcelWSheet = ExcelWBook.getSheet(sheetName);
        return ExcelWSheet.getPhysicalNumberOfRows();
    }

    public int getSheetColNum(String fileName,String sheetName) throws IOException{

        FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"/testData/"+fileName);
        ExcelWBook = new XSSFWorkbook(fs);
        ExcelWSheet = ExcelWBook.getSheet(sheetName);
        return ExcelWSheet.getRow(0).getPhysicalNumberOfCells();
    }
    // Get the sheet data in 2 dimensional array from a sheet
    // Pass file path as argument and sheet Name from which data need to be retrieved.
    public String[][] getSheetData(String fileName,String sheetName) throws IOException{

        FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"/testData/"+fileName);
        ExcelWBook = new XSSFWorkbook(fs);
        ExcelWSheet = ExcelWBook.getSheet(sheetName);
        DataFormatter df = new DataFormatter();
        int rownum = ExcelWSheet.getPhysicalNumberOfRows();
        int colnum = ExcelWSheet.getRow(0).getPhysicalNumberOfCells();
        String[][] sheetDataObject = new String[rownum][colnum];
        for(int i=0 ;i<rownum;i++){
            for (int j=0;j<colnum;j++){

                System.out.println("row: "+rownum + "col: "+colnum);
                //GET CELL
                XSSFCell cell1 = ExcelWSheet.getRow(i).getCell(j);
                //String value = df.formatCellValue(cell1);
                sheetDataObject[i][j]= df.formatCellValue(cell1);
                System.out.println("Values: rownum: "+rownum + " col: "+colnum + " value: "+sheetDataObject[i][j]);
            }
        }
        return sheetDataObject;
    }


    public List<Object> getSheetDataAsList(String fileName,String sheetName){
        try {
            FileInputStream fs = new FileInputStream(System.getProperty("user.dir") + "/testData/" + fileName);
            ExcelWBook = new XSSFWorkbook(fs);
            ExcelWSheet = ExcelWBook.getSheet(sheetName);
            DataFormatter df = new DataFormatter();
            int rownum = ExcelWSheet.getPhysicalNumberOfRows();
            int colnum = ExcelWSheet.getRow(0).getPhysicalNumberOfCells();
            List<Object> testData = new ArrayList<>();
            String[][] sheetDataObject = new String[rownum][colnum];
            for (int i = 0; i < rownum; i++) {
                for (int j = 0; j < colnum; j++) {

                    System.out.println("row: " + rownum + "col: " + colnum);
                    //GET CELL
                    XSSFCell cell1 = ExcelWSheet.getRow(i).getCell(j);
                    //String value = df.formatCellValue(cell1);
                    sheetDataObject[i][j] = df.formatCellValue(cell1);
                    //testData.add(df.formatCellValue(cell1).replaceAll("\n","").replaceAll(" ",""));
                    testData.add(df.formatCellValue(cell1));
                    System.out.println("Values: rownum: " + rownum + " col: " + colnum + " value: " + sheetDataObject[i][j]);
                }
            }
            return testData;
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
