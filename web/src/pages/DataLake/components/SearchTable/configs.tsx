// import { Button, Message, Popconfirm } from "@arco-design/web-react"; 
import {
  
  DATA_SOURCE_TABLE,
  groupTableHeaderKeys,
} from "../../../../utils/checkDataSource.ts";  

export enum TableMode {
  CASE1 = 0,
}

const CN_2_EN_TABLES = {
  
  TABLE1: {
    "井名": "well_name_drill",
    "井型": "well_config",
    "中文井名": "chinese_well_name",
    "一级井别": "well_type",
    "二级井别": "well_sub_type",
    "三级井别": "well_tertiary_type",
    "风险井类别": "risk_class",
    "设计井深": "depth_design",
    "设计垂深": "tvd_design",
    "开钻时间": "spud_time",
    "完钻时间": "finish_drill_time",
    "钻井完成时间": "complete_drill_time",
    "区域构造位置": "location",
    "地面海拔": "ground_elv",
    "水深": "water_depth",
    "初始补心海拔": "orig_elv_kb",
    "当前补心海拔": "recent_elv_kb",
    "槽口号": "slot",
    "主要目的地层": "main_form_action",
    "井控事件次数": "well_control",
    
    "所属分公司": "branch_id",
    "承包商": "contrator",
    "作业者": "operator",
    "自营/合作": "is_cooperation",
  },
};

const DEFAULT_SEARCH_PARAMS = { TABLE1: {},};
const EN_2_CN_TABLES =  { TABLE1: {},};
Object.keys(CN_2_EN_TABLES).forEach((tableName) => {
  const table = CN_2_EN_TABLES[tableName];
  Object.keys(table).forEach((cnKey) => {
    const enKey = table[cnKey];
    EN_2_CN_TABLES[tableName][enKey] = cnKey;
    DEFAULT_SEARCH_PARAMS[tableName][enKey] = null;
  });
});
 
const CN_2_EN_MIXED = {
  ...CN_2_EN_TABLES.TABLE1,
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
    { label: "井类型", field: "Well_Config", defaultValue: "" },
  ],
];

 
const getColumns = () =>
  
  
  {
    
    
    
    
    
    
    
    
    
    
    

    const columnMapper = {
      Well_Name_Drill: {
        fixed: "left",
        
        width: 120
      },
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
    };

    const allTableHeaders = Object.keys(CN_2_EN_TABLES.TABLE1).map(
      (key) => CN_2_EN_TABLES.TABLE1[key]
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
