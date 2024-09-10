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
  const mockParams1 = {
    pageIndex: 1, // 默认第一页
    pageSize: 10, // 默认每页显示10条记录
    wellName: "YC13", // 对于JingShenSearchPageDTO
    primaryWellType: "调整井", // 对于JingShenSearchPageDTO
    wellType: "大位移井", // 对于JingShenSearchPageDTO 和 FuZaSearchPageDTO
  };
  const mockParams2 = {
    pageIndex: 1, // 默认第一页
    pageSize: 10, // 默认每页显示10条记录,
    oilFieldName: "1",
    contractor: "2",
    wellName: "大位移井",
    // primaryWellType: "调整井", // 对于JingShenSearchPageDTO
  };
  const mockParams3 = {
    pageIndex: 1, // 默认第一页
    pageSize: 10, // 默认每页显示10条记录,
    wellName: "1",
    // contractor: "2",
    primaryWellType: "调整井", // 对于JingShenSearchPageDTO
    wellType: "大位移井",
  };
  const mockParams4 = {
    pageIndex: 1, // 默认第一页
    pageSize: 10, // 默认每页显示10条记录,
    wellName: "1",
    // contractor: "2",
    primaryWellType: "调整井", // 对于JingShenSearchPageDTO
    wellType: "大位移井",
  };
  // 如果传入的params有值，则覆盖默认值
  // Object.assign(mockParams, params);
  const promiseList = [
    request.get("/petroleum/searchJS", mockParams1),
    request.get("/petroleum/searchJB", mockParams2),
    request.get("/petroleum/searchFZ", mockParams3),
    request.get("/petroleum/searchZT", mockParams4),
  ];

  return Promise.all(promiseList);
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
