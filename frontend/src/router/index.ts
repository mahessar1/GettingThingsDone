import { createRouter, createWebHistory } from '@ionic/vue-router';
import { RouteRecordRaw } from 'vue-router';
import Tabs from '../views/Tabs.vue'
import Login from '../views/Login.vue'

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    redirect: '/login'
  },
  {
    path: '/login',
    component: Login,
  },
  {
    path: '/tabs/',
    component: Tabs,
    children: [
      {
        path: '',
        redirect: '/tabs/overview'
      },
      {
        path: 'tasks',
        component: () => import('@/views/TasksPage.vue')
      },
      {
        path: 'projectlists',
        component: () => import('@/views/ProjectLists.vue')
      },
      {
        path: 'actionlists',
        component: () => import('@/views/ActionLists.vue')
      },
      {
        path: 'overview',
        component: () => import('@/views/OverviewPage.vue')
      },
      {
        path: 'taskdetails/:id',
        component: () => import('@/components/TaskDetails.vue')
      },
      {
        path: 'newproject',
        component: () => import('@/views/NewProject.vue')
      },
      {
        path: 'newtask',
        component: () => import('@/views/NewTask.vue')
      },
      {
        path: 'projecttask',
        component: () => import('@/components/TaskDetails.vue')
      }
    ]
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
