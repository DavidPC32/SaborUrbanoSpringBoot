package com.saborurbano.restaurante.service.Categoria;

import java.util.List;
import com.saborurbano.restaurante.model.Categoria;

public interface CategoriaServiceInt {

    Categoria registrarCategoria(Categoria categoria);

    List<Categoria> getAllCategorias();

    Categoria getCategoriaById(Long id);

    void deleteCategoria(Long id);
}
