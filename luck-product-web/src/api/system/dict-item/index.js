import request from "@/utils/request";


/**
 * 分页查询字典项列表
 * @param params 查询条件
 */
export async function pageDictItem(params) {
    return await request.post('/sys/dictItem/page', params);
}

/**
 * 查询字典项列表
 * @param params 查询条件
 */
export async function getDictItems(params) {
    return await request.post('/sys/dictItem/getDictItems', params);
}