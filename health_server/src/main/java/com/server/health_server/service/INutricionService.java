package com.server.health_server.service;

import java.io.File;

import com.server.health_server.model.Nutricion;

public interface INutricionService {

    public Nutricion analizarPorTexto(String texto);
    public Nutricion analizarPorPlato(String plato);
    public String identificarAlimento(File file);
    
}
