<template>
    <div class="min-h-screen bg-gray-50 dark:bg-gray-900 text-gray-800 dark:text-gray-100">
      <!-- Hero Section -->
      <section class="relative overflow-hidden">
        <div class="mx-auto max-w-7xl px-6 py-16 sm:py-24 lg:py-32 flex flex-col-reverse lg:flex-row items-center gap-10">
          <!-- Text content -->
          <div class="flex-1 text-center lg:text-left">
            <h1 class="text-4xl font-extrabold tracking-tight sm:text-5xl mb-6">
              Bienvenido a <span class="text-green-600 dark:text-green-400">Health Tracker</span>
            </h1>
            <p class="text-lg sm:text-xl mb-8 text-gray-600 dark:text-gray-300">
              Con esta aplicación podrás <span class="font-semibold">contar las calorías</span> de tus comidas de forma sencilla,
              llevar control de tu nutrición y mantener un estilo de vida saludable.
            </p>
            <div class="flex justify-center lg:justify-start gap-4">
              <router-link
                to="/nutricion"
                class="px-6 py-3 rounded-xl text-white bg-green-600 hover:bg-green-700 dark:bg-green-500 dark:hover:bg-green-600 shadow-md"
              >
                Empezar ahora
              </router-link>
              <router-link
                to="/acerca"
                class="px-6 py-3 rounded-xl border border-gray-300 dark:border-gray-700 hover:bg-gray-100 dark:hover:bg-gray-800"
              >
                Más información
              </router-link>
            </div>
          </div>
  
          <!-- Image -->
          <div class="flex-1 flex justify-center">
            <img
              src="@/assets/health.png"
              alt="Health Tracker"
              class="max-w-sm lg:max-w-md drop-shadow-lg"
            />
          </div>
        </div>
      </section>
  
      <!-- Features Section -->
      <section class="bg-white dark:bg-gray-800 py-16 sm:py-24">
        <div class="mx-auto max-w-7xl px-6">
          <h2 class="text-3xl font-bold text-center mb-12">¿Qué puedes hacer aquí?</h2>
          <div class="grid gap-10 sm:grid-cols-2 lg:grid-cols-3">
            <!-- Feature card -->
            <div class="p-6 rounded-2xl shadow-md bg-gray-50 dark:bg-gray-900 hover:shadow-lg transition">
              <h3 class="text-xl font-semibold mb-3">🍎 Control Nutricional</h3>
              <p class="text-gray-600 dark:text-gray-300">
                Registra tus comidas y obtén información detallada de calorías y nutrientes.
              </p>
            </div>
            <div class="p-6 rounded-2xl shadow-md bg-gray-50 dark:bg-gray-900 hover:shadow-lg transition">
              <h3 class="text-xl font-semibold mb-3">🥗 Recetas Saludables</h3>
              <p class="text-gray-600 dark:text-gray-300">
                Descubre recetas fáciles y balanceadas para mejorar tu alimentación.
              </p>
            </div>
            <div class="p-6 rounded-2xl shadow-md bg-gray-50 dark:bg-gray-900 hover:shadow-lg transition">
              <h3 class="text-xl font-semibold mb-3">📊 Estadísticas</h3>
              <p class="text-gray-600 dark:text-gray-300">
                Visualiza tu progreso con gráficos claros sobre tu consumo diario.
              </p>
            </div>
          </div>
        </div>
      </section>
    </div>
  </template>
  
  <script>
  export default {
    name: "PrincipalPage",
  };
  </script>
  
  <style scoped>
  /* Pequeño detalle extra: animación suave en secciones */
  section {
    transition: background-color 0.3s ease, color 0.3s ease;
  }
  </style>
  