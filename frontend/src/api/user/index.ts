import api from '..';

export function _hello() {
  return api({
    method: 'GET',
    url: '/user/hello',
  });
}
