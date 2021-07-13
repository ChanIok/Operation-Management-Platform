import api from '..';

export function _hello() {
  return api({
    method: 'GET',
    url: '/user/hello',
  });
}


export async function _getUserInfo() {
  return api({ method: 'GET', url: '/user/info' });
}
