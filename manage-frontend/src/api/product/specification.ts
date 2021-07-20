import api from '..';

interface ILimit {
  page_num_start: number;
  page_num_end: number;
}

interface ISpecification {
  product_id: number;
  specification_id: number;
  price: number;
  specification_type: string;
  introduction: string;
  specification_name: string;
}

//获取配置
export async function _getSpecifications(limitDto: ILimit) {
  return api({
    method: 'POST',
    url: '/management/specifications',
    data: limitDto,
  });
}

// 更新配置
export async function _updateSpecification(productDto: ISpecification) {
  return api({
    method: 'POST',
    url: '/management/specifications/update',
    data: productDto,
  });
}

// 添加配置
export async function _createSpecification(productDto: ISpecification) {
  return api({
    method: 'POST',
    url: '/management/specifications/create',
    data: productDto,
  });
}

// 删除配置
export async function _deleteSpecification(productDto: ISpecification) {
  return api({
    method: 'POST',
    url: '/management/specifications/destroy',
    data: productDto,
  });
}
