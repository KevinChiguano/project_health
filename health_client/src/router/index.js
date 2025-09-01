import { createRouter, createWebHistory } from 'vue-router'

import PrincipalPage from '@/components/PrincipalPage.vue'
import AboutPage from '@/components/AboutPage.vue'
import NutricionPage from '@/modules/Nutricion/pages/NutricionPage.vue'
import RecetasPage from '@/modules/Recetas/pages/RecetasPage.vue'
 

const routes = [
  { path: '/', component: PrincipalPage },
  { path: '/about', component: AboutPage},
  { path: '/nutricion', component: NutricionPage},
  { path: '/recetas', component: RecetasPage}


]

export default createRouter({
  history: createWebHistory(),
  routes,
})
