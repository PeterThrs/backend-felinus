package com.felinus.service;

import com.felinus.models.Cliente;

import java.util.List;

public interface IClienteService {
    public List<Cliente> listarClientes();
    public Cliente buscarClientePorId(Integer idCliente);
    public Cliente guardarCliente(Cliente cliente);
    public void eliminarClientePorId(Integer idCliente);
}
