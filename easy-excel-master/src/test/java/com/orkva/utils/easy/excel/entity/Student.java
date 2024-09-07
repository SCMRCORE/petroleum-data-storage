package com.orkva.utils.easy.excel.entity;

import com.orkva.utils.easy.excel.annotation.ExcelColumn;
import com.orkva.utils.easy.excel.annotation.ExcelMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

/**
 * Student
 *
 * @author Shepherd Xie
 * @version 2022/11/17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ExcelMapper("Student")
public class Student {
    @ExcelColumn("id")
    private Integer id;
    @ExcelColumn("name")
    private String name;
    @ExcelColumn("gander")
    private Gender gender;
    @ExcelColumn("birth")
    private OffsetDateTime birth;
}
