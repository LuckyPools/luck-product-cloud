

/**
 * 前端分页排序
 * @param datasource 数据
 * @param sorter 排序值
 * @param page 页码
 * @param limit 每页数量
 * @param needPage 是否分页
 */
export function reloadData(datasource, sorter, page, limit, needPage) {
    const data = getSortedData(datasource, sorter);
    const total = data.length;
    // 前端分页
    if (needPage && page && limit) {
        const maxPage = Math.ceil(total / limit);
        if (maxPage && page > maxPage) {
            page = maxPage;
        }
        const start = (page - 1) * limit;
        const end = start + limit;
        const list = data.slice(start, end > total ? total : end);
        return { page, data: list, total };
    } else {
        return { page: 1, data, total };
    }
}


/**
 * 前端分页排序时获排序后的全部数据
 * @param datasource 数据
 * @param sorter 排序值
 */
export function getSortedData(datasource, sorter) {
    const data = [...datasource];
    // 前端排序
    getOrderItems(sorter).forEach((item) => {
        data.sort((a, b) => {
            if (typeof item.sortMethod === 'function') {
                const r = item.sortMethod(a, b);
                return item.descend ? -r : r;
            } else {
                const av = a[item.field];
                const bv = b[item.field];
                if (av == bv) {
                    return 0;
                } else if (item.descend) {
                    return av < bv ? 1 : -1;
                } else {
                    return av < bv ? -1 : 1;
                }
            }
        });
    });
    return data;
}


/**
 * 获取封装后的排序值
 * @param sorter ElTable排序值
 */
export function getOrderItems(sorter) {
    const orders = [];
    if (!sorter || !sorter.order) {
        return orders;
    }
    const descend = sorter.order === 'descending';
    const sortMethod = sorter.column?.sortMethod;
    const sortBy = sorter.column?.sortBy;
    if (Array.isArray(sortBy) && sortBy.length) {
        sortBy.forEach((field) => {
            if (typeof field === 'string' && field.length) {
                orders.push({ field, descend, sortMethod });
            }
        });
    } else {
        const field = typeof sortBy === 'string' && sortBy ? sortBy : sorter.prop;
        if (field) {
            orders.push({ field, descend, sortMethod });
        }
    }
    return orders;
}


/**
 * 遍历children形式数据
 * @param data 需要遍历的数据
 * @param callback 回调
 * @param childrenField children字段名
 */
export function eachTreeData(
    data,
    callback,
    childrenField = 'children',
    parent
) {
    if (data) {
        data.forEach((d, i) => {
            if (
                callback &&
                callback(d, i, parent) !== false &&
                d[childrenField]?.length
            ) {
                eachTreeData(d[childrenField], callback, childrenField, d);
            }
        });
    }
}


/**
 * 获取任意数据任意字段的值, 支持多层*.*
 * @param data 数据
 * @param field 字段
 */
export function getFieldValue(data, field) {
    if (typeof field === 'function') {
        return field(data);
    }
    if (field) {
        let value = data;
        field.split('.').forEach((f) => {
            value = value ? value[f] : null;
        });
        return value;
    }
}