package com.orkva.utils.easy.excel.parser;

import com.orkva.utils.easy.excel.parser.impl.BasicClassParser;
import com.orkva.utils.easy.excel.parser.impl.OffsetDateTimeExcelClassParser;

import java.time.OffsetDateTime;

/**
 * ExcelInstanceParserFactory
 *
 * @author Shepherd Xie
 * @version 2022/11/17
 */
public final class ExcelClassParserFactory {

    static {
        ExcelClassParserRegister.register(Byte.class, BasicClassParser.BYTE_EXCEL_CLASS_PARSER);
        ExcelClassParserRegister.register(Short.class, BasicClassParser.SHORT_EXCEL_CLASS_PARSER);
        ExcelClassParserRegister.register(Integer.class, BasicClassParser.INTEGER_EXCEL_CLASS_PARSER);
        ExcelClassParserRegister.register(Long.class, BasicClassParser.LONG_EXCEL_CLASS_PARSER);
        ExcelClassParserRegister.register(Float.class, BasicClassParser.FLOAT_EXCEL_CLASS_PARSER);
        ExcelClassParserRegister.register(Double.class, BasicClassParser.DOUBLE_EXCEL_CLASS_PARSER);
        ExcelClassParserRegister.register(String.class, BasicClassParser.STRING_EXCEL_CLASS_PARSER);
        ExcelClassParserRegister.register(OffsetDateTime.class, new OffsetDateTimeExcelClassParser());
    }

    public static <T> ExcelClassParser<T> getParser(Class<T> clazz) {
        if (clazz == null) {
            throw new RuntimeException("Parameter class cannot be null.");
        }
        return ExcelClassParserRegister.loadParser(clazz);
    }

}
