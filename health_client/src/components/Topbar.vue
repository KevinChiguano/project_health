<template>
    <nav class="bg-white border-b border-gray-200 text-gray-800 
             dark:bg-gray-900 dark:border-gray-700 dark:text-gray-100 shadow-sm">
        <div class="mx-auto max-w-7xl px-4 sm:px-6 lg:px-8">
            <div class="flex h-16 justify-between items-center">
                <!-- Logo -->
                <div class="flex items-center">
                    <router-link to="/">
                        <img src="@/assets/health.png" alt="Logo" class="h-8 w-auto" />
                    </router-link>

                </div>

                <!-- Desktop menu -->
                <div class="hidden sm:flex space-x-4">
                    <router-link to="/nutricion" class="px-3 py-2 rounded-md text-sm font-medium 
                      text-gray-900 bg-gray-100 hover:bg-gray-200
                      dark:text-gray-200 dark:bg-gray-800 dark:hover:bg-gray-700"> Nutrición</router-link>

                    <router-link to="/recetas" class="px-3 py-2 rounded-md text-sm font-medium 
                      text-gray-600 hover:bg-gray-100 hover:text-gray-900
                      dark:text-gray-300 dark:hover:bg-gray-700 dark:hover:text-white">Recetas</router-link>

                    <router-link to="/about" class="px-3 py-2 rounded-md text-sm font-medium 
                      text-gray-600 hover:bg-gray-100 hover:text-gray-900
                      dark:text-gray-300 dark:hover:bg-gray-700 dark:hover:text-white">Acerca de</router-link>
                </div>

                <!-- Right side -->
                <div class="flex items-center space-x-4">
                    <!-- Dark mode toggle -->
                    <button @click="toggleDarkMode" class="p-2 rounded-md text-gray-500 hover:text-gray-900 hover:bg-gray-100
                     dark:text-gray-400 dark:hover:text-white dark:hover:bg-gray-700">
                        <span class="sr-only">Toggle dark mode</span>
                        <!-- Sol -->
                        <svg v-if="!isDark" xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none"
                            viewBox="0 0 24 24" stroke="currentColor" stroke-width="1.5">
                            <path stroke-linecap="round" stroke-linejoin="round" d="M12 3v1m0 16v1m9-9h-1M4 12H3
                         m15.364-6.364l-.707.707M7.05 16.95l-.707.707
                         m12.021 0l-.707-.707M7.05 7.05l-.707-.707
                         M16 12a4 4 0 11-8 0 4 4 0 018 0z" />
                        </svg>
                        <!-- Luna -->

                        <svg v-else xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24"
                            stroke-width="1.5" stroke="currentColor" class="size-6">
                            <path stroke-linecap="round" stroke-linejoin="round"
                                d="M21.752 15.002A9.72 9.72 0 0 1 18 15.75c-5.385 0-9.75-4.365-9.75-9.75 0-1.33.266-2.597.748-3.752A9.753 9.753 0 0 0 3 11.25C3 16.635 7.365 21 12.75 21a9.753 9.753 0 0 0 9.002-5.998Z" />
                        </svg>

                    </button>

                    <!-- Notifications -->
                    <button type="button" class="p-1 rounded-full text-gray-500 hover:text-gray-900 hover:bg-gray-100
                     dark:text-gray-400 dark:hover:text-white dark:hover:bg-gray-700">
                        <span class="sr-only">View notifications</span>
                        <svg class="h-6 w-6" fill="none" stroke="currentColor" stroke-width="1.5" viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" d="M14.857 17.082a23.848 23.848 0 0 0 
                         5.454-1.31A8.967 8.967 0 0 1 18 9.75V9A6 6 
                         0 0 0 6 9v.75a8.967 8.967 0 0 1-2.312 6.022
                         c1.733.64 3.56 1.085 5.455 1.31m5.714 
                         0a24.255 24.255 0 0 1-5.714 
                         0m5.714 0a3 3 0 1 1-5.714 0" />
                        </svg>
                    </button>

                    <!-- Profile dropdown -->
                    <el-dropdown>
                        <button class="flex items-center rounded-full focus:outline-none">
                            <img class="h-8 w-8 rounded-full"
                                src="https://images.unsplash.com/photo-1472099645785-5658abf4ff4e" alt="User" />
                        </button>
                        <template #dropdown>
                            <el-dropdown-menu>
                                <el-dropdown-item>Profile</el-dropdown-item>
                                <el-dropdown-item>Settings</el-dropdown-item>
                                <el-dropdown-item divided>Sign out</el-dropdown-item>
                            </el-dropdown-menu>
                        </template>
                    </el-dropdown>

                    <!-- Mobile menu button -->
                    <button @click="isOpen = !isOpen" class="sm:hidden p-2 rounded-md text-gray-500 hover:text-gray-900 hover:bg-gray-100
                     dark:text-gray-400 dark:hover:text-white dark:hover:bg-gray-700">
                        <span class="sr-only">Open menu</span>
                        <svg v-if="!isOpen" class="h-6 w-6" fill="none" stroke="currentColor" stroke-width="1.5"
                            viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" d="M4 6h16M4 12h16M4 18h16" />
                        </svg>
                        <svg v-else class="h-6 w-6" fill="none" stroke="currentColor" stroke-width="1.5"
                            viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" d="M6 18L18 6M6 6l12 12" />
                        </svg>
                    </button>
                </div>
            </div>
        </div>

        <!-- Mobile menu -->
        <div v-if="isOpen" class="sm:hidden px-2 pb-3 space-y-1">
            <!-- <a href="#" class="block px-3 py-2 rounded-md text-base font-medium
                  text-gray-900 bg-gray-100 hover:bg-gray-200
                  dark:text-gray-200 dark:bg-gray-800 dark:hover:bg-gray-700">
                Nutrición
            </a> -->
            <router-link to="/nutricion" class="block px-3 py-2 rounded-md text-base font-medium
                  text-gray-900 bg-gray-100 hover:bg-gray-200
                  dark:text-gray-200 dark:bg-gray-800 dark:hover:bg-gray-700"> Nutrición</router-link>

            <router-link to="/recetas" class="block px-3 py-2 rounded-md text-base font-medium
                  text-gray-600 hover:bg-gray-100 hover:text-gray-900
                  dark:text-gray-300 dark:hover:bg-gray-700 dark:hover:text-white">Recetas</router-link>

            <router-link to="/about" class="block px-3 py-2 rounded-md text-base font-medium
                  text-gray-600 hover:bg-gray-100 hover:text-gray-900
                  dark:text-gray-300 dark:hover:bg-gray-700 dark:hover:text-white">Acerca de</router-link>
        </div>
    </nav>
</template>


<script>
export default {
    name: "Topbar",
    data() {
        return {
            isOpen: false,
            isDark: false,
        };
    },
    mounted() {
        // Al cargar el componente, revisa si ya hay preferencia guardada
        if (localStorage.theme === "dark") {
            this.isDark = true;
            document.documentElement.classList.add("dark");
        } else {
            this.isDark = false;
            document.documentElement.classList.remove("dark");
        }
    },
    methods: {
        toggleDarkMode() {
            this.isDark = !this.isDark;
            if (this.isDark) {
                document.documentElement.classList.add("dark");
                localStorage.theme = "dark";
            } else {
                document.documentElement.classList.remove("dark");
                localStorage.theme = "light";
            }
        },
    },
};
</script>