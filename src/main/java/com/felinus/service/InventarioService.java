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
    public Inventario buscarMaterialPorId(String codigo) {
        return this.inventarioRepositorio.findById(codigo).orElse(null);
    }

    @Override
    public Inventario guardarMaterial(Inventario material) {
        return this.inventarioRepositorio.save(material);
    }

    @Override
    public void eliminarMaterialPorId(String codigo) {
        this.inventarioRepositorio.deleteById(codigo);
    }
}
