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
  const promiseList = [
    request.get("/petroleum/searchJS", params),
    request.get("/petroleum/searchJB", params),
    request.get("/petroleum/searchFZ", params),
  ];

  return Promise.all(promiseList);
};

export const add = async (
  params: AddParams
): Promise<AxiosResponse<StatusResponse>> => {
  return request.post("/add", params);
};

export const upload = async (
  params: UploadParams
): Promise<AxiosResponse<StatusResponse>> => {
  return request.put("/upload", params.data);
};

export const deleteItem = async (
  params: DeleteParams
): Promise<AxiosResponse<StatusResponse>> => {
  return request.post("/delete", params);
};

export const modify = async (
  params: ModifyParams
): Promise<AxiosResponse<StatusResponse>> => {
  return request.post("/set", params);
};
