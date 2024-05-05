package com.felinus.controllers;

import com.felinus.models.Empleado;
import com.felinus.models.Usuario;
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

    @GetMapping("/usuarios")
    public List<Usuario> obtenerUsuarios(){
        List<Usuario> productos = usuarioServicio.listarUsuarios();
        logger.info("usuarios obtenidos: ");
        productos.forEach( producto -> logger.info(producto.toString()));
        return productos;
    }

    @GetMapping("/empleados")
    public List<Empleado> obtenerEmpleados(){
        List<Empleado> empleados = empleadoService.listarUsuarios();
        logger.info("usuarios obtenidos: ");
        empleados.forEach( empleado -> logger.info(empleado.toString()));
        return empleados;
    }

    @PostMapping("/usuarios")
    public Usuario agregarProducto(@RequestBody Usuario usuario){
        logger.info("Usuario A agregar: " + usuario);
        return this.usuarioServicio.guardarUsuario(usuario);
    }

}
