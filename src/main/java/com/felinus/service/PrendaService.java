package com.felinus.service;

import com.felinus.models.Prenda;
import com.felinus.repository.PrendaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrendaService implements IPrendaService {

    @Autowired
    private PrendaRepositorio prendaRepositorio;

    @Override
    public List<Prenda> listarPrendas() {
        return this.prendaRepositorio.findAll();
    }

    @Override
    public Prenda buscarPrendaPorId(Integer idPrenda) {
        return this.prendaRepositorio.findById(idPrenda).orElse(null);
    }

    @Override
    public Prenda guardarPrenda(Prenda prenda) {
        return this.prendaRepositorio.save(prenda);
    }

    @Override
    public void eliminarPrendaPorId(Integer idPrenda) {
        this.prendaRepositorio.deleteById(idPrenda);
    }
}
