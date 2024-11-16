import { Button, Message, Popconfirm } from "@arco-design/web-react";
import {
  checkDataSourceTable,
  DATA_SOURCE_TABLE,
  groupTableHeaderKeys,
} from "../../../../utils/checkDataSource.ts";
import { DeleteParams } from "../../../../services/types.ts";
import { deleteItem } from "../../../../services/searchTable.ts";

export enum TableMode {
  CASE1 = 0,
}

// TODO: 记得改下字段
const CN_2_EN_TABLES = {
  CASE1: {
    井名: "wellName",
    文件: "path",
    上传时间: "uploadTime",
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
    // 这个文件名的搜索，就只需要使用井名搜索
    // { label: "井基本类型", field: "primaryWellType", defaultValue: "" },
    { label: "井名", field: "wellName", defaultValue: "" },
    // { label: "井类型", field: "wellType", defaultValue: "" },
  ],
  [],
];

/** 用于单独配置有些特别的字段的样式、交互 */
const getColumns = (
  handleSearch: () => void
  // handleDonw: (fileUrl: string) => void
) =>
  // handleEdit: (v: Partial<MixedItem>) => void
  {
    //
    const handleDeleteItem = async (params: DeleteParams) => {
      // TODO: 还要写一个删除接口, 删除之后用handleSearch刷新
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

    const handleDownload = (url) => {
      window.open(url);
    };

    const columnMapper = {
      //  wellName: {
      //       fixed: "left",
      //     },
      // location: {
      //   width: 200,
      // },
      // operationDescription: {
      //   width: 600,
      // },
      操作: {
        width: 80,
        fixed: "right",
        render: (_, row) => {
          return (
            <div
              key={"operatinons" + row.onlyKey}
              className="flex flex-col items-center justify-center gap-2"
            >
              <Button
                size="mini"
                type="primary"
                onClick={() => handleDownload(row.filePath)}
              >
                下载
              </Button>
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
            </div>
          );
        },
      },
    };

    const allTableHeaders = Object.keys(CN_2_EN_TABLES.CASE1).map(
      (key) => CN_2_EN_TABLES.CASE1[key]
    );

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
