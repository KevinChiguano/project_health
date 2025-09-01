<template>
  <div class="flex flex-col h-screen bg-white dark:bg-gray-800 text-gray-900 dark:text-gray-100">

    <!-- Topbar -->
    <Topbar />
    <div class="flex flex-1 overflow-hidden">
      <!-- Sidebar -->
      <!-- <Sidebar class="w-64 shrink-0 hidden md:block" /> -->
      <main class="flex-1 overflow-y-auto bg-gray-100 dark:bg-gray-900">
        <router-view />
      </main>
    </div>
  </div>
</template>


<script>

import Topbar from "./components/Topbar.vue"
import Sidebar from './components/Sidebar.vue'

export default {
  name: "App",
  components: { Topbar, Sidebar }
};
</script>


<style>
/* Aquí no hace falta nada extra */
</style>
