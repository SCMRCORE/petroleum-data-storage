// import { Button, Message, Popconfirm } from "@arco-design/web-react";
// import { MixedItem } from "../../../../types/index.ts";
import {
  // checkDataSourceTable,
  DATA_SOURCE_TABLE,
  groupTableHeaderKeys,
} from "../../../../utils/checkDataSource.ts";
// import { DeleteParams } from "../../../../services/types.ts";
// import { deleteItem } from "../../../../services/searchTable.ts";

export enum TableMode {
  CASE1 = 0,
}

const CN_2_EN_TABLES = {
  // 作业井基本信息表
  TABLE1: {
    "井名（井标识）": "Well_Name_Drill",
    "井型": "Well_Config",
    "中文井名": "Chinese_Well_Name",
    "一级井别": "Well_Type",
    "二级井别": "Well_Sub_Type",
    "三级井别": "well_tertiary_type",
    "风险井类别": "risk_class",
    "设计井深": "Depth_Design",
    "设计垂深": "Tvd_Design",
    "开钻时间": "Spud_Time",
    "完钻时间": "finish_Drill_Time",
    "钻井完成时间": "complete_Drill_Time",
    "区域构造位置": "Location",
    "地面海拔": "ground_elv",
    "水深": "water_depth",
    "初始补心海拔": "Orig_Elv_KB",
    "当前补心海拔": "Recent_Elv_KB",
    "槽口号": "Slot",
    "主要目的地层": "Main_Form_action",
    "井控事件次数": "Well_Control",
    // "钻完井专业井名": "Well_Name_Drill",
    "所属分公司": "branch_id",
    "承包商": "Contrator",
    "作业者": "Operator",
    "自营/合作": "Is_Cooperation",
  },
  TABLE2: {
    // TODO 更改京表示
    "井名（井标识）": "Well_Name_Drill",
    "测深": "Survey_MD",
    "垂深": "TVD",
    "井斜": "Incl",
    "方位": "Azimuth",
    "方位变化率": "Turn_Rate",
    "全角变化率": "DLS",
    "造斜率": "Build_Rate",
    "水平投影位移": "VS",
    "南北位移": "NS",
    "东西位移": "EW",
    "闭合方位": "Closure_Azimuth",
    "闭合距": "Departure",
    "测斜公司": "Survey_Company",
    "所属分公司": "branch_id"
  },
  TABLE3: {
    "井名（井标识）": "Well_Name_Drill",
    "钻头类型": "Bit_Type",
    "钻头型号": "Model",
    "钻头标识": "Drill_Bit_ID",
    "钻头编号": "Bit_No",
    "钻头尺寸": "Bit_Size",
    "钻头长度": "Bit_Length",
    "接头扣型": "Connection_Thread",
    "保径长度": "Bit_Gauge_Length",
    "IADC代码": "IADCCode",
    "待钻井眼尺寸": "Sz_ODPass",
    "入井前已转时间": "Drilled_Before_Time",
    "入井前进尺": "Drilled_Before_Footage",
    "钻头厂家": "Factory",
    "所属分公司": "branch_id"
  },
  // 钻遇地层表
  TABLE4: {
    "钻遇地层标识": "Formation_ID",
    "层位名称": "LayerName",
    "坍塌压力": "CollapsePressure",
    "孔隙压力": "PorePressure",
    "地层温度": "Temperature",
    "岩性": "Lithology",
    "最终顶深": "FinalTopDepth",
    "钻遇底深": "DrillingBtmDepth",
    "H2S浓度": "H2SConc",
    "沥青浓度": "BitumenWeight",
    "渗透率（H90）": "Kh90",
    "垂直渗透率": "Kv",
    "最大渗透率": "Kmax",
    "所属分公司": "branch_id"
  },
  TABLE5: {
    "井名（井标识）": "Well_Name_Drill",
    "井深": "Depth",
    "3转读数": "value_3rpm",
    "6转读数": "Vis6rpm",
    "30转读数": "Vis30rpm",
    "60转读数": "Vis60rpm",
    "100转读数": "Vis100rpm",
    "200转读数": "Vis200rpm",
    "300转读数": "Vis300rpm",
    "600转读数": "Vis600rpm",
    "10分钟静切力": "gel30min",
    "k值": "KOver_Ride",
    "屈服值": "yield_value",
    "塑性粘度": "Plastic_Vis",
    "漏斗粘度": "funnel_visc",
    "PH值": "PH",
    "API失水": "APILoss",
    "地面损耗量": "Mud_Lost_Surface",
    "循环泥浆体积有效计算": "Mud_Vol_Active",
    "储备池泥浆量": "Mud_Vol_Surf_Active",
    "重晶石": "Barite",
    "聚膜钻井液用可降解成膜剂（CLMH-?）含量": "CLMHI_percent",
    "所属分公司": "branch_id"
  },
  TABLE6: { // 钻井时间表
    "井名（井标识）": "Well_Name_Drill",
    "时间索引": "DATE_TIME_INDEX", // index
    "测量井深": "DMEA",
    "垂直井深": "DVER",
    "钻井液总池体积": "TVA",
    "入口钻井液温度": "MTIA",
    "出口钻井液温度": "MTOA",
    "入口密度": "MDIA",
    "出口密度": "MDOA",
    "入口流量": "MFIA",
    "返出流量": "MFOA",
    "钻压": "WOBA",
    "钻压（最大值）": "WOBX",
    "悬重": "HKLA",
    "悬重（最大值）": "HKLX",
    "扭矩": "TQA",
    "扭矩（最大值）": "TQX",
    "机械钻速": "ROPA",
    "转速": "RPMA",
    "泵压": "SPPA",
    "大钩速度": "SPR5",
    "钻头垂深": "DBTM",
    "气全量": "GASA",
    "累计钻头进尺": "SPR3",
    "所属分公司": "branch_id"
  },
  TABLE7: {  // 钻井深度表
    "井名（井标识）": "Well_Name_Drill", // index
    "测量井深": "DMEA", // index
    "垂直井深": "DVER",
    "钻井液总池体积": "TVA",
    "当量循环密度": "ECD",
    "入口密度": "MDIA",
    "出口密度": "MDOA",
    "入口流量": "MFIA",
    "返出流量": "MFOA",
    "钻压": "WOBA",
    "悬重": "HKLA",
    "扭矩": "TQA",
    "机械钻速": "ROPA",
    "转速": "RPMA",
    "泵压": "SPPA",
    "DC指数": "DATA15",
    "累计钻头进尺": "SPR3",
    "累计纯钻进时间": "SPR1",
  },
};

