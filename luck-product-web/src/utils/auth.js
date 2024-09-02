import {TOKEN_KEY} from "@/config/app";

/**
 * 获取token
 * @returns {string}
 */
export function getToken() {
  const token = localStorage.getItem(TOKEN_KEY);
  if (!token) {
    return sessionStorage.getItem(TOKEN_KEY);
  }
  return token;
}

/**
 * 设置token
 * @param token
 * @param remember
 */
export function setToken(token, remember) {
  removeToken();
  if (token) {
    if (remember) {
      localStorage.setItem(TOKEN_KEY, token);
    } else {
      sessionStorage.setItem(TOKEN_KEY, token);
    }
  }
}

/**
 * 移除 token
 */
export function removeToken() {
  localStorage.removeItem(TOKEN_KEY);
  sessionStorage.removeItem(TOKEN_KEY);
}
