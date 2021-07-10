import * as VueRouter from 'vue-router';
import auth from '../views/Auth.vue';
import index from '../views/Index.vue';
import PersonalData from '../components/modules/employee-info/PersonalData.vue';
import Presentation from '../components/modules/presentation/Presentation.vue';
const routes = [
  { path: '/', component: auth },
  {
    path: '/index',
    component: index,
    children: [
      {
        path: 'employee-info/personal-data',
        component: PersonalData,
      },  {
        path: 'presentation',
        component: Presentation,
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
