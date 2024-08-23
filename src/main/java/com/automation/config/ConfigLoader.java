package com.automation.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigLoader {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static List<Map<String, String>> readExcel(String filePath, String sheetName) throws IOException {
        List<Map<String, String>> data = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new HSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            Row headerRow = sheet.getRow(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                Map<String, String> rowData = new HashMap<>();

                for (int j = 0; j < headerRow.getLastCellNum(); j++) {
                    String header = headerRow.getCell(j).getStringCellValue();
                    String value = getCellValueAsString(row.getCell(j));
                    rowData.put(header, value);
                }

                data.add(rowData);
            }
        }

        return data;
    }

    public static List<Map<String, Object>> readJson(String filePath) throws IOException {
        return objectMapper.readValue(new FileInputStream(filePath),
                objectMapper.getTypeFactory().constructCollectionType(List.class, Map.class));
    }

    private static String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    return String.valueOf(cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }
}