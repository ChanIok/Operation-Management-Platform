import api from '..';

interface TrHistory_data {
    rade_tr_id: string,
    user_id: number,
    user_name: string,
    produce_name: string,
    specification_name: string,
    price: number,
    num: number,
    total_price: number,
    tramsaction_time: string,
}

//获取交易历史
export async function _getTrHistory() {
    return api({ method: 'GET', url: '/transaction/findAllTrHistory' });
}