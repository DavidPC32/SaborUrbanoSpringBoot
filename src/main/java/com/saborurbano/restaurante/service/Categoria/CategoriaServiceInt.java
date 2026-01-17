package com.saborurbano.restaurante.service.Categoria;

import java.util.List;
import com.saborurbano.restaurante.dtos.CategoriaBasicoDto;

public interface CategoriaServiceInt {

    CategoriaBasicoDto registrarCategoria(CategoriaBasicoDto categoria);

    List<CategoriaBasicoDto> getAllCategorias();

    CategoriaBasicoDto getCategoriaById(Long id);

    void deleteCategoria(Long id);
}
