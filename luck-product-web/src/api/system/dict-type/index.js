import request from "@/utils/request";


/**
 * 分页字典类型
 * @param params 查询条件
 */
export async function pageDictType(params) {
    return await request.post('/sys/dictType/page', params);
}
