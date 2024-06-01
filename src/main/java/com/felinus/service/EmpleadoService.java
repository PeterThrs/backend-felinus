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
    public List<Empleado> listarEmpleados() {
        return this.empleadoRepositorio.findAll();
    }

    @Override
    public Empleado buscarEmpleadoPorId(String curp) {
        return this.empleadoRepositorio.findById(curp).orElse(null);
    }

    @Override
    public Empleado guardarEmpleado(Empleado empleado) {
        return this.empleadoRepositorio.save(empleado);
    }

    @Override
    public void eliminarEmpleadoPorId(String curp) {
        this.empleadoRepositorio.deleteById(curp);
    }
}
