package systemTesting.basePackage;

import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.test.helpers.ExcelUtils;
import com.test.helpers.api.JacksonJsonImpl;
import com.test.helpers.report.ReportHelper;

import java.io.IOException;
import java.util.List;

public class BaseSetup extends ReportHelper {


    public List<Object> getTestDataFromSheet(String workBook, String sheetName){

        List<Object> excelData=ExcelUtils.getInstance().getSheetDataAsList(workBook,sheetName);
        return excelData;
    }

    //Get Test Data
    public <T> List<T> getTestData(String workBook, String sheetName, Class<T> clas) {
        List<Object> excelData = getTestDataFromSheet(workBook, sheetName);
        try {
            CollectionType typeReference =
                    TypeFactory.defaultInstance().constructCollectionType(List.class, clas);
            List<T> li= JacksonJsonImpl.getInstance().fromJsonArray(excelData.toString(),typeReference);
            return li;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
