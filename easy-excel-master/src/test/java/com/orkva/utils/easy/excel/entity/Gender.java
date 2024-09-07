package com.orkva.utils.easy.excel.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * Gender
 *
 * @author Shepherd Xie
 * @version 2022/11/21
 */
@AllArgsConstructor
@Getter
@ToString
public enum Gender {
    OTHER("Other", 0),
    MALE("Male", 1),
    FEMALE("Female", 2);

    private String name;
    private Integer value;
}
