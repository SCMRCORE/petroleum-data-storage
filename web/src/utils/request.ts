import axios, { AxiosResponse, InternalAxiosRequestConfig } from "axios";
import { isDevEnv } from "./env.js";

enum PROXY_MODEL {
  NONE = 0,
  NGINX,
  BFF,
  VITE,
}

// TODO: 需要修改这里
const serverIP = "localhost";
const proxyModel = PROXY_MODEL.VITE as unknown;

const config = {
  baseURL: "/api",
  headers: {
    "Content-Type": "application/json",
  },
};

if (proxyModel === PROXY_MODEL.BFF) {
  Reflect.set(config, "baseURL", `${serverIP}:2233`);
  Reflect.set(config.headers, "Is-BFF-Cute", `${isDevEnv}`);
}

const instance = axios.create(config);

instance.interceptors.request.use(
  (config: InternalAxiosRequestConfig) => {
    return config;
  },
  (error: Error) => {
    return Promise.reject(error);
  }
);

instance.interceptors.response.use(
  (response: AxiosResponse) => {
    return response;
  },
  (error: Error) => {
    return Promise.reject(error);
  }
);

const request = {
  get: (url: string, data = {}) => {
    return instance({
      method: "GET",
      url,
      params: data,
    });
  },

  post: (url: string, data: any = {}, params: any = {}) => {
    return instance({
      method: "POST",
      url,
      data,
      params,
    });
  },

  put: (url: string, data = {}) => {
    return instance({
      method: "PUT",
      url,
      data,
    });
  },
};

export default request;
