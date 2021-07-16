import api from '..';

//  获取所有体验
export async function _getTrials() {
  return api({ method: 'GET', url: '/trial/products' });
}

// 获取单个产品详情
export async function _getProductDetails(id: string) {
  return api({ method: 'GET', url: `/trial/products/${id}` });
}


// 领取体验产品
export async function _getProductSettlement(productId: string,specificationId:string) {
  return api({ method: 'GET', url: `/trial/products/${productId}/${specificationId}/settlement` });
}

