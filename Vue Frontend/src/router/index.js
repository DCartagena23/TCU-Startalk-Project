import { createRouter, createWebHistory } from 'vue-router'

// @ is an alias to /src

import About from '@/views/About'
import Book from '@/views/Book'
import Chapter from '@/views/Chapter'
import Read from '@/views/Read'
import Edit from '@/views/Edit'
import Student from '@/views/Student'
import LogIn from '@/views/LogIn'
import SignUp from '@/views/SignUp'

const routes = [
  {
    path: '/student',
    name: 'Student',
    component: Student,
    props: true,
  },
  {
    path: '/',
    name: 'Books',
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
    component: LogIn,
    props: true,
  },
  {
    path: '/signup',
    name: 'Sign Up',
    component: SignUp,
    props: true,
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
})

export default router
