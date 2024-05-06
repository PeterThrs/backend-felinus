package com.felinus.controllers;

import com.felinus.models.Cliente;
import com.felinus.models.Empleado;
import com.felinus.models.Usuario;
import com.felinus.service.ClienteService;
import com.felinus.service.EmpleadoService;
import com.felinus.service.UsuarioServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//http:/localhost:8080/felinus-app
@RequestMapping("felinus-app")
public class UsuarioControlador {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioControlador.class);

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/usuarios")
    public List<Usuario> obtenerUsuarios(){
        List<Usuario> productos = usuarioServicio.listarUsuarios();
        logger.info("Usuarios obtenidos: ");
        productos.forEach( usuario -> logger.info(usuario.toString()));
        return productos;
    }

    @GetMapping("/empleados")
    public List<Empleado> obtenerEmpleados(){
        List<Empleado> empleados = empleadoService.listarEmpleados();
        logger.info("Empleados obtenidos: ");
        empleados.forEach( empleado -> logger.info(empleado.toString()));
        return empleados;
    }

    @GetMapping("/clientes")
    public List<Cliente> obtenerCliente(){
        List<Cliente> clientes = clienteService.listarClientes();
        logger.info("Clientes obtenidos: ");
        clientes.forEach( empleado -> logger.info(empleado.toString()));
        return clientes;
    }

    @PostMapping("/usuarios")
    public Usuario agregarProducto(@RequestBody Usuario usuario){
        logger.info("Usuario A agregar: " + usuario);
        return this.usuarioServicio.guardarUsuario(usuario);
    }

}
