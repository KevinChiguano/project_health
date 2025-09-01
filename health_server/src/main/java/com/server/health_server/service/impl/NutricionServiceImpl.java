package com.server.health_server.service.impl;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.server.health_server.client.TheMealDbClient;
import com.server.health_server.client.UsdaClient;
import com.server.health_server.model.Nutricion;
import com.server.health_server.service.INutricionService;

@Service
public class NutricionServiceImpl implements INutricionService {

    @Autowired
    private UsdaClient usdaClient;

    @Autowired
    private TheMealDbClient theMealDbClient;

    @Override
    public Nutricion analizarPorTexto(String texto) {

        List<String> ingredientes = Arrays.asList(texto.split(",| y | "));
        System.out.println("Ingredientes");
        ingredientes.forEach(e -> System.out.println(e));
        return usdaClient.calcularNutricion(ingredientes);
    }

    @Override
    public String identificarAlimento(File file) {

        try {
            WebClient client = WebClient.create("http://localhost:5000");

            MultipartBodyBuilder builder = new MultipartBodyBuilder();
            builder.part("file", new FileSystemResource(file))
                    .contentType(MediaType.IMAGE_JPEG);

            Map response = client.post()
                    .uri("/predict")
                    .contentType(MediaType.MULTIPART_FORM_DATA)
                    .body(BodyInserters.fromMultipartData(builder.build()))
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();
            return (String) response.get("alimento");
        } catch (Exception e) {
            throw new RuntimeException("Error identificando alimento", e);
        }

    }

    @Override
    public Nutricion analizarPorPlato(String plato) {

        List<String> ingredientes = theMealDbClient.buscarIngredientesPorPlato(plato);

        return usdaClient.calcularNutricion(ingredientes);
    }

}
