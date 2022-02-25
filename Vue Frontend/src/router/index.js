import { createRouter, createWebHistory } from 'vue-router'

// @ is an alias to /src

import About from '@/views/About'
import Book from '@/views/Book'
import Chapter from '@/views/Chapter'
import Read from '@/views/Read'
import Edit from '@/views/Edit'
import Student from '@/views/Student'
import Home from '@/views/Home'
import Login from '@/views/Login'
import Register from '@/views/Register'
import Profile from '@/views/Profile'
import Admin from '@/views/BoardAdmin'
import Mod from '@/views/BoardModerator'
import User from '@/views/BoardUser'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home,
    props: true,
  },
  {
    path: '/student',
    name: 'Student',
    component: Student,
    props: true,
  },
  {
    path: '/books',
    name: 'Book',
    component: Book,
    props: true,
  },
  {
    path: '/about',
    name: 'About',
    component: About,
    props: true,
  },
  {
    path: '/chapter/:id',
    name: 'Chapter',
    component: Chapter,
    props: true,
  },
  {
    path: '/read/:id',
    name: 'Read',
    component: Read,
    props: true,
  },
  {
    path: '/edit',
    name: 'Edit',
    component: Edit,
    props: true,
  },
  {
    path:'/login',
    name: 'Log In',
    component: Login,
    props: true,
  },
  {
    path: '/register',
    name: 'Register',
    component: Register,
    props: true,
  },
  {
    path: '/home',
    name: 'Home',
    component: Home,
    props: true,
  },
  {
    path: '/profile',
    name: 'Profile',
    component: Profile,
    props: true,
  },
  {
    path: '/admin',
    name: 'Admin',
    component: Admin,
    props: true,
  },
  {
    path: '/mod',
    name: 'Moderator',
    component: Mod,
    props: true,
  },
  {
    path: '/user',
    name: 'User',
    component: User,
    props: true,
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
})

export default router
