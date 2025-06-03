import axios from 'axios'
import { ElMessage } from 'element-plus'

interface Result<T> {
  code: number
  data: T
  message?: string
  msg?: string
  timestamp?: number
}

const instance = axios.create({
  baseURL: 'http://localhost:8080',
  timeout: 5000
})

// 请求拦截器
instance.interceptors.request.use(
  config => {
    const token = localStorage.getItem('token')
    if (token) {
      config.headers.Authorization = `Bearer ${token}`
    }
    return config
  },
  error => Promise.reject(error)
)

// 响应拦截器
instance.interceptors.response.use(
  response => {
    console.log('后端返回的原始数据:', response.data)
    const res = response.data as Result<any>
    
    // 检查响应状态码
    if (res.code !== 200) {
      ElMessage.error(res.message || res.msg || '请求失败')
      return Promise.reject(new Error(res.message || res.msg || 'Error'))
    }
    
    // 返回完整的响应对象，但将 data 字段的内容放在 response.data 中
    console.log('处理后的数据:', res.data)
    return {
      ...response,
      data: res.data
    }
  },
  error => {
    console.error('请求错误:', error.response?.data || error)
    if (error.response) {
      switch (error.response.status) {
        case 401:
          ElMessage.error('未授权，请重新登录')
          localStorage.removeItem('token')
          window.location.href = '/login'
          break
        case 403:
          ElMessage.error('拒绝访问')
          break
        case 404:
          ElMessage.error('请求错误，未找到该资源')
          break
        case 500:
          ElMessage.error('服务器端出错')
          break
        default:
          ElMessage.error('未知错误')
      }
    } else {
      ElMessage.error('网络连接错误')
    }
    return Promise.reject(error)
  }
)

export default instance
