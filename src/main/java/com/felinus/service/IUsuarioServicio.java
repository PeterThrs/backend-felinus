package com.felinus.service;

import com.felinus.models.Usuario;

import java.util.List;

public interface IUsuarioServicio {

    public List<Usuario> listarUsuarios();
    public Usuario buscarUsuarioPorId(String curp);
    public Usuario guardarUsuario(Usuario usuario);
    public void eliminarUsuarioPorId(String curp);

}
