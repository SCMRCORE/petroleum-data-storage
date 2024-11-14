import {
  CN_2_EN_MIXED,
  EN_2_CN_MIXED,
} from "../pages/DataManagement/components/SearchTable/configs.tsx";

export enum DATA_SOURCE_TABLE {
  ALL = 0,
  JB = 1,
  JS,
  FZ,
  ZT,
  TABLE1,
}

export const DATA_SOURCE_TABLE_TITLE_MAP = {
  ALL: "全部",
  JB: "基本信息",
  JS: "井身结构",
  FZ: "复杂情况",
  ZT: "钻头总览",
  TABLE1: "基本信息",
};

/** 根据是否包含某些特定字段来判别是哪张表的神奇函数 */
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
    return DATA_SOURCE_TABLE.ZT;

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
    return DATA_SOURCE_TABLE.JB;
  return;
};

export const formatCnToEn = (key: string) => {
  const formattedKey = key?.replace(/[\r\n\u21B5]+/g, "");
  const cn = EN_2_CN_MIXED[formattedKey] ?? formattedKey;
  const positiveEn = CN_2_EN_MIXED[cn];
  return positiveEn;
};

export const groupTableHeaderKeys = (tables) => {
  const result = [];
  for (const category in tables) {
    const table = tables[category];
    const englishKeys = {};
    for (const chineseKey in table) {
      const englishKey = table[chineseKey];
      englishKeys[englishKey] = englishKey; // 保持键的存在，即使值相同
    }
    result[DATA_SOURCE_TABLE[category]] = Object.keys(englishKeys); // 只需键即可
  }
  return result;
};
