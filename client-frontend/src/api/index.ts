import axios, { AxiosRequestConfig, AxiosResponse } from 'axios';

interface IResponse {
  code: number;
  data: any;
}

const baseURL: string = 'http://localhost:8080/';

const api = axios.create({
  baseURL,
  timeout: 8000,
});

// 请求拦截
api.interceptors.request.use((req: AxiosRequestConfig) => {
  const token: string = localStorage.getItem('token') as string;
  req.headers['token'] = `${token}`;
  return req;
});

// 响应拦截
api.interceptors.response.use(
  (res: AxiosResponse) => {
    return res.data;
  },
  (err) => {
    console.log(err);
  },
);

export default api;
