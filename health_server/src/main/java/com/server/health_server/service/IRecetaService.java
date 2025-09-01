package com.server.health_server.service;

import java.util.List;

import com.server.health_server.model.Receta;

public interface IRecetaService {

    public List<Receta> buscarRecetas(String ingredientes);
    
}
