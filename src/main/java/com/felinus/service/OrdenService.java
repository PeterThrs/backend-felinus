package com.felinus.service;

import com.felinus.models.Orden;
import com.felinus.repository.OrdenRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdenService implements IOrdenService{

    @Autowired
    private OrdenRepositorio ordenRepositorio;

    @Override
    public List<Orden> listarOrdenes() {
        return this.ordenRepositorio.findAll();
    }

    @Override
    public Orden buscarOrdenPorId(Integer idOrden) {
        return this.ordenRepositorio.findById(idOrden).orElse(null);
    }

    @Override
    public Orden guardarOrden(Orden orden) {
        return this.ordenRepositorio.save(orden);
    }

    @Override
    public void eliminarOrdenPorId(Integer idOrden) {
        this.ordenRepositorio.deleteById(idOrden);
    }
}
