import { createRouter, createWebHistory } from 'vue-router'

// @ is an alias to /src

import About from '@/views/About'
import Book from '@/views/Book'
import Chapter from '@/views/Chapter'
import Read from '@/views/Read'
import Edit from '@/views/Edit'

const routes = [
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
    path: '/chapter',
    name: 'Chapter',
    component: Chapter,
    props: true,
  },
  {
    path: '/read',
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
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
})

export default router
