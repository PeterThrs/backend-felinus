package com.felinus.service;


import com.felinus.models.MovimientoInventario;

import java.util.List;

public interface IMovimientoInventarioService {

    public List<MovimientoInventario> listarMovimientos();
    public MovimientoInventario buscarMovimientoPorId(String clave);
    public MovimientoInventario guardarMovimiento(MovimientoInventario movimientoInventario);
    public void eliminarMovimientoPorId(String clave);
}
