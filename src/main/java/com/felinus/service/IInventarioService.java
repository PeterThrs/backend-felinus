package com.felinus.service;

import com.felinus.models.Inventario;

import java.util.List;

public interface IInventarioService {

    public List<Inventario> listarAlmacen();
    public Inventario buscarMaterialPorId(String codigo);
    public Inventario guardarMaterial(Inventario prenda);
    public void eliminarMaterialPorId(String codigo);

}
