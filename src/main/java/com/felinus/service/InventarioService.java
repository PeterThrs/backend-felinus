package com.felinus.service;

import com.felinus.models.Inventario;
import com.felinus.repository.InventarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventarioService implements IInventarioService {

    @Autowired
    private InventarioRepositorio inventarioRepositorio;

    @Override
    public List<Inventario> listarAlmacen() {
        return this.inventarioRepositorio.findAll();
    }

    @Override
    public Inventario buscarMaterialPorId(Integer idPrenda) {
        return this.inventarioRepositorio.findById(idPrenda).orElse(null);
    }

    @Override
    public Inventario guardarMaterial(Inventario prenda) {
        return this.inventarioRepositorio.save(prenda);
    }

    @Override
    public void eliminarMaterialPorId(Integer idPrenda) {
        this.inventarioRepositorio.deleteById(idPrenda);
    }
}
