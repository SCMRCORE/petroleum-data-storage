package com.orkva.utils.easy.excel.parser;

/**
 * ExcelInstanceParser
 *
 * @author Shepherd Xie
 * @version 2022/11/17
 */
public interface ExcelClassParser<T> {

    T parse(String value);

}
