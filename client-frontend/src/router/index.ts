import * as VueRouter from 'vue-router';
import auth from '../views/Auth.vue';
import index from '../views/Index.vue';
import PersonalData from '../components/modules/employee-info/PersonalData.vue';
import Presentation from '../components/modules/presentation/Presentation.vue';
import ShoppingMall from '../components/modules/mall/ShoppingMall.vue';
import Product from '../components/modules/mall/Product.vue';
import PersonalCenter from '../components/modules/user/PersonalCenter.vue';
import Supports from '../components/modules/supports/Supports.vue';
const routes = [
  {
    path: '/',
    redirect: '/presentation',
    component: index,
    children: [
      {
        path: 'employee-info/personal-data',
        component: PersonalData,
      },
      {
        path: 'presentation',
        component: Presentation,
      },
      {
        path: 'mall/:id?',
        component: ShoppingMall,
      },
      {
        path: 'product/:id?',
        component: Product,
      },
      {
        path: 'supports',
        component: Supports,
      },
      {
        path: 'user',
        component: PersonalCenter,
      },
      {
        path: 'login',
        component: auth,
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
