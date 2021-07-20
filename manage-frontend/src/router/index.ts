import * as VueRouter from 'vue-router';
import auth from '../views/Auth.vue';
import main from '../views/Main.vue';
import TradeHistory from '../components/modules/trade-history/TradeHistory.vue';
import TrialHistory from '../components/modules/trial-history/TrialHistory.vue';

import ProductList from '../components/modules/product-manage/ProductList.vue';
import ProductManage from '../components/modules/product-manage/ProductManage.vue';

import SpecificationList from '../components/modules/product-manage/SpecificationList.vue';
import SpecificationManage from '../components/modules/product-manage/SpecificationManage.vue';

import ProductWrapper from '../components/modules/product-manage/ProductWrapper.vue';

import merchandiseControler from '../components/modules/employee-info/merchandiseControler.vue';
const routes = [
  { path: '/', component: auth },
  {
    path: '/index',
    component: main,
    children: [
      {
        path: 'trade-history',
        component: TradeHistory,
      },
      {
        path: 'trial-history',
        component: TrialHistory,
      },
      {
        path: 'merchandise-controler',
        component: merchandiseControler,
      },

      {
        path: 'products',
        component: ProductWrapper,
        children: [
          {
            path: 'list',
            component: ProductList,
          },
          {
            path: 'manage',
            component: ProductManage,
          },
          {
            path: 'specification/list',
            component: SpecificationList,
          },
          {
            path: 'specification/manage',
            component: SpecificationManage,
          },
        ],
      },
    ],
  },
];

const router = VueRouter.createRouter({
  //  hash 模式。
  history: VueRouter.createWebHashHistory(),
  routes, // `routes: routes` 的缩写
});

export default router;
