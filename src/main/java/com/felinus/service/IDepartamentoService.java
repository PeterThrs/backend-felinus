package com.felinus.service;

import com.felinus.models.Departamento;

import java.util.List;

public interface IDepartamentoService {

    public List<Departamento> listarDepartamentos();
    public Departamento buscarDepartamentoPorId(Integer idDepartamento);
    public Departamento guardarDepartamento(Departamento departamento);
    public void eliminarDepartamentoPorId(Integer idDepartamento);
}
