package com.felinus.service;

import com.felinus.models.Empleado;

import java.util.List;

public interface IEmpleadoService{

    public List<Empleado> listarUsuarios();
    public Empleado buscarUsuarioPorId(Integer idUsuario);
    public Empleado guardarUsuario(Empleado empleado);
    public void eliminarUsuarioPorId(Integer idUsuario);

}
