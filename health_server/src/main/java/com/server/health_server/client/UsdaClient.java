package com.server.health_server.client;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.server.health_server.model.Nutricion;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class UsdaClient {

    private final WebClient webClient = WebClient.create("https://api.nal.usda.gov/fdc/v1/");
    private final String apiKey = "GNuQjpCzXCU3veW3Tggb3DFKp5bbUVA4KTSrYv3k";

    // Cantidad por defecto por ingrediente en gramos
    private final double CANTIDAD_POR_DEFECTO = 50.0;

    public Nutricion calcularNutricion(List<String> ingredientes) {

        Nutricion total = new Nutricion();

        Flux.fromIterable(ingredientes)
                .flatMap(ingrediente -> {

                    String url = "foods/search?query=" + ingrediente + "&pageSize=1&api_key=" + apiKey;

                    return webClient.get()
                            .uri(url)
                            .retrieve()
                            .bodyToMono(Map.class)
                            .onErrorResume(e -> {
                                System.err
                                        .println("Error buscando ingrediente '" + ingrediente + "': " + e.getMessage());
                                return Mono.just(Map.of());
                            });

                })
                .collectList()
                .block()
                .forEach(response -> {

                    List<Map<String, Object>> foods = (List<Map<String, Object>>) response.get("foods");

                    if (foods != null && !foods.isEmpty()) {
                        Map<String, Object> food = foods.get(0);
                        List<Map<String, Object>> nutrients = (List<Map<String, Object>>) food.get("foodNutrients");

                        for (Map<String, Object> nutrient : nutrients) {
                            String name = (String) nutrient.get("nutrientName");
                            Double value = nutrient.get("value") != null
                                    ? ((Number) nutrient.get("value")).doubleValue()
                                    : 0.0;

                            // Ajustar por cantidad (g) / 100
                            double ajustado = value * (CANTIDAD_POR_DEFECTO / 100.0);

                            if (name.contains("Energy"))
                                total.setCalorias(total.getCalorias() + ajustado);
                            else if (name.contains("Protein"))
                                total.setProteinas(total.getProteinas() + ajustado);
                            else if (name.contains("Total lipid"))
                                total.setGrasas(total.getGrasas() + ajustado);
                            else if (name.contains("Carbohydrate"))
                                total.setCarbohidratos(total.getCarbohidratos() + ajustado);
                        }
                    }

                });

        return total;
    }

}
