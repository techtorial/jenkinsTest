package api.stepdefs;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;

public class ExcelPractice {


    @Test
    public void test() throws IOException {
        String filePath="src/test/java/resources/ExcelTest.xlsx";
        // converts excel file into the java object.
        File file=new File(filePath);
        // FileInputStream will open the file that we provided.
        FileInputStream inputStream=new FileInputStream(file);
        // It will save excel data into the WorkBook object.
        Workbook book=new XSSFWorkbook(inputStream);
        //It will store specific sheet that we provided.
        Sheet sheet=book.getSheet("tests");
        // It will keep the row data
        Row row=sheet.getRow(0);
        // It will store the given cell data into this object.
        Cell cell=row.getCell(1);

        System.out.println(cell.toString());

        cell.setCellValue(cell+"newtestdata");

        FileOutputStream outputStream=new FileOutputStream(file);
        book.write(outputStream);

    }

    @Test
    public void test1() throws IOException {
        String filePath="src/test/java/resources/ExcelTest.xlsx";
        File file =new File(filePath);
        FileInputStream inputStream=new FileInputStream(file);

        Workbook book=new XSSFWorkbook(inputStream);
        Sheet sheet=book.getSheet("years");
        Row row=sheet.getRow(1);
        Cell cell=row.getCell(0);
        double year=Double.parseDouble(cell.toString());
        double expected=1994.0;
        double DELTA = 1e-15;
        Assert.assertEquals(expected,year,DELTA);

    }

    @Test
    public void test2() throws IOException {
        String filePath="src/test/java/resources/ExcelTest.xlsx";
        File file =new File(filePath);
        FileInputStream inputStream=new FileInputStream(file);

        Workbook book=new XSSFWorkbook(inputStream);
        Sheet sheet=book.getSheet("years");
        int firstRow=sheet.getFirstRowNum();
        int lastRow=sheet.getLastRowNum();

        for(int i=firstRow;i<=lastRow;i++){
            Row row=sheet.getRow(i);
            int firstCell=row.getFirstCellNum();
            int lastCell=row.getLastCellNum();
            for(int k=firstCell;k<lastCell;k++){
                if(row.getCell(k)==null){
                    continue;
                }
                System.out.println(row.getCell(k));
            }
        }

    }


    @Test
    public void test3() throws IOException {
        String filePath="src/test/java/resources/ExcelTest.xlsx";
        File file =new File(filePath);
        FileInputStream inputStream=new FileInputStream(file);
        // when we navigate to the row which has no any data, row object will not be instantiated.
        // That's why it will throw nullpointerexception when we use the row object.
        // To store the data into the row which has no data, we should create row and cell first then store the data.
        Workbook book=new XSSFWorkbook(inputStream);
        Sheet sheet=book.getSheet("years");
        Row row=sheet.createRow(3);
        Cell cell=row.createCell(1);
        cell.setCellValue("2000");

        FileOutputStream outputStream=new FileOutputStream(file);
        book.write(outputStream);
    }

}
