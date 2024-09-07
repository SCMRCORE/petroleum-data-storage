package com.orkva.utils.easy.excel.parser;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ExcelClassParserRegister
 *
 * @author Shepherd Xie
 * @version 2022/11/17
 */
public class ExcelClassParserRegister {

    private static final Map<Class<?>, ExcelClassParser<?>> EXCEL_CLASS_PARSER_REGISTER_POOL = new ConcurrentHashMap<>();

    public static <T> void register(Class<T> clazz, ExcelClassParser<T> excelClassParser) {
        if (clazz == null || excelClassParser == null) {
            throw new RuntimeException("Class or excelClassParser cannot be null.");
        }
        EXCEL_CLASS_PARSER_REGISTER_POOL.put(clazz, excelClassParser);
    }

    public static <T> ExcelClassParser<T> loadParser(Class<T> clazz) {
        return (ExcelClassParser<T>) EXCEL_CLASS_PARSER_REGISTER_POOL.get(clazz);
    }

}
