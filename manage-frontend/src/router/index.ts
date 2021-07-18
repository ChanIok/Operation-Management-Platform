import * as VueRouter from 'vue-router';
import auth from '../views/Auth.vue';
import main from '../views/Main.vue';
import transactionHistory from '../components/modules/employee-info/transactionHistory.vue';
import giftHistory from '../components/modules/employee-info/giftHistory.vue';
import merchandiseControler from '../components/modules/employee-info/merchandiseControler.vue';
const routes = [
  { path: '/', component: auth },
  {
    path: '/index',
    component: main,
    children: [
      {
        path: 'employee-info/transactionHistory',
        component: transactionHistory,
      },
      {
        path: 'employee-info/giftHistory',
        component: giftHistory,
      },
      {
        path: 'employee-info/merchandiseControler',
        component: merchandiseControler,
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
