// 文档： https://k01kbsmr7e.feishu.cn/docx/Ko7vdlx4ToqGtax4gHUcwcnRnZc

import { AxiosResponse } from "axios";
import request from "../utils/request.ts";
import {
  SearchParams,
  AddParams,
  StatusResponse,
  UploadParams,
  DeleteParams,
  ModifyParams,
} from "./types.ts";

export const search = async (params: SearchParams) => {
  const jsParams = { ...params };
  const fzParams = { ...params };
  const ztParams = { ...params };
  const jbParams = { ...params };

  const promiseList = [
    request.get("/petroleum/searchJS", jsParams),
    request.get("/petroleum/searchJB", jbParams),
    request.get("/petroleum/searchFZ", fzParams),
    request.get("/petroleum/searchZT", ztParams),
  ];

  const resList = await Promise.all(promiseList);
  const list = [];
  let total = 0;
  resList?.forEach((resItem) => {
    list.push(...(resItem?.data?.data?.records ?? []));
    total = Math.max(resItem?.data?.data?.total, total);
  });
  // console.log("search res", dataList);
  return { list, total };
};

export const add = async (
  params: AddParams
): Promise<AxiosResponse<StatusResponse>> => {
  // TODO: const mockParams = {...}
  return request.post("/add", params);
};

export const upload = async (
  params: UploadParams
): Promise<AxiosResponse<StatusResponse>> => {
  // TODO: const mockParams = {...}
  return request.put("/upload", params.data);
};

export const deleteItem = async (
  params: DeleteParams
): Promise<AxiosResponse<StatusResponse>> => {
  // TODO: const mockParams = {...}
  return request.post("/delete", params);
};

export const modify = async (
  params: ModifyParams
): Promise<AxiosResponse<StatusResponse>> => {
  // TODO: const mockParams = {...}
  return request.post("/set", params);
};
