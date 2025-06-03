<template>
  <div class="fullscreen-bg">
    <div class="center-content">
      <h1 class="main-title">欢迎来到智慧教育平台</h1>
      <p class="main-subtitle">智能教学，未来已来</p>
      <el-button type="primary" size="large" class="main-btn" @click="goLogin">立即体验</el-button>
    </div>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router'

const router = useRouter()

const goLogin = () => {
  console.log('点击立即体验按钮')
  try {
    // 清除可能存在的旧token
    localStorage.removeItem('token')
    console.log('清除旧token，跳转到登录页')
    router.replace('/login')
  } catch (error) {
    console.error('路由跳转错误:', error)
  }
}
</script>

<style lang="scss">
@import url('https://fonts.googleapis.com/css2?family=Orbitron:wght@700;900&family=Poppins:wght@400;700&display=swap');

html, body {
  height: 100%;
  width: 100%;
  margin: 0;
  padding: 0;
  background: linear-gradient(120deg, #667eea 0%, #764ba2 50%, #6b8dd6 100%) !important;
  min-height: 100vh;
  min-width: 100vw;
  overflow-x: hidden;
}

#app {
  height: 100%;
  width: 100%;
  min-height: 100vh;
  min-width: 100vw;
}

.fullscreen-bg {
  min-height: 100vh;
  min-width: 100vw;
  height: 100vh;
  width: 100vw;
  display: flex;
  align-items: center;
  justify-content: center;
  background: radial-gradient(circle at 20% 30%, rgba(255,255,255,0.08) 0, transparent 60%),
              radial-gradient(circle at 80% 70%, rgba(255,255,255,0.10) 0, transparent 70%);
  position: relative;
  overflow: hidden;
}

.center-content {
  text-align: center;
  color: #fff;
  z-index: 2;
  user-select: none;
}

.main-title {
  font-family: 'Orbitron', 'Poppins', sans-serif;
  font-size: 5vw;
  font-weight: 900;
  margin-bottom: 32px;
  letter-spacing: 4px;
  text-shadow: 0 8px 32px rgba(0,0,0,0.25), 0 0 32px #fff2, 0 0 80px #764ba2aa;
  background: linear-gradient(90deg, #fff 20%, #ffd700 50%, #fff 80%);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  animation: shine 2.5s linear infinite;
}

@keyframes shine {
  0% {
    background-position: -200px 0;
  }
  100% {
    background-position: 400px 0;
  }
}

.main-subtitle {
  font-family: 'Poppins', sans-serif;
  font-size: 2vw;
  font-weight: 400;
  margin-bottom: 60px;
  opacity: 0.96;
  letter-spacing: 2px;
  text-shadow: 0 2px 12px #0005;
}

.main-btn {
  font-size: 1.5vw;
  padding: 20px 64px;
  border-radius: 40px;
  background: linear-gradient(90deg, #ffd700 0%, #764ba2 100%);
  border: none;
  color: #fff;
  font-weight: 900;
  box-shadow: 0 8px 32px #ffd70055, 0 2px 24px #764ba255;
  transition: background 0.3s, box-shadow 0.3s, transform 0.2s;
  letter-spacing: 2px;
  text-shadow: 0 2px 8px #764ba2aa;
}

.main-btn:hover {
  background: linear-gradient(90deg, #764ba2 0%, #ffd700 100%);
  box-shadow: 0 16px 48px #ffd70099, 0 4px 32px #764ba299;
  transform: scale(1.06) translateY(-4px);
}

.login-container {
  min-height: 100vh;
  min-width: 100vw;
  width: 100vw;
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

.register-container {
  min-height: 100vh;
  min-width: 100vw;
  width: 100vw;
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}
</style>
