package com.server.health_server.controller;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.server.health_server.model.Nutricion;
import com.server.health_server.service.INutricionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/nutricion")
public class NutricionController {

    @Autowired
    private INutricionService nutricionService;

    @PostMapping("/texto")
    public ResponseEntity<Nutricion> analizarTexto(@RequestBody Map<String, String> request) {
        
        String descripcion = request.get("descripcion");
        Nutricion nutricion = nutricionService.analizarPorTexto(descripcion);
        
        return ResponseEntity.ok(nutricion);
    }
    
    @PostMapping("/foto")
    public ResponseEntity<Nutricion> analizarFoto(@RequestParam("file") MultipartFile file) throws IOException {
        
        File temFile = File.createTempFile("upload-", ".jpg");
        file.transferTo(temFile);

        String plato = nutricionService.identificarAlimento(temFile);
        System.out.println("--------------------------------------------");
        System.out.println(plato);

        Nutricion nutricion = nutricionService.analizarPorPlato(plato);

        return ResponseEntity.ok(nutricion);

    }
    

    
}
