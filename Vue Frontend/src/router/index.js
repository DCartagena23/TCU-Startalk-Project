import { createRouter, createWebHistory } from 'vue-router'

// @ is an alias to /src

import About from '@/views/About'
import Book from '@/views/Book'
import Chapter from '@/views/Chapter'
import Read from '@/views/Read'
import Edit from '@/views/Edit'
import Student from '@/views/Student'
import AudioTest from '@/views/AudioTest'
import AudioTestTaking from '@/views/AudioTestTaking'
import AudioAnswerList from '@/views/AudioAnswerList'
import AudioAnswerComment from '@/views/AudioAnswerComment'

const routes = [
  {
    path: '/student/:id',
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
    path: '/edit/:id',
    name: 'Edit',
    component: Edit,
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
