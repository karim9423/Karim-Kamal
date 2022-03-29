package utilities;

import io.ExcelParser;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TestDataCollector {
    private HashMap<String, String> testData = new HashMap<>();
    private static TestDataCollector testDataCollector = null;

    public static TestDataCollector getinstance() {
	if (testDataCollector == null)
	    testDataCollector = new TestDataCollector(Constants.TESTDATA_DIR, Constants.TESTDATA_SHEETNAME);
	return testDataCollector;

    }

    public TestDataCollector(String filePath, String sheetName) {
	try {
	    ExcelParser.setExcelFile(filePath, sheetName);
	    collectData();
	} catch (IOException e) {
	    e.printStackTrace();
	}

    }

    public void collectData() {
	for (int i = 0; i <= ExcelParser.getRowCountInSheet(); i++) {
	    testData.put(ExcelParser.getCellData(i, 0), ExcelParser.getCellData(i, 1));
	}
    }

    public Map<String, String> getTestData() {
	return testData;
    }
}
