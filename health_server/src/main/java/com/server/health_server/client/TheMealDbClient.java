package com.server.health_server.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.server.health_server.model.Receta;

import lombok.RequiredArgsConstructor;

@Component
public class TheMealDbClient {

    private WebClient webClient = WebClient.create("https://www.themealdb.com/api/json/v1/1/");

    public List<Receta> buscarPorIngrediente(String ingredientes) {
        List<Receta> recetas = new ArrayList<>();

        // separar por coma y limpiar espacios
        String[] ingArray = ingredientes.split(",");

        for (String ing : ingArray) {
            ing = ing.trim();
            String url = "filter.php?i=" + ing;

            Map response = webClient.get()
                    .uri(url)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();

            List<Map<String, Object>> meals = (List<Map<String, Object>>) response.get("meals");

            if (meals != null) {
                for (Map<String, Object> meal : meals) {
                    String mealId = (String) meal.get("idMeal");

                    // 🔎 segundo request para traer detalles completos
                    Map detailResponse = webClient.get()
                            .uri("lookup.php?i=" + mealId)
                            .retrieve()
                            .bodyToMono(Map.class)
                            .block();

                    List<Map<String, Object>> detailedMeals = (List<Map<String, Object>>) detailResponse.get("meals");

                    if (detailedMeals != null && !detailedMeals.isEmpty()) {
                        Map<String, Object> detailedMeal = detailedMeals.get(0);

                        Receta receta = new Receta();
                        receta.setNombre((String) detailedMeal.get("strMeal"));
                        receta.setImagen((String) detailedMeal.get("strMealThumb"));
                        receta.setFuente(mealId);

                        // ✅ extraer ingredientes (strIngredient1 ... strIngredient20)
                        List<String> ingredientesList = new ArrayList<>();
                        for (int i = 1; i <= 20; i++) {
                            String ingKey = "strIngredient" + i;
                            String ingValue = (String) detailedMeal.get(ingKey);
                            if (ingValue != null && !ingValue.isBlank()) {
                                ingredientesList.add(ingValue);
                            }
                        }
                        receta.setIngredientes(ingredientesList);

                        recetas.add(receta);
                    }
                }
            }
        }

        System.out.println("Recetas encontradas: " + recetas.size());
        recetas.forEach(e -> System.out.println(e.getNombre() + " -> " + e.getIngredientes()));

        return recetas;
    }

    public List<String> buscarIngredientesPorPlato(String plato) {
        String url = "search.php?s=" + plato;

        Map response = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        List<Map<String, Object>> meals = (List<Map<String, Object>>) response.get("meals");

        if (meals != null && !meals.isEmpty()) {
            Map<String, Object> detailedMeal = meals.get(0);

            List<String> ingredientesList = new ArrayList<>();
            for (int i = 1; i <= 20; i++) {
                String ingKey = "strIngredient" + i;
                String ingValue = (String) detailedMeal.get(ingKey);
                if (ingValue != null && !ingValue.isBlank()) {
                    ingredientesList.add(ingValue);
                }
            }
            return ingredientesList;
        }

        return List.of();
    }

}
