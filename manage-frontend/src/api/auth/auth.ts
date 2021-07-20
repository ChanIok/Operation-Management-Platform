import api from '..';

/**
 * 用户表
 */
interface IUser {
  username: string;
  password: string;
}

/**
 * 登录请求
 * @param user
 * @returns
 */
export async function _login(user: IUser) {
  return api({ method: 'POST', url: '/auth/management-login', data: user });
}

/**
 * 注册请求
 * @param user 
 * @returns 
 */
export async function _regist(user: IUser) {
  return api({ method: 'POST', url: '/auth/register', data: user });
}
