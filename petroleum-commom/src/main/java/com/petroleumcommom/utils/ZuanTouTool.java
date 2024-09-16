package com.petroleumcommom.utils;

import cn.hutool.poi.excel.ExcelReader;

public class ZuanTouTool {

    public static ExcelReader transition(ExcelReader reader){
        reader.addHeaderAlias("井名", "wellName");
        reader.addHeaderAlias("公司", "company");
        reader.addHeaderAlias("一级井别", "primaryWellType");
        reader.addHeaderAlias("井型", "wellType");
        reader.addHeaderAlias("钻头编号", "drillBitNumber");
        reader.addHeaderAlias("钻头类型", "drillBitType");
        reader.addHeaderAlias("尺寸(in)", "sizeIn");
        reader.addHeaderAlias("厂家", "manufacturer");
        reader.addHeaderAlias("型号", "model");
        reader.addHeaderAlias("序列号", "serialNumber");
        reader.addHeaderAlias("喷嘴(1/32″)", "nozzleSize");
        reader.addHeaderAlias("钻头TFA(in^2)", "drillBitTFAn2");
        reader.addHeaderAlias("入井深度(m)", "entryDepth");
        reader.addHeaderAlias("出井深度(m)", "exitDepth");
        reader.addHeaderAlias("入井时间", "entryTime");
        reader.addHeaderAlias("出井时间", "exitTime");
        reader.addHeaderAlias("进尺(m)", "advanceDistance");
        reader.addHeaderAlias("纯钻时间(h)", "pureDrillingTime");
        reader.addHeaderAlias("ROP(m/h)", "rop");
        reader.addHeaderAlias("最大钻压(tonne)", "maxDrillingPressure");
        reader.addHeaderAlias("最小钻压(tonne)", "minDrillingPressure");
        reader.addHeaderAlias("最大转速(RPM)", "maxRotationSpeed");
        reader.addHeaderAlias("最小转速(RPM)", "minRotationSpeed");
        reader.addHeaderAlias("钻头IADC磨损评价", "iadcWearEvaluation");
        reader.addHeaderAlias("钻遇地层", "encounteredFormation");
        return reader;
    }
}


