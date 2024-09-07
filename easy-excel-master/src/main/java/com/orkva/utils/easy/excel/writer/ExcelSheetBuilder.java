package com.orkva.utils.easy.excel.writer;

import com.orkva.utils.easy.excel.CellTypeHandler;
import com.orkva.utils.easy.excel.WriterCellBuilder;
import com.orkva.utils.easy.excel.annotation.ExcelColumn;
import com.orkva.utils.easy.excel.annotation.ExcelMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.poi.ss.usermodel.*;

import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.time.OffsetDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * ExcelSheetBuilder
 *
 * @author Shepherd Xie
 * @version 2022/11/22
 */
@Data
@AllArgsConstructor
public final class ExcelSheetBuilder {
    /**
     * Excel POI default single character width
     */
    private static final int POI_FONT_WIDTH = 256;
    private String title;
    public final Collection<Object> instances;
    private final Class<?> type;
    private Workbook workbook;
    private final Map<ExcelColumn, CellStyle> columnCellStyleMap;

    public ExcelSheetBuilder(Collection<Object> instances, Class<?> type, String title) {
        this.instances = instances;
        this.title = title;
        columnCellStyleMap = new ConcurrentHashMap<>();

        Class<?> loadType = instances.stream().findAny().map(Object::getClass)
                .orElseThrow(() -> new RuntimeException("Cannot get class from write data."));
        if (type == null) {
            this.type = loadType;
        } else if (type.isAssignableFrom(loadType)) {
            this.type = type;
        } else {
            throw new RuntimeException("Cannot specify the type of instances.");
        }
    }

    public boolean isTitleAvailable() {
        return title != null && !title.isEmpty();
    }

    public void buildSheet(Workbook workbook) {
        if (!this.type.isAnnotationPresent(ExcelMapper.class)) {
            throw new RuntimeException("This class is not supported.");
        }
        this.workbook = workbook;

        List<Field> excelColumnFields = loadExcelColumnField();
        if (excelColumnFields.isEmpty()) {
            throw new RuntimeException("The target class does not have a valid parse field.");
        }

        List<ExcelColumn> excelColumns = excelColumnFields.stream()
                .map(field -> field.getDeclaredAnnotation(ExcelColumn.class))
                .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));

        Sheet sheet = isTitleAvailable()
                ? workbook.createSheet(this.title)
                : workbook.createSheet();
        AtomicInteger index = new AtomicInteger();

        // set header
        Row header = sheet.createRow(index.getAndIncrement());
        buildHeader(header, excelColumns);

        // set contents
        for (Object instance : this.instances) {
            if (!this.type.isInstance(instance)) {
                throw new RuntimeException("Only allow instances of the same type to be manipulated.");
            }
            buildRow(excelColumnFields, sheet.createRow(index.getAndIncrement()), instance);
        }

    }

    private List<Field> loadExcelColumnField() {
        return Arrays.stream(this.type.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(ExcelColumn.class))
                .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
    }

    /**
     * Sheet title build
     *
     * @param header
     * @param excelColumns
     * @return
     */
    private Row buildHeader(Row header, Collection<ExcelColumn> excelColumns) {
        AtomicInteger index = new AtomicInteger();
        for (ExcelColumn excelColumn : excelColumns) {
            final Cell cell = header.createCell(index.get(), CellType.STRING);
            cell.setCellValue(excelColumn.value());
            setColumnStyle(header, index.get(), excelColumn);
            index.incrementAndGet();
        }
        return header;
    }

    /**
     * Set column style
     *
     * @param header
     * @param index
     * @param excelColumn
     */
    private void setColumnStyle(Row header, int index, ExcelColumn excelColumn) {
        header.getSheet().setColumnWidth(index, excelColumn.width() * POI_FONT_WIDTH);
    }

    /**
     * Row build
     *
     * @param fields
     * @param row
     * @param instance
     * @return
     */
    private Row buildRow(final Collection<Field> fields, Row row, Object instance) {
        final AtomicInteger index = new AtomicInteger();
        for (Field field : fields) {
            CellType cellType = CellTypeHandler.getCellType(field.getType());
            Cell cell = buildCell(row.createCell(index.getAndIncrement(), cellType), field, instance);
            cell.setCellStyle(buildCellStyle(field));
        }
        return row;
    }

    /**
     * Cell build
     *
     * @param cell
     * @param field
     * @param instance
     * @return
     */
    private Cell buildCell(Cell cell, Field field, Object instance) {
        final Object value;
        try {
            field.setAccessible(true);
            value = field.get(instance);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("This field is not visible", e);
        } finally {
            field.setAccessible(false);
        }
        if (value == null) {
            return cell;
        }
        WriterCellBuilder<Object> cellBuilder = classCellBuilder(field);
        if (cellBuilder == null) {
            String errorMsg = MessageFormat.format("Cannot find parser for {0}.", field.getType().getSimpleName());
            throw new RuntimeException(errorMsg);
        }
        cellBuilder.build(cell, value);
        return cell;
    }

    /**
     * Get cell builder
     *
     * @param field
     * @return
     */
    private WriterCellBuilder<Object> classCellBuilder(Field field) {
        if (Enum.class.isAssignableFrom(field.getType())) {
            final ExcelColumn excelColumn = field.getDeclaredAnnotation(ExcelColumn.class);
            try {
                final Field enumValueField = field.getType().getDeclaredField(excelColumn.enumValue());
                return (cell, value) -> {
                    final Object enumValue;
                    enumValueField.setAccessible(true);
                    try {
                        enumValue = enumValueField.get(value);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException("This field is not visible", e);
                    } finally {
                        enumValueField.setAccessible(false);
                    }
                    cell.setCellValue((String) enumValue);
                };
            } catch (NoSuchFieldException e) {
                return (cell, value) -> {
                    Enum<?> anEnum = (Enum<?>) value;
                    cell.setCellValue(anEnum.name());
                };
            }
        }
        return CellTypeHandler.getWriterCellBuilder(field.getType());
    }

    /**
     * Cell style build
     *
     * @param field
     * @return
     */
    private CellStyle buildCellStyle(final Field field) {
        ExcelColumn excelColumn = field.getDeclaredAnnotation(ExcelColumn.class);
        CellStyle cellStyle = columnCellStyleMap.get(excelColumn);
        if (cellStyle != null) {
            return cellStyle;
        }
        CellStyle newCellStyle = workbook.createCellStyle();
        if (field.getType() == OffsetDateTime.class) {
            CreationHelper creationHelper = workbook.getCreationHelper();
            newCellStyle.setDataFormat(creationHelper.createDataFormat().getFormat(excelColumn.datetimePattern()));
        }
        columnCellStyleMap.put(excelColumn, newCellStyle);
        return newCellStyle;
    }

    Collection<Object> getInstances(){
        return instances;
    }
}
