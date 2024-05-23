package com.felinus.service;

import com.felinus.models.Prenda;

import java.util.List;

public interface IPrendaService {

    public List<Prenda> listarPrendas();
    public Prenda buscarPrendaPorId(Integer idPrenda);
    public Prenda guardarPrenda(Prenda prenda);
    public void eliminarPrendaPorId(Integer idPrenda);
}
