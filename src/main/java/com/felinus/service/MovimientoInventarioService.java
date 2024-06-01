package com.felinus.service;

import com.felinus.models.MovimientoInventario;
import com.felinus.repository.MovimientoInventarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovimientoInventarioService implements IMovimientoInventarioService{

    @Autowired
    private MovimientoInventarioRepositorio movimientoInventarioRepositorio;

    @Override
    public List<MovimientoInventario> listarMovimientos() {
        return this.movimientoInventarioRepositorio.findAll();
    }

    @Override
    public MovimientoInventario buscarMovimientoPorId(String clave) {
        return this.movimientoInventarioRepositorio.findById(clave).orElse(null);
    }

    @Override
    public MovimientoInventario guardarMovimiento(MovimientoInventario movimientoInventario) {
        return this.movimientoInventarioRepositorio.save(movimientoInventario);
    }

    @Override
    public void eliminarMovimientoPorId(String clave) {
        this.movimientoInventarioRepositorio.deleteById(clave);
    }
}
