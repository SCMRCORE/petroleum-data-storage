// @ts-ignore
import axios from 'axios';

const isDevEnv = ['localhost', '127.0.0.1'].includes(location.hostname) ;

const instance = axios.create({
  baseURL: isDevEnv ? 'http://localhost:3000/api' : '',
  headers: {
    'Content-Type': 'application/json',
  },
});

// 请求拦截器
instance.interceptors.request.use(
  (config: any) => {
    // 在这里可以添加请求前的逻辑，例如添加token等
    return config;
  },
  (error: Error) => {
    // 处理请求错误
    return Promise.reject(error);
  }
);

// 响应拦截器
instance.interceptors.response.use(
  (response: Response) => {
    // 对响应数据做点什么
    return response;
  },
  (error: Error) => {
    // 处理响应错误
    return Promise.reject(error);
  }
);

const request = {
  get: (url:string, data = {}) => {
    return instance({
      method: 'get',
      url,
      params: data,
    });
  },

  post: (url:string, data = {}) => {
    return instance({
      method: 'post',
      url,
      data,
    });
  },

  put: (url:string, data:Record<string, any> = {}) => {
    let formData = new FormData();
    Object.keys(data).forEach((key) => {
      formData.append(key, data[key]);
    });

    return instance({
      method: 'put',
      url,
      data: formData,
      headers: {
        'Content-Type': 'multipart/form-data',
      },
    });
  },
};

export default request;