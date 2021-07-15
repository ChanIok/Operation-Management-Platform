import api from '..';

// 用户信息
interface IUserInfo {
  phone_num: string;
  email: string;
}

export function _hello() {
  return api({
    method: 'GET',
    url: '/user/hello',
  });
}

// 获取用户信息
export async function _getUserInfo() {
  return api({ method: 'GET', url: '/user/info' });
}

// 获取用户余额
export async function _getUserBalance() {
  return api({ method: 'GET', url: '/user/balance' });
}

// 更新用户信息
export async function _updateUserInfo(userInfoDto:IUserInfo) {
  return api({ method: 'POST', url: '/user/info' ,data:userInfoDto});
}
