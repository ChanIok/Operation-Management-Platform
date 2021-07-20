import api from '..';

interface ILimit {
  page_num_start: number;
  page_num_end: number;
}

interface IProduct {
  product_id: number;
  product_type: string;
  img_url: string;
  introduction: string;
  product_name: string;
}

//获取交易历史
export async function _getProducts(limitDto: ILimit) {
  return api({
    method: 'POST',
    url: '/management/products',
    data: limitDto,
  });
}

// 更新产品
export async function _updateProduct(productDto: IProduct) {
  return api({
    method: 'POST',
    url: '/management/products/update',
    data: productDto,
  });
}

// 添加产品
export async function _createProduct(productDto: IProduct) {
  return api({
    method: 'POST',
    url: '/management/products/create',
    data: productDto,
  });
}

// 删除产品
export async function _deleteProduct(productDto: IProduct) {
  return api({
    method: 'POST',
    url: '/management/products/destroy',
    data: productDto,
  });
}
