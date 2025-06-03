<template>
  <div class="dashboard-bg">
    <div class="dashboard-container">
      <!-- 顶部导航栏 -->
      <el-header class="header">
        <div class="header-left">
          <h2>智慧教育平台</h2>
        </div>
        <div class="header-right">
          <el-dropdown @command="handleCommand">
            <span class="user-info">
              {{ username || '用户' }}
              <el-icon><ArrowDown /></el-icon>
            </span>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item command="profile">个人信息</el-dropdown-item>
                <el-dropdown-item command="settings">设置</el-dropdown-item>
                <el-dropdown-item divided command="logout">退出登录</el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </div>
      </el-header>
      <!-- 主要内容区域 -->
      <div class="main-content">
        <!-- 左侧菜单 -->
        <el-aside width="200px" class="aside">
          <el-menu
            :default-active="activeMenu"
            class="menu"
            @select="handleSelect"
          >
            <el-menu-item index="dashboard">
              <el-icon><HomeFilled /></el-icon>
              <span>首页</span>
            </el-menu-item>
            <el-menu-item index="courses">
              <el-icon><Reading /></el-icon>
              <span>课程管理</span>
            </el-menu-item>
            <el-menu-item index="students">
              <el-icon><User /></el-icon>
              <span>学生管理</span>
            </el-menu-item>
            <el-menu-item index="teachers">
              <el-icon><UserFilled /></el-icon>
              <span>教师管理</span>
            </el-menu-item>
            <el-menu-item index="analytics">
              <el-icon><DataLine /></el-icon>
              <span>数据分析</span>
            </el-menu-item>
          </el-menu>
        </el-aside>
        <!-- 右侧内容区 -->
        <el-main class="main">
          <div class="content-header">
            <h3>{{ pageTitle }}</h3>
          </div>
          <div class="content-body">
            <!-- 这里根据不同的菜单项显示不同的内容 -->
            <div v-if="activeMenu === 'dashboard'">
              <el-row :gutter="20">
                <el-col :span="6">
                  <el-card shadow="hover">
                    <template #header>
                      <div class="card-header">
                        <span>总课程数</span>
                      </div>
                    </template>
                    <div class="card-content">
                      <h2>12</h2>
                    </div>
                  </el-card>
                </el-col>
                <el-col :span="6">
                  <el-card shadow="hover">
                    <template #header>
                      <div class="card-header">
                        <span>学生总数</span>
                      </div>
                    </template>
                    <div class="card-content">
                      <h2>256</h2>
                    </div>
                  </el-card>
                </el-col>
                <el-col :span="6">
                  <el-card shadow="hover">
                    <template #header>
                      <div class="card-header">
                        <span>教师总数</span>
                      </div>
                    </template>
                    <div class="card-content">
                      <h2>24</h2>
                    </div>
                  </el-card>
                </el-col>
                <el-col :span="6">
                  <el-card shadow="hover">
                    <template #header>
                      <div class="card-header">
                        <span>活跃用户</span>
                      </div>
                    </template>
                    <div class="card-content">
                      <h2>189</h2>
                    </div>
                  </el-card>
                </el-col>
              </el-row>
            </div>
          </div>
        </el-main>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, computed } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage, ElMessageBox } from 'element-plus'
import {
  HomeFilled,
  Reading,
  User,
  UserFilled,
  DataLine,
  ArrowDown
} from '@element-plus/icons-vue'

const router = useRouter()
const username = ref(localStorage.getItem('username') || '用户')
const activeMenu = ref('dashboard')

// 根据当前选中的菜单项计算页面标题
const pageTitle = computed(() => {
  const titles: { [key: string]: string } = {
    dashboard: '控制台',
    courses: '课程管理',
    students: '学生管理',
    teachers: '教师管理',
    analytics: '数据分析'
  }
  return titles[activeMenu.value] || '控制台'
})

// 处理菜单选择
const handleSelect = (key: string) => {
  activeMenu.value = key
  // 这里可以根据不同的菜单项加载不同的内容
}

// 处理用户下拉菜单命令
const handleCommand = async (command: string) => {
  switch (command) {
    case 'profile':
      ElMessage.info('查看个人信息')
      break
    case 'settings':
      ElMessage.info('进入设置页面')
      break
    case 'logout':
      try {
        await ElMessageBox.confirm(
          '确定要退出登录吗？',
          '提示',
          {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }
        )
        // 清除登录信息
        localStorage.removeItem('token')
        localStorage.removeItem('username')
        ElMessage.success('已退出登录')
        // 跳转到登录页
        router.replace('/login')
      } catch {
        // 用户取消退出
      }
      break
  }
}
</script>

<style scoped>
.dashboard-bg {
  min-height: 100vh;
  width: 100vw;
  background: linear-gradient(120deg, #667eea 0%, #764ba2 50%, #6b8dd6 100%);
}

.dashboard-container {
  width: 100vw;
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  background: transparent;
}

.header {
  background: transparent;
  color: white;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0 32px;
  width: 100%;
  min-height: 64px;
  border-radius: 0;
  margin-top: 0;
  box-shadow: none;
}

.main-content {
  width: 100vw;
  min-height: calc(100vh - 64px);
  display: flex;
  margin: 0;
  border-radius: 0;
  box-shadow: none;
  background: transparent;
}

.header-centered {
  width: 100vw;
  display: flex;
  justify-content: center;
  background: transparent;
}

.header h2 {
  margin: 0;
  font-size: 1.5em;
}

.user-info {
  color: white;
  cursor: pointer;
  display: flex;
  align-items: center;
  gap: 8px;
}

.aside {
  background: transparent;
  border-right: 1px solid #e6e6e6;
}

.menu {
  height: 100%;
  border-right: none;
  background: transparent;
}

.main {
  padding: 20px;
  background: transparent;
}

.content-header {
  margin-bottom: 20px;
}

.content-header h3 {
  margin: 0;
  color: #fff;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.card-content {
  text-align: center;
  padding: 20px 0;
}

.card-content h2 {
  margin: 0;
  font-size: 24px;
  color: #409EFF;
}

.el-card {
  margin-bottom: 20px;
  background: rgba(255,255,255,0.85);
  border-radius: 12px;
  box-shadow: 0 2px 8px rgba(102,126,234,0.08);
}

.el-card__header {
  background: transparent;
}

@media (max-width: 900px) {
  .main-content {
    flex-direction: column;
  }
  .aside {
    width: 100vw !important;
    border-right: none;
    border-bottom: 1px solid #e6e6e6;
  }
}
</style> 