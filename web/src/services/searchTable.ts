// 文档： https://k01kbsmr7e.feishu.cn/docx/Ko7vdlx4ToqGtax4gHUcwcnRnZc

import { AxiosResponse } from "axios";
import request from "../utils/request.ts";
import { SearchParams, SearchResult, AddParams, StatusResponse, UploadParams, DeleteParams, ModifyParams } from "./types.ts";

export const search = async <T>(params: SearchParams): Promise<AxiosResponse<SearchResult<T>>> => {
  return request.post('/search', params);
};

export const add = async (params: AddParams): Promise<AxiosResponse<StatusResponse>> => {
  return request.post('/add', params);
};

export const upload = async (params: UploadParams): Promise<AxiosResponse<StatusResponse>> => {
  return request.put('/upload', params.data);
};

export const deleteItem = async (params: DeleteParams): Promise<AxiosResponse<StatusResponse>> => {
  return request.post('/delete', params);
};

export const modify = async (params: ModifyParams): Promise<AxiosResponse<StatusResponse>> => {
  return request.post('/set', params);
};