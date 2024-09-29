import { Button, Message, Popconfirm } from "@arco-design/web-react";
import { MixedItem } from "../../../../types/index.ts";
import {
  checkDataSourceTable,
  DATA_SOURCE_TABLE,
  groupTableHeaderKeys,
} from "../../../../utils/checkDataSource.ts";
import { DeleteParams } from "../../../../services/types.ts";
import { deleteItem } from "../../../../services/searchTable.ts";

export enum TableMode {
  ALL = 0,
  FZ,
  JS,
}

export const CN_2_EN_TABLES = {
  FZ: {
    井名: "wellName",
    分公司: "company",
    一级井别: "primaryWellType",
    井型: "wellType",
    开始时间: "startTime",
    结束时间: "endTime",
    "持续时间（hr）": "durationHours",
    一级代码: "firstLevelCode",
    二级代码: "secondLevelCode",
    三级代码: "thirdLevelCode",
    四级代码: "fourthLevelCode",
    复杂情况类型: "complexityType",
    井眼段: "wellSection",
    "开始深度（m）": "startDepth",
    "结束深度（m）": "endDepth",
    作业描述: "operationDescription",
  },
  JB: {
    井名: "wellName",
    油田名: "oilFieldName",
    油田代码: "oilFieldCode",
    是否合作: "isCooperative",
    承包商: "contractor",
    一级井别: "primaryWellType",
    二级井别: "secondaryWellType",
    三级井别: "tertiaryWellType",
    井型: "wellType",
    水深: "waterDepth1",
    设计深度: "designDepth",
    设计垂深: "designVerticalDepth",
    设计时间: "designDate",
    构造名称: "structureName",
    井唯一识别号: "uniqueWellId",
    中文井名: "chineseWellName",
    作业者: "operator",
    油气类型: "hydrocarbonType",
    风险等级: "riskLevel",
    风险类型: "riskType",
    风险级别: "riskGrade",
    含硫级别: "sulfurGrade",
    是否含CO2: "containsCO2",
    深度零点: "zeroDepth",
    初始补心海拔: "initialKellyElevation",
    地面海拔: "groundElevation",
    当前补心海拔: "currentKellyElevation",
    套管头海拔: "casingHeadElevation",
    油管法兰海拔: "waterDepth2",
    泥线海拔: "mudLineElevation",
    人工底部深度: "artificialBottomDepth",
    钻井天数: "drillingDays",
    定位方法: "positioningMethod",
    位置: "location",
    大地测量系统: "geodeticSystem",
    经度: "longitude",
    纬度: "latitude",
    X坐标: "xcoordinate",
    Y坐标: "ycoordinate",
    槽口号: "slotNumber",
    国家: "country",
    区域: "region",
    州省: "province",
    县: "county",
    "工程报废井？": "scrapedWell",
    现场办公室电话: "officePhone",
    备注: "remark",
  },
  JS: {
    井名: "wellName",
    一级井别: "primaryWellType",
    井型: "wellType",
    井段: "wellSection",
    "井眼尺寸（in）": "holeDiameter",
    开始时间: "startTime",
    结束时间: "endTime",
    "开始深度（m）": "startDepth",
    "开始垂深（m）": "startVerticalDepth",
    "结束深度（m）": "endDepth",
    "结束垂深（m）": "endVerticalDepth",
    "套管尺寸（in）": "casingDiameter",
    钢级: "steelGrade",
    "套管下深（m）": "casingSetDepth",
    "套管长度（m）": "casingLength",
    "水泥返高（m）": "cementReturnHeight",
    "上层套管尺寸（in）": "upperCasingDiameter",
    "上层套管下深（m）": "upperCasingSetDepth",
    钻井液体系: "drillingFluidSystem",
    "钻井液密度（g/cm3）": "drillingFluidDensity",
    "塑性黏度（mPa•s）": "plasticViscosity",
    "屈服值（Pa）": "yieldValue",
    地层: "formationPressure",
  },
  ZT: {
    井名: "wellName",
    分公司: "company",
    一级井别: "primaryWellType",
    井型: "wellType",
    钻头编号: "drillBitNumber",
    钻头类型: "drillBitType",
    "尺寸(in)": "sizeIn",
    厂家: "manufacturer",
    型号: "model",
    序列号: "serialNumber",
    "喷嘴(1/32″)": "nozzleSize",
    "钻头TFA(in2)": "drillBitTFAn2",
    "入井深度(m)": "entryDepth",
    "出井深度(m)": "exitDepth",
    入井时间: "entryTime",
    出井时间: "exitTime",
    "进尺(m)": "advanceDistance",
    "纯钻时间(h)": "pureDrillingTime",
    "ROP(m/h)": "rop",
    "最大钻压(tonne)": "maxDrillingPressure",
    "最小钻压(tonne)": "minDrillingPressure",
    "最大转速(RPM)": "maxRotationSpeed",
    "最小转速(RPM)": "minRotationSpeed",
    钻头IADC磨损评价: "iadcWearEvaluation",
    钻遇地层: "encounteredFormation",
  },
};

