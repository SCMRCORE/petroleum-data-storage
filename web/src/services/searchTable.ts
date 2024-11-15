// 文档： https://k01kbsmr7e.feishu.cn/docx/Ko7vdlx4ToqGtax4gHUcwcnRnZc

import axios, {AxiosResponse} from "axios";
import request from "../utils/request.ts";
import {AddParams, DeleteParams, ModifyParams, SearchParams, StatusResponse,} from "./types.ts";
import {checkDataSourceTable, DATA_SOURCE_TABLE,} from "../utils/checkDataSource.ts";
import {DEFAULT_SEARCH_PARAMS} from "../pages/DataManagement/components/SearchTable/configs.tsx";

export const search = async (params: SearchParams) => {
  const jsParams = { ...DEFAULT_SEARCH_PARAMS.JS, ...params };
  const fzParams = { ...DEFAULT_SEARCH_PARAMS.FZ, ...params };
  const ztParams = { ...DEFAULT_SEARCH_PARAMS.ZT, ...params };
  const jbParams = { ...DEFAULT_SEARCH_PARAMS.JB, ...params };

  const promiseList = [
    // 严格按照枚举顺序
    request.post("/petroleum/searchJB", jbParams),
    request.post("/petroleum/searchJS", jsParams),
    request.post("/petroleum/searchFZ", fzParams),
    request.post("/petroleum/searchZT", ztParams),
  ];

  const resList = await Promise.all(promiseList);
  const list = [];
  let total = 0;
  resList?.forEach((resItem) => {
    list.push(...(resItem?.data?.data?.records ?? []));
    total = Math.max(resItem?.data?.data?.total, total);
  });
  // console.log("search res", resList);
  const result = {
    [DATA_SOURCE_TABLE[DATA_SOURCE_TABLE.ALL]]: { list, total },
  };
  resList?.forEach((item, index) => {
    const key = DATA_SOURCE_TABLE[index + 1]; // 0 是全部
    const data = item?.data?.data;
    result[key] = {
      list: data?.records,
      total: data?.total ?? 0,
    };
  });
  return result;
};

export const add = async (
  fileList: AddParams
): Promise<Array<AxiosResponse<StatusResponse>>> => {
  const sampleFile = fileList?.[0] ?? [];
  const sampleFileRow = sampleFile?.[0] ?? {};
  const keys = Object.keys(sampleFileRow);
  console.log("????????", sampleFileRow, keys);
  const dataSourceTable = checkDataSourceTable(keys);
  if (dataSourceTable) {
    const pathMap = {
      [DATA_SOURCE_TABLE.FZ]: "addfz",
      [DATA_SOURCE_TABLE.JS]: "addjs",
      [DATA_SOURCE_TABLE.JB]: "addjb",
      [DATA_SOURCE_TABLE.ZT]: "addzt",
    };
    const path = pathMap[dataSourceTable];
    const promiseList = fileList.map((file) => {
      return request.post(`/petroleum/${path}`, file);
    });
    const res = await Promise.all(promiseList);
    console.log(res);
    return res;
  } else {
    throw "表头字段不匹配 或 数据为空";
  }
};

export const deleteItem = async (
  params: DeleteParams
): Promise<AxiosResponse<StatusResponse>> => {
  const res = await request.put("/petroleum/delete", params);
  return res;
};

export const updateItem = async (params: ModifyParams) => {
  const { num, rowData, onlyKey } = params;
  const configMap = {
    [DATA_SOURCE_TABLE.FZ]: {
      path: "setFZ",
      extraParams: DEFAULT_SEARCH_PARAMS.FZ,
    },
    [DATA_SOURCE_TABLE.JS]: {
      path: "setJS",
      extraParams: DEFAULT_SEARCH_PARAMS.JS,
    },
    [DATA_SOURCE_TABLE.JB]: {
      path: "setJB",
      extraParams: DEFAULT_SEARCH_PARAMS.JB,
    },
    [DATA_SOURCE_TABLE.ZT]: {
      path: "setZT",
      extraParams: DEFAULT_SEARCH_PARAMS.ZT,
    },
  };

  const { path, extraParams } = configMap[num];

  const res = await request.put(`/petroleum/${path}?OnlyKey=${onlyKey}`, {
    ...extraParams,
    ...rowData,
  });
  return res;
};

/** 只支持单个上传，若要上传list，就多次调用 */
export const uploadWordFile = async (file, wellName) => {

  const formData = new FormData();
  console.log("file11", file);
  formData.append("word", file.originFile);
  // 加入井名参数
  formData.append("wellName", wellName)
  const res = await axios({
    method: "post",
    // TODO 这里的请求写死，部署时记得改
    url: "http://localhost:8080/file/uploadWG",
    headers: {
      "Content-Type": "multipart/form-data",
    },
    data: formData,
  });
  return res;
};

/** 查询数据湖数据 */
export const dataLakeSearch = async (props) => {
  const {index,  pageIndex, pageSize, ...rest } = props;
  const params = {
    index,
    pageIndex,
    pageSize,
    jsonObj: rest,
  };
  const res = await request.post("/data/searchData?index=" + index, params);
  console.log("得到的回应", res)
  return {
      total: res.data.total,
      list: res.data.data
  }
  //
  // const mockRes = {
  //   total: 100,
  //   list: [{ SPM4: "这是MOCK的，记得替换为真实接口" }],
  // };
  // return mockRes;
};

/** 查询文件 */
export const fileSearch = async (params) => {
  const res =  await request.post("/file/search", params);
  return {
    total: res.data.data.total,
    list: res.data.data.records
  }
}

  // TODO: 这是mock的异步请求，记得替换为真实请求
//   const mockRes = {
//     total: 100,
//     list: [
//       {
//         wellName: "这是mock的文件",
//         fileName:
//           "http://web-core.oss-cn-beijing.aliyuncs.com/396c2df4-e35e-47aa-a68c-cd8e994acb28.doc",
//         uploadTime: "123",
//       },
//     ],
//   };
//   return mockRes;
// };
