package com.felinus.service;

import com.felinus.models.Orden;

import java.util.List;

public interface IOrdenService {

    public List<Orden> listarOrdenes();
    public Orden buscarOrdenPorId(Integer idOrden);
    public Orden guardarOrden(Orden orden);
    public void eliminarOrdenPorId(Integer idOrden);
}
