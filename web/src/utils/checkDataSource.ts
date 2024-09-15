import {
  CN_2_EN_MIXED,
  EN_2_CN_MIXED,
} from "../pages/DataManagement/components/SearchTable/configs.tsx";

export enum DATA_SOURCE_TABLE {
  FZ = 1,
  JB,
  JS,
  ZT,
}

export const DATA_SOURCE_TABLE_TITLE_MAP = {
  FZ: "复杂情况表",
  JB: "基本情况表",
  JS: "井身结构表",
  ZT: "钻头总览表",
};

export const checkDataSourceTable = (keys) => {
  if (
    keys.some((key) =>
      [
        "durationHours",
        "firstLevelCode",
        "secondLevelCode",
        "thirdLevelCode",
        "fourthLevelCode",
        "complexityType",
        "operationDescription",
      ].includes(key)
    )
  )
    return DATA_SOURCE_TABLE.FZ;
  if (
    keys.some((key) =>
      [
        "drillBitNumber",
        "drillBitType",
        "sizeIn",
        "manufacturer",
        "model",
        "serialNumber",
        "nozzleSize",
        "drillBitTFAn2",
        "entryDepth",
        "exitDepth",
        "advanceDistance",
        "pureDrillingTime",
        "rop",
        "maxDrillingPressure",
        "minDrillingPressure",
        "maxRotationSpeed",
        "minRotationSpeed",
        "iadcWearEvaluation",
        "encounteredFormation",
      ].includes(key)
    )
  )
    return DATA_SOURCE_TABLE.JB;

  if (
    keys.some((key) =>
      [
        "steelGrade",
        "yieldValue",
        "casingLength",
        "upperCasingDiameter",
        "startVerticalDepth",
        "cementReturnHeight",
        "plasticViscosity",
        "drillingFluidDensity",
        "upperCasingSetDepth",
        "drillingFluidSystem",
        "endVerticalDepth",
        "formationPressure",
      ].includes(key)
    )
  )
    return DATA_SOURCE_TABLE.JS;
  if (
    keys.some((key) =>
      [
        "waterDepth1",
        "designDepth",
        "designVerticalDepth",
        "designDate",
        "structureName",
        "uniqueWellId",
        "chineseWellName",
        "operator",
        "hydrocarbonType",
        "riskLevel",
        "riskType",
        "riskGrade",
        "sulfurGrade",
        "containsCO2",
        "zeroDepth",
        "initialKellyElevation",
        "groundElevation",
        "currentKellyElevation",
        "casingHeadElevation",
        "waterDepth2",
        "mudLineElevation",
        "artificialBottomDepth",
        "drillingDays",
        "positioningMethod",
        "location",
        "geodeticSystem",
        "longitude",
        "latitude",
        "xcoordinate",
        "ycoordinate",
        "slotNumber",
        "country",
        "region",
        "province",
        "county",
        "scrapedWell",
        "officePhone",
        "remark",
      ].includes(key)
    )
  )
    return DATA_SOURCE_TABLE.ZT;
  return;
};

export const formatCnToEn = (key: string) => {
  const formattedKey = key?.replace(/[\r\n\u21B5]+/g, "").toLowerCase();
  const cn = EN_2_CN_MIXED[formattedKey] ?? formattedKey;
  const positiveEn = CN_2_EN_MIXED[cn];
  return positiveEn;
};
