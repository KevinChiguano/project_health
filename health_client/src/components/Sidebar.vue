<template>
    <!-- Fondo oscuro en mobile -->
    <div
      v-if="isOpen"
      class="fixed inset-0 z-40 bg-black/50 sm:hidden"
      @click="$emit('close')"
    ></div>
  
    <!-- Sidebar -->
    <aside
      :class="[
        'fixed inset-y-0 left-0 z-50 w-64 bg-gray-900 text-white transform transition-transform sm:translate-x-0',
        isOpen ? 'translate-x-0' : '-translate-x-full'
      ]"
    >
      <div class="flex items-center h-16 px-4 border-b border-gray-700">
        <span class="text-lg font-bold">Menú</span>
      </div>
      <nav class="mt-4 space-y-2 px-4">
        <a href="#" class="block px-3 py-2 rounded-md hover:bg-gray-700">Dashboard</a>
        <a href="#" class="block px-3 py-2 rounded-md hover:bg-gray-700">Pacientes</a>
        <a href="#" class="block px-3 py-2 rounded-md hover:bg-gray-700">Citas</a>
        <a href="#" class="block px-3 py-2 rounded-md hover:bg-gray-700">Reportes</a>
      </nav>
    </aside>
  </template>
  
  <script>
  export default {
    name: "Sidebar",
    props: {
      isOpen: { type: Boolean, default: false },
    },
  };
  </script>
  