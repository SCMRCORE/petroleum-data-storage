import { Button, Message, Popconfirm } from "@arco-design/web-react";
import {
  checkDataSourceTable,
  DATA_SOURCE_TABLE,
  groupTableHeaderKeys,
} from "../../../../utils/checkDataSource.ts";
import { DeleteParams } from "../../../../services/types.ts";
import {deleteItem, deleteItemFile} from "../../../../services/searchTable.ts";

export enum TableMode {
  CASE1 = 0,
}
 
const CN_2_EN_TABLES = {
  CASE1: {
    井名: "wellName",
    文件名: "fileName",
    上传时间: "uploadTime",
    下载地址: "url"
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
    
    
    { label: "井名", field: "wellName", defaultValue: "" },
    
  ],
  [],
];

 
const getColumns = (
  handleSearch: () => void
  
) =>
  
  {
    
    const handleDeleteItem = async (params: { num: number; url: any }) => {
      
      console.log("文件删除参数！", params);
      const res = await deleteItemFile(params);
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
      console.log(url)
      window.open(url);
    };

    const columnMapper = {
      
      
      
      
      
      
      
      
      
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
                onClick={() => handleDownload(row.url)}
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
                  const url =
                  console.log("删除", _, row);
                  
                    handleDeleteItem({ url: row.url, num: 0});
                  
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
