import api from '..';


interface ILimit {
    page_num_start: number,
    page_num_end: number,
}

//获取交易历史
export async function _getTrHistory(limitDto:ILimit) {
    return api({ method: 'POST', url: '/transaction/findAllTrHistory' ,data:limitDto});
}