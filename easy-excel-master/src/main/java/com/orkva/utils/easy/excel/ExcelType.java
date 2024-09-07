package com.orkva.utils.easy.excel;

/**
 * ExcelType
 *
 * @author Shepherd Xie
 * @version 2022/11/20
 */
public enum ExcelType {
    /**
     * xlsx
     */
    XLSX("xlsx"),
    /**
     * xls
     */
    XLS("xls");

    private final String extension;

    ExcelType(String extension) {
        this.extension = extension;
    }
}
