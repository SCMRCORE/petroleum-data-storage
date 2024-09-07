package com.orkva.utils.easy.excel.writer;

import com.orkva.utils.easy.excel.ExcelType;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * ExcelWriterBuilder
 *
 * @author Shepherd Xie
 * @version 2022/11/22
 */
public final class ExcelWorkbookBuilder {
    private static final int POI_SXSS_TRANSFER_COLUMN = 2048;
    private final ExcelType excelType;
    private Workbook workbook;
    private final List<ExcelSheetBuilder> excelSheetBuilders;

    public ExcelWorkbookBuilder(ExcelType excelType) {
        this.excelType = excelType;
        excelSheetBuilders = new ArrayList<>();
    }

    public ExcelWorkbookBuilder addSheet(Collection<Object> instances) {
        return addSheet(instances, null,null);
    }

    public ExcelWorkbookBuilder addSheet(Collection<Object> instances, Class<?> type, String title) {
        if (instances == null || instances.isEmpty()) {
            throw new RuntimeException("Target instances cannot be blank.");
        }

        ExcelSheetBuilder excelSheetBuilder = new ExcelSheetBuilder(instances, type, title);
        excelSheetBuilders.add(excelSheetBuilder);
        return this;
    }

    public Workbook build() {
        return assembly().workbook;
    }

    private void createWorkbook(int maxRecordSize) {
        if (maxRecordSize >= POI_SXSS_TRANSFER_COLUMN) {
            workbook = new SXSSFWorkbook();
        } else {
            if (excelType == ExcelType.XLSX) {
                workbook = new XSSFWorkbook();
            } else if (excelType == ExcelType.XLS) {
                workbook = new HSSFWorkbook();
            }
        }
    }

    private ExcelWorkbookBuilder assembly() {
        if (excelSheetBuilders.isEmpty()) {
            throw new NullPointerException("No data to export.");
        }

        Integer maxRecordSize = excelSheetBuilders.stream()
                .map(ExcelSheetBuilder::getInstances)
                .map(Collection::size)
                .max(Integer::compareTo)
                .get();
        createWorkbook(maxRecordSize);

        excelSheetBuilders.forEach(excelSheetBuilder -> excelSheetBuilder.buildSheet(workbook));
        return this;
    }

}
