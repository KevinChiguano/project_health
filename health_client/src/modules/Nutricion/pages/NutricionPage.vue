<template>
    <div class="min-h-screen bg-gray-50 dark:bg-gray-900 text-gray-800 dark:text-gray-100">
      <div class="mx-auto max-w-4xl px-6 py-16 sm:py-24 lg:py-32">
        <!-- Título -->
        <div class="text-center mb-12">
          <h1 class="text-4xl font-extrabold tracking-tight sm:text-5xl mb-4">
            🥑 Análisis <span class="text-green-600 dark:text-green-400">Nutricional</span>
          </h1>
          <p class="text-lg text-gray-600 dark:text-gray-300">
            Ingresa una descripción de tu comida o sube una foto para obtener información nutricional detallada.
          </p>
        </div>
  
        <!-- Formulario -->
        <div class="grid gap-10 sm:grid-cols-2">
          <!-- Texto -->
          <div class="p-6 rounded-2xl shadow-md bg-white dark:bg-gray-800">
            <h2 class="text-xl font-semibold mb-4">📋 Analizar por Texto</h2>
            <textarea
              v-model="descripcion"
              placeholder="Ejemplo: arroz con pollo y ensalada"
              class="w-full p-3 rounded-lg border border-gray-300 dark:border-gray-700 dark:bg-gray-900"
            ></textarea>
            <button
              @click="analizarTexto"
              class="mt-4 px-6 py-3 w-full rounded-xl text-white bg-green-600 hover:bg-green-700 dark:bg-green-500 dark:hover:bg-green-600 shadow-md"
            >
              Analizar
            </button>
          </div>
  
          <!-- Foto -->
          <div class="p-6 rounded-2xl shadow-md bg-white dark:bg-gray-800">
            <h2 class="text-xl font-semibold mb-4">📷 Analizar por Foto</h2>
            <input
              type="file"
              @change="onFileChange"
              accept="image/*"
              class="w-full p-2 border rounded-lg dark:border-gray-700 dark:bg-gray-900"
            />
            <button
              @click="analizarFoto"
              :disabled="!file"
              class="mt-4 px-6 py-3 w-full rounded-xl text-white bg-green-600 hover:bg-green-700 dark:bg-green-500 dark:hover:bg-green-600 shadow-md disabled:opacity-50"
            >
              Analizar
            </button>
          </div>
        </div>
  
        <!-- Resultado -->
        <div v-if="nutricion" class="mt-12">
          <h2 class="text-2xl font-bold text-center mb-6">📊 Resultados Nutricionales</h2>
          <div class="grid gap-6 sm:grid-cols-2 lg:grid-cols-4">
            <div class="p-6 rounded-2xl shadow-md bg-gray-50 dark:bg-gray-800 text-center">
              <p class="text-lg font-semibold">🔥 Calorías</p>
              <p class="text-2xl font-bold text-green-600 dark:text-green-400">{{ nutricion.calorias }}</p>
            </div>
            <div class="p-6 rounded-2xl shadow-md bg-gray-50 dark:bg-gray-800 text-center">
              <p class="text-lg font-semibold">💪 Proteínas</p>
              <p class="text-2xl font-bold text-green-600 dark:text-green-400">{{ nutricion.proteinas }} g</p>
            </div>
            <div class="p-6 rounded-2xl shadow-md bg-gray-50 dark:bg-gray-800 text-center">
              <p class="text-lg font-semibold">🥑 Grasas</p>
              <p class="text-2xl font-bold text-green-600 dark:text-green-400">{{ nutricion.grasas }} g</p>
            </div>
            <div class="p-6 rounded-2xl shadow-md bg-gray-50 dark:bg-gray-800 text-center">
              <p class="text-lg font-semibold">🍞 Carbohidratos</p>
              <p class="text-2xl font-bold text-green-600 dark:text-green-400">{{ nutricion.carbohidratos }} g</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import axios from "axios";
  import { analizarTexto } from "../helper/NutricionClient";
  import { analizarFoto } from "../helper/NutricionClient";
  
  export default {
    name: "NutricionPage",
    data() {
      return {
        descripcion: "",
        file: null,
        nutricion: null,
      };
    },
    methods: {
      async analizarTexto() {
        try {
          const res = await analizarTexto(this.descripcion)
          this.nutricion = res;
          //console.log(res)
        } catch (error) {
          console.error("Error analizando texto", error);
        }
      },
      onFileChange(event) {
        this.file = event.target.files[0];
      },
      async analizarFoto() {
        if (!this.file) return;
        const formData = new FormData();
        formData.append("file", this.file);
        try {
          const res = await analizarFoto(formData)

          this.nutricion = res;
          console.log(res.data)
        } catch (error) {
          console.error("Error analizando foto", error);
        }
      },
    },
  };
  </script>
  
  <style scoped>
  /* Animación suave */
  textarea,
  input,
  button {
    transition: all 0.3s ease;
  }
  </style>
  