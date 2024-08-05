import axios from 'axios'
import { getToken } from '@/utils/auth'
import { Message } from "element-ui";

// 创建axios实例
const request= axios.create({
    //baseURL: process.env.NODE_ENV === 'prod' ? process.env.VUE_APP_BASE_API : '/', // api 的 base_url
    baseURL: 'http://localhost:9090/',
    timeout: 12000 // 请求超时时间
})

// request请求拦截器
request.interceptors.request.use(
    config => {
        if (getToken()) {
            config.headers['Authorization'] = 'Bearer ' + getToken() // 让每个请求携带自定义token 请根据实际情况自行修改
        }
        return config
    },
    error => {
        // Do something with request error
        // for debug
        Promise.reject(error)
    }
)

// response 响应拦截器
request.interceptors.response.use(
    /**
     * If you want to get http information such as headers or status
     * Please return  response => response
     */

    /**
     * Determine the request status by custom code
     * Here is just an example
     * You can also judge the status by HTTP Status Code
     */
    response => {
        return response.data
    },
    error => {
        if (error.code === 'ECONNABORTED') {
            Message({
                message: '接口超时未响应',
                duration: 1000,
                forbidClick: true
            })
            return Promise.reject(error)
        }
        if (error.response.status === 401) {
            Message({
                message: '您的登录已失效',
                duration: 1000,
                forbidClick: true
            })
        } else {
            let msg = error.message
            if (error.response) {
                const { data } = error.response
                msg = data.message
            }
            Message({
                message: msg,
                duration: 1000,
                type: 'error'
            })
        }
        return Promise.reject(error)
    }
)
export default request
