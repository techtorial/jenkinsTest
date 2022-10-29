package api.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {

    /*
    open file
    get data
    set data
    get row data
    get all data
     */
    private static Workbook workbook;
    private static Sheet sheet;
    private static Row row;
    private static Cell cell;
    private static String path;

    public static Sheet openExcelFile(String fileName, String sheetName){
        path="src/test/java/resources/"+fileName+".xlsx";
        File file=new File(path);

        try {
            FileInputStream inputStream=new FileInputStream(file);
            workbook=new XSSFWorkbook(inputStream);
            sheet=workbook.getSheet(sheetName);
        } catch (FileNotFoundException e) {
            System.out.println(fileName+" not found in given file location");
            System.out.println(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sheet;
    }

    public static String getData(int rowNum, int cellNum){
        return sheet.getRow(rowNum).getCell(cellNum).toString();
    }

    public static void setData(int rowNum, int cellNum, String data){
        row=sheet.getRow(rowNum);
        if(row==null){
            row=sheet.createRow(rowNum);
        }
        cell=row.getCell(cellNum);
        if(cell==null){
            cell=row.createCell(cellNum, CellType.STRING);
        }
        cell.setCellValue(data);

        FileOutputStream outputStream;
        try {
             outputStream=new FileOutputStream(path);
            workbook.write(outputStream);
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Path: "+path);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*
    It will return the all cell values from given row number
    @param rowNumber
    @return all cell values
     */
    public static List<String> getRowData(int rowNum){
        List<String> cells=new ArrayList<>();
        row=sheet.getRow(rowNum);
        int firstCell=row.getFirstCellNum();
        int lastCell=row.getLastCellNum();
        for(int i=firstCell;i<lastCell;i++){
            if(row.getCell(i)!=null){
                cells.add(row.getCell(i).toString());
            }
        }
        return cells;
    }

    /*
    It will return all the data from Excel sheet
     */
    public static List<List<String>> getAllData(){
        List<List<String>> allData=new ArrayList<>();
        int firstRow=sheet.getFirstRowNum();
        int lastRow=sheet.getLastRowNum();
        for(int i=firstRow;i<=lastRow;i++){
            allData.add(getRowData(i));
        }
        return allData;
    }
}
