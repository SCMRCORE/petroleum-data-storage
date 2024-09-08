package com.petroleumcommom.utils;

import cn.hutool.poi.excel.ExcelReader;

public class JiBenTool {

    public static ExcelReader transition(ExcelReader reader){
        reader.addHeaderAlias("井名", "wellName");
        reader.addHeaderAlias("油田名", "oilFieldName");
        reader.addHeaderAlias("油田代码", "oilFieldCode");
        reader.addHeaderAlias("是否合作", "isCooperative");
        reader.addHeaderAlias("承包商", "contractor");
        reader.addHeaderAlias("一级井别", "primaryWellType");
        reader.addHeaderAlias("二级井别", "secondaryWellType");
        reader.addHeaderAlias("三级井别", "tertiaryWellType");
        reader.addHeaderAlias("井型", "wellType");
        reader.addHeaderAlias("水深1", "waterDepth1");
        reader.addHeaderAlias("设计深度", "designDepth");
        reader.addHeaderAlias("设计垂深", "designVerticalDepth");
        reader.addHeaderAlias("设计时间", "designDate");
        reader.addHeaderAlias("构造名称", "structureName");
        reader.addHeaderAlias("井唯一识别号", "uniqueWellId");
        reader.addHeaderAlias("中文井名", "chineseWellName");
        reader.addHeaderAlias("作业者", "operator");
        reader.addHeaderAlias("油气类型", "hydrocarbonType");
        reader.addHeaderAlias("风险等级", "riskLevel");
        reader.addHeaderAlias("风险类型", "riskType");
        reader.addHeaderAlias("风险级别", "riskGrade");
        reader.addHeaderAlias("含硫级别", "sulfurGrade");
        reader.addHeaderAlias("是否含CO2", "containsCO2");
        reader.addHeaderAlias("深度零点", "zeroDepth");
        reader.addHeaderAlias("初始补心海拔", "initialKellyElevation");
        reader.addHeaderAlias("地面海拔", "groundElevation");
        reader.addHeaderAlias("当前补心海拔", "currentKellyElevation");
        reader.addHeaderAlias("套管头海拔", "casingHeadElevation");
        reader.addHeaderAlias("水深2", "waterDepth2");
        reader.addHeaderAlias("泥线海拔", "mudLineElevation");
        reader.addHeaderAlias("人工底部深度", "artificialBottomDepth");
        reader.addHeaderAlias("钻井天数", "drillingDays");
        reader.addHeaderAlias("定位方法", "positioningMethod");
        reader.addHeaderAlias("位置", "location");
        reader.addHeaderAlias("大地测量系统", "geodeticSystem");
        reader.addHeaderAlias("经度", "longitude");
        reader.addHeaderAlias("纬度", "latitude");
        reader.addHeaderAlias("X坐标", "xcoordinate");
        reader.addHeaderAlias("Y坐标", "ycoordinate");
        reader.addHeaderAlias("槽口号", "slotNumber");
        reader.addHeaderAlias("国家", "country");
        reader.addHeaderAlias("区域", "region");
        reader.addHeaderAlias("州省", "province");
        reader.addHeaderAlias("县", "county");
        reader.addHeaderAlias("工程报废井？", "scrapedWell");
        reader.addHeaderAlias("现场办公室电话", "officePhone");
        reader.addHeaderAlias("备注", "remark");
        return reader;
    }
}


