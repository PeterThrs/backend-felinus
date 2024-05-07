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
    public Departamento buscarDepartamentoPorId(Integer idDepartamento) {
        return this.departamentoRepositorio.findById(idDepartamento).orElse(null);
    }

    @Override
    public Departamento guardarDepartamento(Departamento departamento) {
        return this.departamentoRepositorio.save(departamento);
    }

    @Override
    public void eliminarDepartamentoPorId(Integer idDepartamento) {
        this.departamentoRepositorio.deleteById(idDepartamento);
    }
}
