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
    钻头类型: "bit_type",
    钻头型号: "model",
    钻头标识: "drill_bit_id",
    钻头编号: "bit_no",
    钻头尺寸: "bit_size",
    钻头长度: "bit_length",
    接头扣型: "connection_thread",
    保径长度: "bit_gauge_length",
    IADC代码: "iadccode",
    待钻井眼尺寸: "sz_odpass",
    入井前已转时间: "drilled_before_time",
    入井前进尺: "drilled_before_footage",
    钻头厂家: "factory",
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
  [{ label: "井名", field: "Well_Name_Drill", defaultValue: "" }],
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
