package com.orkva.utils.easy.excel.parser.impl;

import com.orkva.utils.easy.excel.parser.ExcelClassParser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * OffsetDateTimeParser
 *
 * @author Shepherd Xie
 * @version 2022/11/21
 */
public class OffsetDateTimeExcelClassParser implements ExcelClassParser<OffsetDateTime> {

    @Override
    public OffsetDateTime parse(String value) {
        return LocalDate.parse(value, DateTimeFormatter.ofPattern("y/M/d").withZone(ZoneId.of("Asia/Shanghai")))
                .atTime(LocalTime.MIDNIGHT)
                .atZone(ZoneId.of("Asia/Shanghai")).toOffsetDateTime();
    }

}
