package com.felinus.service;

import com.felinus.models.Departamento;
import com.felinus.repository.DepartamentoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartamentoService implements IDepartamentoService{

    @Autowired
    private DepartamentoRepositorio departamentoRepositorio;

    @Override
    public List<Departamento> listarDepartamentos() {
        return this.departamentoRepositorio.findAll();
    }

    @Override
    public Departamento buscarDepartamentoPorId(String nombre) {
        return this.departamentoRepositorio.findById(nombre).orElse(null);
    }

    @Override
    public Departamento guardarDepartamento(Departamento departamento) {
        return this.departamentoRepositorio.save(departamento);
    }

    @Override
    public void eliminarDepartamentoPorId(String nombre) {
        this.departamentoRepositorio.deleteById(nombre);
    }
}
