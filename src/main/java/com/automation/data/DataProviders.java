package com.automation.data;

import com.automation.config.ConfigLoader;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class DataProviders {

    @DataProvider(name = "excelData")
    public Object[][] excelDataProvider() throws IOException {
        // Replace with your Excel file path and sheet name
        List<Map<String, String>> data = ConfigLoader.readExcel("path/to/excel/file.xls", "Sheet1");

        // Convert List<Map<String, String>> to Object[][]
        return data.stream()
                .map(map -> map.values().toArray())
                .toArray(Object[][]::new);
    }

    @DataProvider(name = "jsonData")
    public Object[][] jsonDataProvider() throws IOException {
        // Replace with your JSON file path
        List<Map<String, Object>> data = ConfigLoader.readJson("path/to/json/file.json");

        // Convert List<Map<String, Object>> to Object[][]
        return data.stream()
                .map(map -> map.values().toArray())
                .toArray(Object[][]::new);
    }
}
