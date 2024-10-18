/**
 *  引入公共api封装为Vue.prototype.$api属性
 */
import request from '@/utils/request';
import {getDictItems} from '@/api/system/dict-item';

/**
 * 根据url发送请求获取数据
 * @param method 请求方法类型get/post
 * @param url 请求路径
 * @param params 请求参数
 * @param headers 请求头
 * @returns {Promise<AxiosResponse<T>>}
 */
function httpRequest(method, url, params, headers) {
  if (method.toLowerCase() === 'get') {
    return  request.default.get(url,headers);
  } else if (method.toLowerCase() === 'post') {
    return  request.default.post(url, params, headers);
  } else {
    return Promise.reject(new Error('无效请求类型'));
  }
}

/**
 * 字典数据源
 * @param param
 * @returns {*}
 */
function dictDataSource(param){
  return getDictItems(param)
}

export default {
  dictDataSource,
  httpRequest
};
