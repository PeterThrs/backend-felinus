package com.felinus.service;

import com.felinus.models.Usuario;

import java.util.List;

public interface IUsuarioServicio {

    public List<Usuario> listarUsuarios();
    public Usuario buscarUsuarioPorId(Integer idUsuario);
    public Usuario guardarUsuario(Usuario usuario);
    public void eliminarUsuarioPorId(Integer idUsuario);
}
