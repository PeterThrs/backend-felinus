package com.felinus.service;

import com.felinus.models.Usuario;
import com.felinus.repository.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServicio implements IUsuarioServicio{

    @Autowired
    private UsuarioRepositorio usuarioRepositorio; //inyeccion de objeto

    @Override
    public List<Usuario> listarUsuarios() {
        return this.usuarioRepositorio.findAll();
    }

    @Override
    public Usuario buscarUsuarioPorId(Integer idUsuario) {
        return this.usuarioRepositorio.findById(idUsuario).orElse(null);
    }

    @Override
    public Usuario guardarUsuario(Usuario usuario) {
        //si el id es null se guarda, caso contrario se actualiza
        return this.usuarioRepositorio.save(usuario);
    }

    @Override
    public void eliminarUsuarioPorId(Integer idUsuario) {
        this.usuarioRepositorio.deleteById(idUsuario);
    }
}
