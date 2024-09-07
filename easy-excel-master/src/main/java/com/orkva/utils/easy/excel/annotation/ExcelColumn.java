package com.orkva.utils.easy.excel.annotation;

import java.lang.annotation.*;

/**
 * ExcelColumn
 *
 * @author Shepherd Xie
 * @version 2022/11/17
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcelColumn {
    /**
     * Excel column value
     *
     * @return column value
     */
    String value();

    /**
     * Cell width
     *
     * @return cell width
     */
    int width() default 18;

    /**
     * Parse instance attribute if type is {@link Enum}
     *
     * @return Parse fields
     */
    String enumValue() default "name";

    /**
     * Datetime formatting pattern if column type is {@link java.time.OffsetDateTime}
     *
     * @return the pattern
     */
    String datetimePattern() default "yyyy/mm/dd hh:mm:ss";

    /**
     * Is required
     *
     * @return is required
     */
    boolean required() default true;
}
