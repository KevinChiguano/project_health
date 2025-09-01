package com.server.health_server.model;

import java.util.List;

import lombok.Data;

@Data
public class Receta {

    private String nombre;
    private String imagen;
    private String fuente;
    private List<String> ingredientes;
    private Nutricion nutricion;
    
}
