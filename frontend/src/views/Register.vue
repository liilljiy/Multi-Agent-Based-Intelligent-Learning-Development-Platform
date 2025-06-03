<template>
  <div class="register-container">
    <div class="register-box">
      <!-- 左侧装饰区域 -->
      <div class="register-left">
        <div class="welcome-text">
          <h1>创建账号</h1>
          <p>加入我们，开启您的旅程</p>
        </div>
      </div>
      
      <!-- 右侧注册表单 -->
      <div class="register-right">
        <div class="form-container">
          <h2>用户注册</h2>
          <el-form
            ref="registerFormRef"
            :model="registerForm"
            :rules="rules"
            class="register-form"
            @keyup.enter="handleRegister"
          >
            <el-form-item prop="username">
              <el-input
                v-model="registerForm.username"
                placeholder="用户名"
                :prefix-icon="User"
                clearable
              />
            </el-form-item>
            
            <el-form-item prop="password">
              <el-input
                v-model="registerForm.password"
                type="password"
                placeholder="密码"
                :prefix-icon="Lock"
                show-password
                clearable
              />
            </el-form-item>

            <el-form-item prop="confirmPassword">
              <el-input
                v-model="registerForm.confirmPassword"
                type="password"
                placeholder="确认密码"
                :prefix-icon="Lock"
                show-password
                clearable
              />
            </el-form-item>

            <el-form-item>
              <el-button
                type="primary"
                class="register-button"
                :loading="loading"
                @click="handleRegister"
              >
                {{ loading ? '注册中...' : '注册' }}
              </el-button>
            </el-form-item>

            <div class="login-link">
              已有账号？
              <router-link to="/login" class="link">立即登录</router-link>
            </div>
          </el-form>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive, computed } from 'vue'
import { useRouter, useRoute } from 'vue-router'
import { ElMessage } from 'element-plus'
import type { FormInstance } from 'element-plus'
import { User, Lock } from '@element-plus/icons-vue'
import request from '../utils/axios'
import type { RegisterResponse } from '../types/auth'

const router = useRouter()
const route = useRoute()
const registerFormRef = ref<FormInstance>()
const loading = ref(false)

const registerForm = reactive({
  username: '',
  password: '',
  confirmPassword: ''
})

// 检查是否有重定向参数
const redirectPath = computed(() => {
  return route.query.redirect as string || '/login'
})

const validatePass = (rule: any, value: string, callback: any) => {
  if (value === '') {
    callback(new Error('请再次输入密码'))
  } else if (value !== registerForm.password) {
    callback(new Error('两次输入密码不一致'))
  } else {
    callback()
  }
}

const rules = {
  username: [
    { required: true, message: '请输入用户名', trigger: 'blur' },
    { min: 3, max: 20, message: '用户名长度应在 3 到 20 个字符之间', trigger: 'blur' }
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' },
    { min: 6, max: 20, message: '密码长度应在 6 到 20 个字符之间', trigger: 'blur' }
  ],
  confirmPassword: [
    { required: true, message: '请再次输入密码', trigger: 'blur' },
    { validator: validatePass, trigger: 'blur' }
  ]
}

const handleRegister = async () => {
  if (!registerFormRef.value) return
  
  try {
    await registerFormRef.value.validate()
    loading.value = true
    
    const response = await request.post<RegisterResponse>('/api/auth/register', {
      username: registerForm.username,
      password: registerForm.password
    })
    
    if (response.data && response.data.success) {
      ElMessage.success(response.data.message || '注册成功，即将跳转到登录页面')
      // 使用重定向路径或默认跳转到登录页
      setTimeout(() => {
        router.push({
          path: redirectPath.value,
          query: { username: registerForm.username }
        })
      }, 1500)
    } else {
      ElMessage.error(response.data?.message || '注册失败')
    }
  } catch (error: any) {
    console.error('注册错误:', error)
    if (error.response?.data?.message) {
      ElMessage.error(error.response.data.message)
    } else {
      ElMessage.error('注册失败，请稍后重试')
    }
  } finally {
    loading.value = false
  }
}
</script>

<style scoped>
.register-container {
  min-height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  padding: 20px;
}

.register-box {
  width: 1000px;
  height: 600px;
  background: white;
  border-radius: 20px;
  box-shadow: 0 15px 30px rgba(0, 0, 0, 0.1);
  display: flex;
  overflow: hidden;
}

.register-left {
  flex: 1;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  padding: 40px;
  display: flex;
  align-items: center;
  justify-content: center;
  color: white;
  position: relative;
  overflow: hidden;
}

.register-left::before {
  content: '';
  position: absolute;
  width: 200%;
  height: 200%;
  background: rgba(255, 255, 255, 0.1);
  transform: rotate(45deg);
  top: -50%;
  left: -50%;
  animation: shine 3s infinite;
}

@keyframes shine {
  0% {
    transform: rotate(45deg) translateY(-100%);
  }
  100% {
    transform: rotate(45deg) translateY(100%);
  }
}

.welcome-text {
  text-align: center;
  z-index: 1;
}

.welcome-text h1 {
  font-size: 2.5em;
  margin-bottom: 20px;
  font-weight: 600;
}

.welcome-text p {
  font-size: 1.1em;
  opacity: 0.9;
}

.register-right {
  flex: 1;
  padding: 40px;
  display: flex;
  align-items: center;
}

.form-container {
  width: 100%;
  max-width: 400px;
  margin: 0 auto;
}

.form-container h2 {
  font-size: 2em;
  color: #333;
  margin-bottom: 30px;
  text-align: center;
}

.register-form {
  margin-top: 20px;
}

.register-form :deep(.el-input__wrapper) {
  padding: 12px 15px;
  border-radius: 8px;
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.05);
}

.register-form :deep(.el-input__inner) {
  height: 24px;
}

.register-button {
  width: 100%;
  padding: 12px;
  font-size: 1.1em;
  border-radius: 8px;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  border: none;
  transition: transform 0.3s, box-shadow 0.3s;
}

.register-button:hover {
  transform: translateY(-2px);
  box-shadow: 0 5px 15px rgba(102, 126, 234, 0.4);
}

.login-link {
  text-align: center;
  margin-top: 20px;
  color: #666;
}

.link {
  color: #667eea;
  text-decoration: none;
  font-weight: 600;
  margin-left: 5px;
  transition: color 0.3s;
}

.link:hover {
  color: #764ba2;
}

@media (max-width: 768px) {
  .register-box {
    flex-direction: column;
    width: 100%;
    height: auto;
  }

  .register-left {
    padding: 30px;
  }

  .register-right {
    padding: 30px;
  }

  .welcome-text h1 {
    font-size: 2em;
  }
}
</style>
  