export const DEFAULT_SEARCH_PARAMS = { FZ: {}, JB: {}, JS: {}, ZT: {} };
export const EN_2_CN_TABLES = { FZ: {}, JB: {}, JS: {}, ZT: {} };
Object.keys(CN_2_EN_TABLES).forEach((tableName) => {
  const table = { ...CN_2_EN_TABLES[tableName] };
  Object.keys(table).forEach((cnKey) => {
    const enKey = table[cnKey];
    table[enKey] = cnKey;
    DEFAULT_SEARCH_PARAMS[tableName][enKey] = null;
  });
});

//  = reverseMapping(CN_2_EN_TABLES);
export const CN_2_EN_MIXED = {
  ...CN_2_EN_TABLES.FZ,
  ...CN_2_EN_TABLES.JB,
  ...CN_2_EN_TABLES.JS,
  ...CN_2_EN_TABLES.ZT,
};
export const EN_2_CN_MIXED = {};
Object.keys(CN_2_EN_MIXED).forEach((cn) => {
  const en = (CN_2_EN_MIXED[cn] as string) ?? "";
  EN_2_CN_MIXED[en.toLowerCase()] = cn;
});

console.log("cn2en", CN_2_EN_MIXED);
console.log("en2cn", EN_2_CN_MIXED);

export const formConfigList = [
  [
    { label: "井基本类型", field: "primaryWellType", defaultValue: "" },
    { label: "井名", field: "wellName", defaultValue: "" },
    { label: "井类型", field: "wellType", defaultValue: "" },
    // { label: "油田类型", field: "oilFieldName", defaultValue: "" },
    // { label: "合同方", field: "contractor", defaultValue: "" },
  ],
  [],
];

export const getColumns = (handleSearch: () => void) => {
  const handleDeleteItem = async (params: DeleteParams) => {
    const res = await deleteItem(params);
    console.log("res", res);
    if (res?.data?.code === 1) {
      Message.info("删除成功");
      console.log("删除成功");
      handleSearch();
    } else {
      Message.info("删除失败");
    }
  };

  const columnMapper = {
    // onlyKey: {
    //   fixed: "left",
    // },
    wellName: {
      fixed: "left",
    },
    // company: {
    //   fixed: "left",
    // },
    location: {
      width: 200,
    },
    operationDescription: {
      width: 600,
    },
    操作: {
      width: 80,
      fixed: "right",
      render: (_, row: MixedItem) => (
        <Popconfirm
          focusLock
          title="确定要删除吗？"
          okText="确定"
          cancelText="取消"
          onOk={() => {
            const keys = Object.keys(row);
            const num = checkDataSourceTable(keys);
            console.log("删除", _, row, num);
            if (row.onlyKey && num) {
              handleDeleteItem({ OnlyKey: row.onlyKey, num });
            }
          }}
        >
          <Button type="primary" status="danger" size="mini">
            删除
          </Button>
        </Popconfirm>
      ),
    },
  };

  const allTableHeaders = [
    // "onlyKey",
    "wellName",
    "company",
    "primaryWellType",
    "wellType",
    "startTime",
    "endTime",
    "durationHours",
    "firstLevelCode",
    "secondLevelCode",
    "thirdLevelCode",
    "fourthLevelCode",
    "complexityType",
    "wellSection",
    "startDepth",
    "endDepth",
    "operationDescription",
    "status",
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
    "entryTime",
    "exitTime",
    "advanceDistance",
    "pureDrillingTime",
    "rop",
    "maxDrillingPressure",
    "minDrillingPressure",
    "maxRotationSpeed",
    "minRotationSpeed",
    "iadcWearEvaluation",
    "encounteredFormation",
    "holeDiameter",
    "steelGrade",
    "yieldValue",
    "casingLength",
    "casingDiameter",
    "casingSetDepth",
    "upperCasingDiameter",
    "startVerticalDepth",
    "cementReturnHeight",
    "plasticViscosity",
    "drillingFluidDensity",
    "upperCasingSetDepth",
    "drillingFluidSystem",
    "endVerticalDepth",
    "formationPressure",
    "oilFieldName",
    "oilFieldCode",
    "isCooperative",
    "contractor",
    "secondaryWellType",
    "tertiaryWellType",
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
  ];

  const extraHeaders = ["操作"];
  const groupedTableHeaders = groupTableHeaderKeys(CN_2_EN_TABLES);

  const extraFormat = (str) => {
    const cnStr = EN_2_CN_MIXED[str?.toLowerCase()] ?? str;
    return {
      key: str,
      title: cnStr,
      dataIndex: str,
      width: Math.max(str.length * 16, 80),
      ...(columnMapper[str] ?? {}),
    };
  };

  // const headers =
  //   +tableMode === +DATA_SOURCE_TABLE.ALL
  //     ? allTableHeaders
  //     : groupedTableHeaders[tableMode];

  const headerSet = {
    [DATA_SOURCE_TABLE[DATA_SOURCE_TABLE.ALL]]: [
      ...allTableHeaders,
      ...extraHeaders,
    ].map(extraFormat),
  };
  groupedTableHeaders.forEach((headers, index) => {
    const key = DATA_SOURCE_TABLE[index];
    if (Array.isArray(headers)) {
      headerSet[key] = [...headers, ...extraHeaders].map(extraFormat);
    }
  });

  console.log("headerSet", headerSet);

  return headerSet;
};
