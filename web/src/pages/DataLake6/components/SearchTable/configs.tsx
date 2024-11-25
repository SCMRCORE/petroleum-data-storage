// import { Button, Message, Popconfirm } from "@arco-design/web-react";
import {

  DATA_SOURCE_TABLE,
  groupTableHeaderKeys,
} from "../../../../utils/checkDataSource.ts";

export enum TableMode {
  CASE1 = 0,
}

const CN_2_EN_TABLES = {

  CASE1: {
    井名: "well_name_drill",
    时间索引: "date_time_index",
    测量井深: "dmea",
    垂直井深: "dver",
    钻井液总池体积: "tva",
    入口钻井液温度: "mtia",
    出口钻井液温度: "mtoa",
    入口密度: "mdia",
    出口密度: "mdoa",
    入口流量: "mfia",
    返出流量: "mfoa",
    钻压: "woba",
    "钻压（最大值）": "wobx",
    悬重: "hkla",
    "悬重（最大值）": "hklx",
    扭矩: "tqa",
    "扭矩（最大值）": "tqx",
    机械钻速: "ropa",
    转速: "rpma",
    泵压: "sppa",
    大钩速度: "spr5",
    钻头垂深: "dbtm",
    气全量: "gasa",
    累计钻头进尺: "spr3",
    所属分公司: "branch_id",
  },
};

const DEFAULT_SEARCH_PARAMS = { CASE1: {} };
const EN_2_CN_TABLES = { CASE1: {} };
Object.keys(CN_2_EN_TABLES).forEach((tableName) => {
  const table = CN_2_EN_TABLES[tableName];
  Object.keys(table).forEach((cnKey) => {
    const enKey = table[cnKey];
    EN_2_CN_TABLES[tableName][enKey] = cnKey;
    DEFAULT_SEARCH_PARAMS[tableName][enKey] = null;
  });
});

const CN_2_EN_MIXED = {
  ...CN_2_EN_TABLES.CASE1,
};
const EN_2_CN_MIXED = {};
Object.keys(CN_2_EN_MIXED).forEach((cn) => {
  const en = (CN_2_EN_MIXED[cn] as string) ?? "";
  EN_2_CN_MIXED[en.toLowerCase()] = cn;
});

console.log("cn2en", CN_2_EN_MIXED);
console.log("en2cn", EN_2_CN_MIXED);

export const formConfigList = [
  [
    { label: "井名", field: "Well_Name_Drill", defaultValue: "" },
    { label: "时间索引", field: "DATE_TIME_INDEX", defaultValue: "" },

  ],
  [],
];


const getColumns = () =>


  {












    const columnMapper = {











































    };

    const allTableHeaders = Object.keys(CN_2_EN_TABLES.CASE1).map(
      (key) => CN_2_EN_TABLES.CASE1[key]
    );

    const extraHeaders = [];
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

    console.log("allTableHeaders", allTableHeaders);
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

export {
  DEFAULT_SEARCH_PARAMS,
  EN_2_CN_TABLES,
  EN_2_CN_MIXED,
  CN_2_EN_MIXED,
  CN_2_EN_TABLES,
  getColumns,
};
