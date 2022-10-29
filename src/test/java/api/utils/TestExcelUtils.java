package api.utils;

import org.junit.Test;

public class TestExcelUtils {

    @Test
    public void test(){
        ExcelUtils.openExcelFile("ExcelTest","tests");
        String str=ExcelUtils.getData(1,1);
        System.out.println(str);
        System.out.println(ExcelUtils.getAllData());
        System.out.println(ExcelUtils.getRowData(0));
    }
}
