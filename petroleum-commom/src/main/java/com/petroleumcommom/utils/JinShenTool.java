package com.petroleumcommom.utils;

import cn.hutool.poi.excel.ExcelReader;

public class JinShenTool {

    public static ExcelReader transition(ExcelReader reader){
        reader.addHeaderAlias("井名", "wellName");
        reader.addHeaderAlias("一级井别", "primaryWellType");
        reader.addHeaderAlias("井型", "wellType");
        reader.addHeaderAlias("井段", "WellSection");
        reader.addHeaderAlias("井眼尺寸（in）", "HoleDiameter");
        reader.addHeaderAlias("开始时间", "StartTime");
        reader.addHeaderAlias("结束时间", "EndTime");
        reader.addHeaderAlias("开始深度（m）", "StartDepth");
        reader.addHeaderAlias("开始垂深（m）", "StartVerticalDepth");
        reader.addHeaderAlias("结束深度（m）", "EndDepth");
        reader.addHeaderAlias("结束垂深（m）", "EndVerticalDepth");
        reader.addHeaderAlias("套管尺寸（in）", "CasingDiameter");
        reader.addHeaderAlias("钢级", "SteelGrade");
        reader.addHeaderAlias("套管下深（m）", "CasingSetDepth");
        reader.addHeaderAlias("套管长度（m）", "CasingLength");
        reader.addHeaderAlias("水泥返高（m）", "CementReturnHeight");
        reader.addHeaderAlias("上层套管尺寸（in）", "UpperCasingDiameter");
        reader.addHeaderAlias("上层套管下深（m）", "UpperCasingSetDepth");
        reader.addHeaderAlias("钻井液体系", "DrillingFluidSystem");
        reader.addHeaderAlias("钻井液密度（g/cm3）", "DrillingFluidDensity");
        reader.addHeaderAlias("塑性黏度（mPa•s）", "PlasticViscosity");
        reader.addHeaderAlias("屈服值（Pa）", "YieldValue");
        reader.addHeaderAlias("地层", "FormationPressure");
        return reader;
    }
}


