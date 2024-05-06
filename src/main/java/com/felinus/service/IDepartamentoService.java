package com.felinus.service;

import com.felinus.models.Departamento;

import java.util.List;

public interface IDepartamentoService {

    public List<Departamento> listarDepartamentos();
    public Departamento buscarClientePorId(Integer idDepartamento);
    public Departamento guardarCliente(Departamento departamento);
    public void eliminarClientePorId(Integer idDepartamento);
}
