// 文档： https://k01kbsmr7e.feishu.cn/docx/Ko7vdlx4ToqGtax4gHUcwcnRnZc

import { AxiosResponse } from "axios";
import request from "../utils/request.ts";
import {
  SearchParams,
  AddParams,
  StatusResponse,
  DeleteParams,
} from "./types.ts";
import {
  checkDataSourceTable,
  DATA_SOURCE_TABLE,
} from "../utils/checkDataSource.ts";

export const search = async (params: SearchParams) => {
  const jsParams = { ...params };
  const fzParams = { ...params };
  const ztParams = { ...params };
  const jbParams = { ...params };

  const promiseList = [
    // 严格按照枚举顺序
    request.post("/petroleum/searchJS", jsParams),
    request.post("/petroleum/searchJB", jbParams),
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
