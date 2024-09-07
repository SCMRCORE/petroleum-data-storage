package com.orkva.utils.easy.excel.annotation;

import java.lang.annotation.*;

/**
 * ExcelMapper
 *
 * @author Shepherd Xie
 * @version 2022/11/20
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcelMapper {
    String value() default "";
}
