package com.felinus.service;

import com.felinus.models.Empleado;
import com.felinus.repository.EmpleadoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoService implements IEmpleadoService{

    @Autowired
    private EmpleadoRepositorio empleadoRepositorio;

    @Override
    public List<Empleado> listarUsuarios() {
        return this.empleadoRepositorio.findAll();
    }

    @Override
    public Empleado buscarUsuarioPorId(Integer idUsuario) {
        return null;
    }

    @Override
    public Empleado guardarUsuario(Empleado empleado) {
        return null;
    }

    @Override
    public void eliminarUsuarioPorId(Integer idUsuario) {

    }
}
