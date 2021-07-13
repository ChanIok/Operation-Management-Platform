import api from '..';

//  单个产品简略信息
interface IProduct {
  productId: number;
  productName: string;
  introduction: string;
}

//  单类产品
interface IProductsInType {
  type: string;
  list: IProduct[];
}

//  获取所有产品
export async function _getProducts() {
  return api({ method: 'GET', url: '/mall/products' });
}

// 获取单个产品详情
export async function _getProductDetails(id: string) {
  return api({ method: 'GET', url: `/mall/products/${id}` });
}
