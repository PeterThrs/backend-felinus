package com.felinus.service;

import com.felinus.models.Inventario;

import java.util.List;

public interface IInventarioService {

    public List<Inventario> listarAlmacen();
    public Inventario buscarMaterialPorId(Integer idPrenda);
    public Inventario guardarMaterial(Inventario prenda);
    public void eliminarMaterialPorId(Integer idPrenda);

}
