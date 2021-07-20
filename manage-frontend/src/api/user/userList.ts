import api from '..';


interface ILimit {
    page_num_start: number,
    page_num_end: number,
}

export function _getUserList(limitDto: ILimit) {
    return api({
        method: 'POST',
        url: '/user/find',
        data: limitDto
    });
}
