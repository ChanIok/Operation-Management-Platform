import api from '..';

interface ILimit {
  page_num_start: number;
  page_num_end: number;
}

//获取体验历史
export async function _getExHistory(limitDto: ILimit) {
  return api({
    method: 'POST',
    url: '/history/trial',
    data: limitDto,
  });
}
