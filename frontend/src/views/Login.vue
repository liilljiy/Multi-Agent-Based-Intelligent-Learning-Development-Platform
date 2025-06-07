<template>
  <div class="login-container">
    <div class="login-box">
      <!-- 左侧装饰区域 -->
      <div class="login-left">
        <div class="welcome-text">
          <h1>欢迎回来</h1>
          <p>登录您的账号，开启您的旅程</p>
        </div>
      </div>
      
      <!-- 右侧登录表单 -->
      <div class="login-right">
        <div class="form-container">
          <h2>用户登录</h2>
          <el-form
            ref="loginFormRef"
            :model="loginForm"
            :rules="rules"
            class="login-form"
            @keyup.enter="handleLogin"
          >
            <el-form-item prop="username">
              <el-input
                v-model="loginForm.username"
                placeholder="用户名"
                :prefix-icon="User"
                clearable
              />
            </el-form-item>
            
            <el-form-item prop="password">
              <el-input
                v-model="loginForm.password"
                type="password"
                placeholder="密码"
                :prefix-icon="Lock"
                show-password
                clearable
              />
            </el-form-item>

            <div class="remember-forgot">
              <el-checkbox v-model="rememberMe">记住我</el-checkbox>
              <a href="#" class="forgot-link">忘记密码？</a>
            </div>

            <el-form-item>
              <el-button
                type="primary"
                class="login-button"
                :loading="loading"
                @click="handleLogin"
              >
                {{ loading ? '登录中...' : '登录' }}
              </el-button>
            </el-form-item>

            <div class="register-link">
              还没有账号？
              <router-link to="/register" class="link">立即注册</router-link>
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
import type { LoginResponse } from '../types/auth'

const router = useRouter()
const route = useRoute()
const loginFormRef = ref<FormInstance>()
const loading = ref(false)
const rememberMe = ref(false)

// 检查是否有重定向参数
const redirectPath = computed(() => {
  return route.query.redirect as string || '/dashboard'
})

const loginForm = reactive({
  username: '',
  password: ''
})

const rules = {
  username: [
    { required: true, message: '请输入用户名', trigger: 'blur' },
    { min: 3, max: 20, message: '用户名长度应在 3 到 20 个字符之间', trigger: 'blur' }
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' },
    { min: 6, max: 20, message: '密码长度应在 6 到 20 个字符之间', trigger: 'blur' }
  ]
}

const handleLogin = async () => {
  if (!loginFormRef.value) return

  try {
    await loginFormRef.value.validate()
    loading.value = true

    const response = await request.post('/api/auth/login', loginForm)

    // response.data 是内部的 data 部分
    const res = response.data

    if (res.success) {
      localStorage.setItem('token', res.token)
      if (rememberMe.value) {
        localStorage.setItem('username', loginForm.username)
      } else {
        localStorage.removeItem('username')
      }
      ElMessage.success(res.message || '登录成功')
      router.push(redirectPath.value)
    } else {
      ElMessage.error(res.message || '登录失败')
    }
  } catch (error: any) {
    console.error('登录错误:', error)
    if (error.response?.data?.message) {
      ElMessage.error(error.response.data.message)
    } else {
      ElMessage.error('登录失败，请稍后重试')
    }
  } finally {
    loading.value = false
  }
}


</script>

<style scoped>
.login-container {
  min-height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  padding: 20px;
}

.login-box {
  width: 1000px;
  height: 600px;
  background: white;
  border-radius: 20px;
  box-shadow: 0 15px 30px rgba(0, 0, 0, 0.1);
  display: flex;
  overflow: hidden;
}

.login-left {
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

.login-left::before {
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

.login-right {
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

.login-form {
  margin-top: 20px;
}

.login-form :deep(.el-input__wrapper) {
  padding: 12px 15px;
  border-radius: 8px;
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.05);
}

.login-form :deep(.el-input__inner) {
  height: 24px;
}

.remember-forgot {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

.forgot-link {
  color: #667eea;
  text-decoration: none;
  font-size: 0.9em;
  transition: color 0.3s;
}

.forgot-link:hover {
  color: #764ba2;
}

.login-button {
  width: 100%;
  padding: 12px;
  font-size: 1.1em;
  border-radius: 8px;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  border: none;
  transition: transform 0.3s, box-shadow 0.3s;
}

.login-button:hover {
  transform: translateY(-2px);
  box-shadow: 0 5px 15px rgba(102, 126, 234, 0.4);
}

.register-link {
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
  .login-box {
    flex-direction: column;
    width: 100%;
    height: auto;
  }

  .login-left {
    padding: 30px;
  }

  .login-right {
    padding: 30px;
  }

  .welcome-text h1 {
    font-size: 2em;
  }
}
</style>
  