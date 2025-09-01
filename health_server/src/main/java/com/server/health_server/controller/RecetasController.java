package com.server.health_server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.server.health_server.model.Receta;
import com.server.health_server.service.IRecetaService;

@RestController
@RequestMapping("/recetas")
public class RecetasController {

    @Autowired
    private IRecetaService recetaService;

    @GetMapping
    public ResponseEntity<List<Receta>> getRecetas(@RequestParam String ingredientes){
        List<Receta> recetas = recetaService.buscarRecetas(ingredientes);
        return ResponseEntity.ok(recetas);
    }
    
}
