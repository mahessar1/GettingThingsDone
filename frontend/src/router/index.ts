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
        path: 'newactionlist',
        component: () => import('@/views/NewActionlist.vue')
      },
      {
        path: 'newtask',
        component: () => import('@/views/NewTask.vue')
      },
      {
        path: 'projecttask/:id',
        component: () => import('@/views/ListTasks.vue')
      },
      {
        path: 'edittask/:id',
        component: () => import('@/views/EditTask.vue')
      }
    ]
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
