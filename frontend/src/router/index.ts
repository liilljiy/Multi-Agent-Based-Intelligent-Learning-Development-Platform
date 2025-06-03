import { createRouter, createWebHistory } from 'vue-router'
import index from '../views/index.vue'
import Login from '../views/Login.vue'
import Register from '../views/Register.vue'
import Dashboard from '../views/Dashboard.vue'

const routes = [
  {
    path: '/',
    name: 'Index',
    component: index
  },
  {
    path: '/login',
    name: 'Login',
    component: Login,
    meta: { guest: true }
  },
  {
    path: '/register',
    name: 'Register',
    component: Register,
    meta: { guest: true }
  },
  {
    path: '/dashboard',
    name: 'Dashboard',
    component: Dashboard,
    meta: { requiresAuth: true }
  }
]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
})

// 统一判断token是否有效的辅助函数
function isValidToken(token: string | null): boolean {
  return !!token && token !== 'null' && token !== 'undefined'
}

// 全局前置守卫
router.beforeEach((to, from, next) => {
  console.log('路由跳转开始:', { to, from })
  const token = localStorage.getItem('token')
  const hasToken = isValidToken(token)
  console.log('Token状态:', { hasToken, token, path: to.path })

  // 如果是访问登录或注册页面
  if (to.meta.guest) {
    console.log('访问登录/注册页面')
    if (hasToken) {
      console.log('已登录，重定向到首页')
      next({ path: '/' })
    } else {
      console.log('未登录，允许访问登录/注册页')
      next()
    }
  }
  // 如果是需要登录的页面
  else if (to.meta.requiresAuth) {
    console.log('访问需要登录的页面')
    if (!hasToken) {
      console.log('未登录，重定向到登录页')
      next({
        path: '/login',
        query: { redirect: to.fullPath }
      })
    } else {
      console.log('已登录，允许访问受保护页面')
      next()
    }
  }
  // 其他页面直接放行
  else {
    console.log('访问其他页面，直接放行:', to.path)
    next()
  }
})

export default router
