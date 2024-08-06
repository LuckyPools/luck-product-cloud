import axios from 'axios'

// 新建axios实例
const request = axios.create({
    baseURL: '/api',
    timeout: 3000
})

// 添加请求拦截器
request.interceptors.request.use(config => {
    let token = ''
    if (token) {
        config.headers['token'] = token
    }
    return config
}, error => {
    return Promise.reject(error)
})

// 添加相应拦截器
request.interceptors.response.use(response => {
    let {code, msg} = response.data
    if (code !== 20000) {
        console.log(msg)
    }
    return response
}, error => {
    return Promise.reject(error)
})

export default request
