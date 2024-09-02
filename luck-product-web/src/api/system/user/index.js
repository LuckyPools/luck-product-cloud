import request from "@/utils/request";

/**
 * 获取当前登录的用户信息、菜单、权限、角色
 */
export async function getUserInfo() {
    return await request.get('/sys/user/getUserLoginInfo');
}
