import api from '..';

//  单个收藏表
interface IFavor {
  title: string;
  address: string;
  icon: string;
}

// 所有收藏
interface IFavorites {
  userID: string;
  favorites: IFavor[];
}

// userID
interface userID {
  userID: string;
}

//  获取所有收藏
export async function _getFavorites(idDto: userID) {
  return api({ method: 'POST', url: '/favor/show', data: idDto });
}

// 更新收藏
export async function _updateFavorites(favor: IFavorites) {
  return api({ method: 'POST', url: '/favor/update', data: favor });
}
