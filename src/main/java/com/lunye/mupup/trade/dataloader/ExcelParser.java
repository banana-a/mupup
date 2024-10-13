package com.lunye.mupup.trade.dataloader;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelParser {

    public static List<StockData> parseExcel(String filePath) {
        List<StockData> stockDataList = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            // 跳过标题行
            if (rowIterator.hasNext()) {
                rowIterator.next();
            }

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                StockData stockData = new StockData();

                stockData.setStockName(row.getCell(0).getStringCellValue());
                stockData.setStockCode(row.getCell(1).getStringCellValue());
                stockData.setDate(row.getCell(2).getStringCellValue());
                stockData.setOpen(row.getCell(3).getNumericCellValue());
                stockData.setClose(row.getCell(4).getNumericCellValue());
                stockData.setHigh(row.getCell(5).getNumericCellValue());
                stockData.setLow(row.getCell(6).getNumericCellValue());
                stockData.setVolume((long) row.getCell(7).getNumericCellValue());
                stockData.setTurnover(row.getCell(8).getNumericCellValue());
                stockData.setAmplitude(row.getCell(9).getNumericCellValue());
                stockData.setChgPct(row.getCell(10).getNumericCellValue());
                stockData.setChgAmt(row.getCell(11).getNumericCellValue());
                stockData.setTurnoverRate(row.getCell(12).getNumericCellValue());

                stockDataList.add(stockData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stockDataList;
    }
}