const DEFAULT_SEARCH_PARAMS = { TABLE1: {}, TABLE2:{}, TABLE3:{}, TABLE4:{}, TABLE5:{}, TABLE6:{}, TABLE7:{}};
const EN_2_CN_TABLES =  { TABLE1: {}, TABLE2:{}, TABLE3:{}, TABLE4:{}, TABLE5:{}, TABLE6:{}, TABLE7:{}};
Object.keys(CN_2_EN_TABLES).forEach((tableName) => {
  const table = CN_2_EN_TABLES[tableName];
  Object.keys(table).forEach((cnKey) => {
    const enKey = table[cnKey];
    EN_2_CN_TABLES[tableName][enKey] = cnKey;
    DEFAULT_SEARCH_PARAMS[tableName][enKey] = null;
  });
});

//  = reverseMapping(CN_2_EN_TABLES);
const CN_2_EN_MIXED = {
  ...CN_2_EN_TABLES.TABLE1,
  ...CN_2_EN_TABLES.TABLE2,
  ...CN_2_EN_TABLES.TABLE3,
  ...CN_2_EN_TABLES.TABLE4,
  ...CN_2_EN_TABLES.TABLE5,
  ...CN_2_EN_TABLES.TABLE6,
  ...CN_2_EN_TABLES.TABLE7,
};
const EN_2_CN_MIXED = {};
Object.keys(CN_2_EN_MIXED).forEach((cn) => {
  const en = (CN_2_EN_MIXED[cn] as string) ?? "";
  EN_2_CN_MIXED[en.toLowerCase()] = cn;
});

console.log("cn2en", CN_2_EN_MIXED);
console.log("en2cn", EN_2_CN_MIXED);

// TODO: 这里是搜索配置项，用于动态生成表单
// 每个二级数组代表一排，比如第0个二级数组代表第1排，第1个代表第2排...
export const formConfigList = [
  [
    // { label: "井基本类型", field: "primaryWellType", defaultValue: "" },
    { label: "井名", field: "Well_Name_Drill", defaultValue: "" },
    { label: "井类型", field: "Well_Config", defaultValue: "" },
  ],
  // [
  //   { label: "井名", field: "Well_Name_Drill", defaultValue: "" },
  // ],
  // [
  //   { label: "井名", field: "Well_Name_Drill", defaultValue: "" },
  // ],
  // [
  //   { label: "井名", field: "Well_Name_Drill", defaultValue: "" },
  // ],
  // [
  //   { label: "井名", field: "Well_Name_Drill", defaultValue: "" },
  //   { label: "时间索引", field: "Date_Time_Index", defaultValue: "" },
  // ],
  // [
  //   { label: "井名", field: "Well_Name_Drill", defaultValue: "" },
  // ],
  // [
  //   { label: "测量井深", field: "DMEA", defaultValue: "" },
  //   { label: "井名", field: "Well_Name_Drill", defaultValue: "" },
  // ],
];

/** 用于单独配置有些特别的字段的样式、交互 */
const getColumns = () =>
  // handleSearch: () => void
  // handleEdit: (v: Partial<MixedItem>) => void
  {
    // const handleDeleteItem = async (params: DeleteParams) => {
    //   const res = await deleteItem(params);
    //   console.log("res", res);
    //   if (res?.data?.code === 1) {
    //     Message.info("删除成功");
    //     console.log("删除成功");
    //     handleSearch();
    //   } else {
    //     Message.info("删除失败");
    //   }
    // };

    const columnMapper = {
      Well_Name_Drill: {
        fixed: "left",
        // width:
      },
      // location: {
      //   width: 200,
      // },
      // operationDescription: {
      //   width: 600,
      // },
      // 操作: {
      //   width: 80,
      //   fixed: "right",
      //   render: (_, row: MixedItem) => {
      //     return (
      //       <div
      //         key={"operatinons" + row.onlyKey}
      //         className="flex flex-col items-center justify-center gap-2"
      //       >
      //         <Button size="mini" onClick={() => handleEdit(row)}>
      //           修改
      //         </Button>
      //         <Popconfirm
      //           focusLock
      //           title="确定要删除吗？"
      //           okText="确定"
      //           cancelText="取消"
      //           onOk={() => {
      //             const keys = Object.keys(row);
      //             const num = checkDataSourceTable(keys);
      //             console.log("删除", _, row, num);
      //             if (row.onlyKey && num) {
      //               handleDeleteItem({ OnlyKey: row.onlyKey, num });
      //             }
      //           }}
      //         >
      //           <Button type="primary" status="danger" size="mini">
      //             删除
      //           </Button>
      //         </Popconfirm>
      //       </div>
      //     );
      //   },
      // },
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
