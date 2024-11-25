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
    井深: "sample_depth",
    "3转读数": "value_3rpm",
    "6转读数": "value_6rpm",
    "30转读数": "value_30rpm",
    "60转读数": "value_60rpm",
    "100转读数": "value_100rpm",
    "200转读数": "value_200rpm",
    "300转读数": "value_300rpm",
    "600转读数": "value_600rpm",
    "10分钟静切力": "gel30min",
    k值: "consistency_coefficient",
    屈服值: "yield_value",
    塑性粘度: "plastic_viscosity",
    漏斗粘度: "funnel_visc",
    PH值: "fluid_ph",
    API失水: "apiloss",
    地面损耗量: "mud_lost_surface",
    循环泥浆体积有效计算: "mud_vol_active",
    储备池泥浆量: "mud_vol_surf_active",
    重晶石: "barite",
    "聚膜钻井液用可降解成膜剂（CLMH-?）含量": "clmhi_percent",
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
