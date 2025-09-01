package com.server.health_server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.server.health_server.client.TheMealDbClient;
import com.server.health_server.client.UsdaClient;
import com.server.health_server.model.Receta;
import com.server.health_server.service.IRecetaService;

@Service
public class RecetaServiceImpl implements IRecetaService{

    @Autowired
    private TheMealDbClient mealDbClient;

    @Autowired
    private UsdaClient usdaClient;

    @Override
    public List<Receta> buscarRecetas(String ingredientes) {

        System.out.println("Ingredientes: "+ ingredientes);
        
        List<Receta> recetas = mealDbClient.buscarPorIngrediente(ingredientes);

        for(Receta receta: recetas){
            receta.setNutricion(usdaClient.calcularNutricion(receta.getIngredientes()));
        }

        return recetas;

    }

    
    
}
