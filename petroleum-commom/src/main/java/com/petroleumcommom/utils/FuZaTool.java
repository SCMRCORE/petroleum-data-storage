package com.petroleumcommom.utils;

import cn.hutool.poi.excel.ExcelReader;

public class FuZaTool {

    public static ExcelReader transition(ExcelReader reader){
        reader.addHeaderAlias("井名", "wellName");
        reader.addHeaderAlias("分公司", "company");
        reader.addHeaderAlias("一级井别", "primaryWellType");
        reader.addHeaderAlias("井型", "wellType");
        reader.addHeaderAlias("开始时间", "startTime");
        reader.addHeaderAlias("结束时间", "endTime");
        reader.addHeaderAlias("持续时间（hr）", "durationHours");
        reader.addHeaderAlias("一级代码", "firstLevelCode");
        reader.addHeaderAlias("二级代码", "secondLevelCode");
        reader.addHeaderAlias("三级代码", "thirdLevelCode");
        reader.addHeaderAlias("四级代码", "fourthLevelCode");
        reader.addHeaderAlias("复杂情况类型", "complexityType");
        reader.addHeaderAlias("井眼段", "wellSection");
        reader.addHeaderAlias("开始深度（m）", "startDepth");
        reader.addHeaderAlias("结束深度（m）", "endDepth");
        reader.addHeaderAlias("作业描述", "operationDescription");
        return reader;
    }
}


