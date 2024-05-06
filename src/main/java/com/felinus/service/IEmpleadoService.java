package com.felinus.service;

import com.felinus.models.Empleado;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IEmpleadoService{

    public List<Empleado> listarEmpleados();
    public Empleado buscarEmpleadoPorId(Integer idEmpleado);
    public Empleado guardarEmpleado(Empleado empleado);
    public void eliminarEmpleadoPorId(Integer idEmpleado);

}
