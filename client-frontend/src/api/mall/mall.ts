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

// 购物车单项商品详情
interface IProductInTrolley {
  product_id: number;
  specification_id: number;
  buy_count: number;
}

// 购物车
interface ITrolley {
  trolley: IProductInTrolley[];
}

//  获取所有产品
export async function _getProducts() {
  return api({ method: 'GET', url: '/mall/products' });
}

// 获取单个产品详情
export async function _getProductDetails(id: string) {
  return api({ method: 'GET', url: `/mall/products/${id}` });
}

// 更新购物车
export async function _updateShoppingCart(torlleyDto: ITrolley) {
  return api({ method: 'POST', url: '/mall/cart', data: torlleyDto });
}

// 获取购物车详情
export async function _getShoppingCart() {
  return api({ method: 'GET', url: '/mall/cart' });
}

// 购物车结算
export async function _getSettlement() {
  return api({ method: 'GET', url: '/mall/cart/settlement' });
}
