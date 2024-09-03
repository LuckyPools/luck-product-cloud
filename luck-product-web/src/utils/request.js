import axios from 'axios'
import {getToken} from "@/utils/auth";

// 新建axios实例
const request = axios.create({
    baseURL: '/api',
    timeout: 3000
})

// 添加请求拦截器
request.interceptors.request.use(config => {
    let token = getToken();
    if (token) {
        config.headers['Authorization'] = 'Bearer ' + getToken()
    }
    return config
}, error => {
    return Promise.reject(error)
})

// 添加响应拦截器
request.interceptors.response.use(response => {
    let {code, msg} = response.data
    if (code !== 20000) {
        console.log(msg)
    }
    return response
}, error => {
    return Promise.reject(error)
})

/**
 * 项目自定义全局post方法
 * 统一处理接口请求异常
 *
 * @param url
 * @param param
 * @param config
 * @returns {Promise<unknown>}
 */
async function post(url, param = {}, config = {}) {
    return request.post(url, param, config);
}

/**
 * 项目自定义全局get方法
 * 统一处理接口请求异常
 *
 * @param url
 * @param config
 * @returns {Promise<unknown>}
 */
async function get(url, config = {}) {
    return request.get(url, config);
}

export default request
