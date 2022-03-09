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
import Teacher from '@/views/BoardTeacher'
import User from '@/views/BoardStudent'
import AudioTest from '@/views/AudioTest'
import AudioTestTaking from '@/views/AudioTestTaking'
import AudioAnswerList from '@/views/AudioAnswerList'
import AudioAnswerComment from '@/views/AudioAnswerComment'

const routes = [
  {
    path: '/',
    name: 'Login',
    component: Login,
    props: true,
  },
  {
    path: '/student/:courseId/:bookId/:id',
    name: 'Student',
    component: Student,
    props: true,
  },
  {
    path: '/book/:courseId',
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
    path: '/chapter/:courseId/:bookId',
    name: 'Chapter',
    component: Chapter,
    props: true,
  },
  {
    path: '/read/:courseId/:bookId/:id',
    name: 'Read',
    component: Read,
    props: true,
  },
  {
    path: '/edit/:courseId/:bookId/:id',
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
    path: '/teacher',
    name: 'Teacher',
    component: Teacher,
    props: true,
  },
  {
    path: '/user',
    name: 'User',
    component: User,
    props: true,
  },
  {
    path: '/audioTest',
    name: 'AudioTest',
    component: AudioTest,
    props: true,
  },
  {
    path: '/audioTestTaking/:id',
    name: 'AudioTestTaking',
    component: AudioTestTaking,
    props: true,
  },
  {
    path: '/audioAnswerList/:id',
    name: 'AudioAnswerList',
    component: AudioAnswerList,
    props: true,
  },
  {
    path: '/audioAnswerComment/:testId/:answerId',
    name: 'AudioAnswerComment',
    component: AudioAnswerComment,
    props: true,
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
})

export default router
