package com.felinus.service;

import com.felinus.models.Cliente;
import com.felinus.repository.ClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService implements IClienteService {

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Override
    public List<Cliente> listarClientes() {
        return this.clienteRepositorio.findAll();
    }

    @Override
    public Cliente buscarClientePorId(String curp) {
        return this.clienteRepositorio.findById(curp).orElse(null);
    }

    @Override
    public Cliente guardarCliente(Cliente cliente) {
        return this.clienteRepositorio.save(cliente);
    }

    @Override
    public void eliminarClientePorId(String curp) {
        this.clienteRepositorio.deleteById(curp);
    }
}
