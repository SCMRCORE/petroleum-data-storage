package com.orkva.utils.easy.excel.parser.impl;

import com.orkva.utils.easy.excel.parser.ExcelClassParser;

/**
 * BasicClassParser
 *
 * @author Shepherd Xie
 * @version 2022/11/17
 */
public class BasicClassParser {

    public static final ExcelClassParser<Byte> BYTE_EXCEL_CLASS_PARSER = Byte::valueOf;
    public static final ExcelClassParser<Short> SHORT_EXCEL_CLASS_PARSER = Short::valueOf;
    public static final ExcelClassParser<Integer> INTEGER_EXCEL_CLASS_PARSER = Integer::valueOf;
    public static final ExcelClassParser<Long> LONG_EXCEL_CLASS_PARSER = Long::valueOf;
    public static final ExcelClassParser<Float> FLOAT_EXCEL_CLASS_PARSER = Float::valueOf;
    public static final ExcelClassParser<Double> DOUBLE_EXCEL_CLASS_PARSER = Double::valueOf;
    public static final ExcelClassParser<String> STRING_EXCEL_CLASS_PARSER = String::toString;

}
