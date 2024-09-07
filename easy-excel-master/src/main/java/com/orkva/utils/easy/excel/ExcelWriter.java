package com.orkva.utils.easy.excel;

import com.orkva.utils.easy.excel.writer.ExcelWorkbookBuilder;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;

/**
 * ExcelWriter
 *
 * @author Shepherd Xie
 * @version 2022/11/15
 */
public class ExcelWriter {

    /**
     * write excel file to path
     *
     * @param outputStream outputStream
     */
    public static void write(Collection instances, OutputStream outputStream) {
        try (Workbook workbook = builder().addSheet(instances).build()) {
            workbook.write(outputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static ExcelWorkbookBuilder builder() {
        return builder(ExcelType.XLSX);
    }

    public static ExcelWorkbookBuilder builder(ExcelType excelType) {
        return new ExcelWorkbookBuilder(excelType);
    }

}
