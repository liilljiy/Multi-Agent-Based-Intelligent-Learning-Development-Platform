import './assets/main.css'

import { createApp } from 'vue'
import App from './App.vue'
import ElementPlus from 'element-plus';
import 'element-plus/dist/index.css'; // 引入样式文件
import router from './router'
import axios from './utils/axios' // 导入配置好的axios实例

const app = createApp(App)
app.use(router)
app.use(ElementPlus)

// 全局挂载axios
app.config.globalProperties.$axios = axios

app.mount('#app')
