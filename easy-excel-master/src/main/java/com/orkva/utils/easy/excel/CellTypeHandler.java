package com.orkva.utils.easy.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * CellTypeHandler
 *
 * @author Shepherd Xie
 * @version 2022/11/20
 */
public class CellTypeHandler {

    private static final Map<Class<?>, CellType> CLASS_CELL_TYPE_MAP;
    private static final Map<Class<?>, WriterCellBuilder<Object>> CLASS_CELL_BUILDER_MAP;

    static {
        CLASS_CELL_TYPE_MAP = initClassCellTypeMap();
        CLASS_CELL_BUILDER_MAP = initClassCellBuilderMap();
    }

    public static CellType getCellType(Class<?> clazz) {
        return CLASS_CELL_TYPE_MAP.getOrDefault(clazz, CellType.BLANK);
    }

    public static WriterCellBuilder<Object> getWriterCellBuilder(Class<?> clazz) {
        return CLASS_CELL_BUILDER_MAP.get(clazz);
    }

    private static Map<Class<?>, CellType> initClassCellTypeMap() {
        Map<Class<?>, CellType> map = new ConcurrentHashMap<>();
        map.put(Integer.class, CellType.NUMERIC);
        map.put(Long.class, CellType.NUMERIC);
        map.put(Double.class, CellType.NUMERIC);
        map.put(String.class, CellType.STRING);
        map.put(OffsetDateTime.class, CellType.NUMERIC);
        map.put(BigDecimal.class, CellType.NUMERIC);
        return map;
    }

    private static Map<Class<?>, WriterCellBuilder<Object>> initClassCellBuilderMap() {
        Map<Class<?>, WriterCellBuilder<Object>> map = new ConcurrentHashMap<>();
        map.put(Byte.class, (cell, value) -> cell.setCellValue((Byte) value));
        map.put(Short.class, (cell, value) -> cell.setCellValue((Short) value));
        map.put(Integer.class, (cell, value) -> cell.setCellValue((Integer) value));
        map.put(Long.class, (cell, value) -> cell.setCellValue((Long) value));
        map.put(Float.class, (cell, value) -> cell.setCellValue((Float) value));
        map.put(Double.class, (cell, value) -> cell.setCellValue((Double) value));
        map.put(Boolean.class, (cell, value) -> cell.setCellValue((Boolean) value));
        map.put(String.class, (cell, value) -> cell.setCellValue((String) value));
        map.put(OffsetDateTime.class, CellTypeHandler::offsetDateTimeBuilder);
        return map;
    }

    /* ==================================== CellBuilder ==================================== */

    private static void offsetDateTimeBuilder(final Cell cell, final Object value) {
        OffsetDateTime offsetDateTime = (OffsetDateTime) value;
        cell.setCellValue(Date.from(offsetDateTime.toInstant()));
    }

}
