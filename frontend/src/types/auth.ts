export interface User {
  id: number
  username: string
  password: string
  age?: number
}

export interface LoginResponse {
  user: User
  token: string
  success: boolean
  message: string
}

export interface RegisterResponse {
  success: boolean
  message: string
} 