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
  CASE1: {
    月份: "MONTH",
    "4号泵冲数": "SPM4",
    采集任务标识: "collect_job_id",
    密级: "secrecy_level",
    "2号泵冲数": "SPM2",
    录入时间: "input_time",
    "3号泵冲数": "SPM3",
    "钻压（最大值）": "WOBX",
    录入人: "input_user",
    "1号泵冲数": "SPM1",
    扩展参数13: "DATA13",
    气全量: "GASA",
    扩展参数14: "DATA14",
    扩展参数15: "DATA15",
    入口钻井液电导率: "MCIA",
    扩展参数10: "DATA10",
    出口密度: "MDOA",
    扩展参数11: "DATA11",
    最近更新单位: "update_org",
    作业代码: "ACTC",
    扩展参数12: "DATA12",
    返出流量: "MFOA",
    扩展参数7: "DATA7",
    扩展参数6: "DATA6",
    扩展参数9: "DATA9",
    扩展参数8: "DATA8",
    扩展参数3: "DATA3",
    套压: "CHKP",
    扩展参数2: "DATA2",
    扩展参数5: "DATA5",
    扩展参数4: "DATA4",
    累计钻头进尺: "SPR3",
    悬重: "HKLA",
    瞬时机械钻速: "SPR4",
    累计纯钻进时间: "SPR1",
    扩展参数1: "DATA1",
    迟到时间: "SPR2",
    删除标识: "delete_flag",
    出口钻井液温度: "MTOA",
    迟到泵冲数: "LSTK",
    返出流量比: "MFOP",
    钻头垂深: "DBTV",
    垂直井深: "DVER",
    数据资产编码: "data_asset_code",
    是否提交中心库: "is_submit_centre_db",
    迟到深度: "DRTM",
    自动编号: "GUID",
    泵压: "SPPA",
    序列标识: "SEQID",
    录入单位: "input_org",
    大钩高度: "BPOS",
    最近更新时间: "update_date",
    产生记录时的日期: "DATE",
    油井标识: "WID",
    汇集时间: "uniontime",
    "侧钻/井段号": "SKNO",
    钻压: "WOBA",
    测量井深: "DMEA",
    钻井液净体积变化: "TVCA",
    机械钻速: "ROPA",
    入口密度: "MDIA",
    出口钻井液电导率: "MCOA",
    归档标识: "archive_flag",
    最近更新人: "update_user",
    入口流量: "MFIA",
    所属分公司: "branch_id",
    发布单位: "publish_org",
    入口钻井液温度: "MTIA",
    累计泵冲数: "STKC",
    扭矩1: "TQA",
    大钩速度: "SPR5",
    钻头测量深度: "DBTM",
    产生记录时的时间: "TIME",
    审核时间: "verify_date",
    审核单位: "verify_org",
    记录标识: "RID",
    数据集编码: "dataset_class",
    "悬重（最大值）": "HKLX",
    时间索引: "DATE_TIME_INDEX",
    作业状态: "ACTC2",
    转速: "RPMA",
    审核人: "verifier",
    扭矩2: "TQX",
    数据采集方式: "acqtn_mode",
    质量标志: "quality_tag",
    钻井液池总体积: "TVA",
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

//  = reverseMapping(CN_2_EN_TABLES);
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

// TODO: 这里是搜索配置项，用于动态生成表单
// 每个二级数组代表一排，比如第0个二级数组代表第1排，第1个代表第2排...
export const formConfigList = [
  [
    { label: "井基本类型", field: "primaryWellType", defaultValue: "" },
    { label: "井名", field: "wellName", defaultValue: "" },
    { label: "井类型", field: "wellType", defaultValue: "" },
  ],
  [],
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
      wellName: {
        fixed: "left",
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
