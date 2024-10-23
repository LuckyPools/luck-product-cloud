import axios from 'axios'
import {getToken} from "@/utils/auth";
import { message } from 'ant-design-vue';

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
    if (code !== 200) {
        console.log(msg)
    }
    return response
}, error => {
    return Promise.reject(error)
})

function dealAxiosResult(res) {
    let realRes = res.data ? res.data : res;
    // 业务异常处理
    if (realRes.code === 500) {
        let msg = realRes.message;
        let errorCode = realRes.errorCode;
        if (errorCode) {
            msg =
                '请把以下错误编码发送给系统管理员以便快速帮您处理</br>错误编码：<span id="a-error-code">' +
                errorCode +
                '</span>';
        }
        // todo 复制异常编码
        message.error(msg);
        return Promise.reject(realRes);
    } else {
        // 文件下载，直接返回整个response对象
        if (res.request?.responseType === 'blob') {
            return Promise.resolve(res);
        }
        return Promise.resolve(realRes);
    }
}

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
    let res = await request.post(url, param, config);
    return dealAxiosResult(res);
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
    let res = await request.get(url, config);
    return dealAxiosResult(res);
}

export default {
    default: request,
    ...request,
    post,
    get
};
