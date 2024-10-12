import {$t} from "@/locales";

/**
 * Toggle html class
 *
 * @param className
 */
export function toggleHtmlClass(className) {
    function add() {
        document.documentElement.classList.add(className);
    }

    function remove() {
        document.documentElement.classList.remove(className);
    }

    return {
        add,
        remove
    };
}

/**
 * parentId形式数据转children形式
 * @param option ToTreeDataOption
 */
export function toTreeData(option) {
    // 兼容旧版
    if (Array.isArray(option)) {
        option = {
            data: arguments[0],
            idField: arguments[1],
            parentIdField: arguments[2],
            childrenField: arguments[3],
            parentId: arguments[4],
            addParentIds: arguments[5],
            parentIdsField: arguments[6],
            parentIds: arguments[7]
        };
    }
    const data = option.data;
    const idField = option.idField || option.idKey || 'id';
    const parentIdField = option.parentIdField || option.pidKey || 'parentId';
    const childrenField = option.childrenField || option.childKey || 'children';
    const parentIdIsNull =
        typeof option.parentId === 'undefined' && typeof option.pid === 'undefined';
    const parentId = parentIdIsNull ? [] : option.parentId ?? option.pid;
    const addParentIds = option.addParentIds ?? option.addPIds;
    const parentIdsField =
        option.parentIdsField || option.parentsKey || 'parentIds';
    const parentIds = option.parentIds;
    if (parentIdIsNull) {
        data.forEach((d) => {
            let flag = true;
            for (let i = 0; i < data.length; i++) {
                if (d[parentIdField] === data[i][idField]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                parentId.push(d[parentIdField]);
            }
        });
    }

    const result = [];
    data.forEach((d) => {
        if (d[idField] === d[parentIdField]) {
            throw new Error(
                [
                    'data error: {',
                    idField + ': ',
                    JSON.stringify(d[idField]),
                    parentIdField + ': ',
                    JSON.stringify(d[parentIdField]),
                    '}'
                ].join('')
            );
        }
        const isArr = Array.isArray(parentId);
        const isParent = isArr
            ? parentId.includes(d[parentIdField])
            : d[parentIdField] === parentId;
        if (isParent) {
            const r = { ...d };
            const children = toTreeData({
                data,
                idField,
                parentIdField,
                childrenField,
                parentId: d[idField],
                addParentIds,
                parentIdsField,
                parentIds: (parentIds ?? []).concat([d[idField]])
            });
            if (children.length > 0) {
                r[childrenField] = children;
            }
            if (addParentIds) {
                r[parentIdsField] = parentIds ?? [];
            }
            result.push(r);
        }
    });
    return result;
}


/**
 * 格式化菜单数据
 * @param data 数据
 * @param childField 子级的字段名称
 */
export function formatMenus(data, childField = 'children') {
    let homePath;
    let homeTitle;
    const menus = formatTreeData(
        data,
        (item) => {
            const meta =
                typeof item.meta === 'string'
                    ? JSON.parse(item.meta || '{}')
                    : item.meta;
            const path = meta?.fullPath || item.fullPath || item.path;
            const component = item.component?.startsWith('/')
                ? item.component.substring(1)
                : item.component;
            const menu = {
                path,
                component,
                name: meta?.name,
                redirect: item.redirect,
                meta: {
                    title: item.name,
                    icon: item.icon,
                    color: item.bgColor,
                    hide: item.isShow === '1' ? 0 : 1,
                    active: item.active,
                    breadcrumb: item.breadcrumb,
                    hideFooter: item.hideFooter,
                    hideSidebar: item.hideSidebar,
                    // 同路由不同参数是否只显示一个页签，默认为 true
                    tabUnique: item.isUniqueTab !== '0',
                    closable: item.closable,
                    keepAlive: item.keepAlive,
                    routePath: item.path,
                    ...meta
                }
            };
            const children = item[childField]?.filter(
                (d) => !(d.meta?.hide ?? (d.isShow === '1' ? 0 : 1))
            );
            if (!children?.length) {
                if (!homePath && path && !isExternalLink(path)) {
                    homePath = path;
                    homeTitle = menu.meta.title;
                    menu.meta.isHome = true; // 兼容旧版本
                }
            } else {
                const childPath = children[0].path || children[0].fullPath;
                if (childPath) {
                    if (!menu.redirect) {
                        menu.redirect = childPath;
                    }
                    if (!menu.path) {
                        menu.path = childPath.substring(0, childPath.lastIndexOf('/'));
                    }
                }
            }
            if (!path) {
                console.error('菜单 path 不能为空且要唯一: ', item);
                return;
            }
            return menu;
        },
        childField
    );
    return { menus, homePath, homeTitle };
}


/**
 * 处理children形式数据
 * @param data 需要处理的数据
 * @param formatter 处理器
 * @param childrenField children字段名
 * @param resultChildrenField
 * @param parent
 */
export function formatTreeData(
    data,
    formatter,
    childrenField = 'children',
    resultChildrenField = 'children',
    parent
) {
    const result = [];
    data?.forEach((d, i) => {
        const item = formatter(d, i, parent);
        if (item) {
            if (d[childrenField]?.length) {
                item[resultChildrenField] = formatTreeData(
                    d[childrenField],
                    formatter,
                    childrenField,
                    resultChildrenField,
                    item
                );
            }
            result.push(item);
        }
    });
    return result;
}


/**
 * 判断是否是外链
 * @param url 地址
 */
export function isExternalLink(url) {
    return !!(
        url &&
        (url.startsWith('http://') ||
            url.startsWith('https://') ||
            url.startsWith('//'))
    );
}


/**
 * Translate options
 *
 * @param options
 */
export function translateOptions(options) {
    return options.map(option => ({
        ...option,
        label: $t(option.label)
    }));
}


export function transformRecordToOption(record) {
    return Object.entries(record).map(([value, label]) => ({
        value,
        label
    }));
}


/**
 * 深度克隆对象
 * @param obj 源对象
 * @return 克隆后的对象
 */
export function deepClone(obj) {
    let result;
    if (Array.isArray(obj)) {
        result = [];
    } else if (typeof obj === 'object' && obj !== null) {
        result = {};
    } else {
        return obj;
    }
    Object.keys(obj).forEach((key) => {
        if (typeof obj[key] === 'object' && obj[key] !== null) {
            result[key] = deepClone(obj[key]);
        } else {
            result[key] = obj[key];
        }
    });
    return result;
}

export default {
    toggleHtmlClass,
    toTreeData,
    formatMenus,
    formatTreeData,
    isExternalLink,
    translateOptions,
    transformRecordToOption,
    deepClone
}