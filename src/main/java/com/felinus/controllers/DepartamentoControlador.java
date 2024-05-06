package com.felinus.controllers;


import com.felinus.models.Departamento;
import com.felinus.service.DepartamentoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
//http:/localhost:8080/felinus-app
@RequestMapping("felinus-app")
public class DepartamentoControlador {

    private static final Logger logger = LoggerFactory.getLogger(DepartamentoControlador.class);

    @Autowired
    private DepartamentoService departamentoService;

    @GetMapping("/departamentos")
    public List<Departamento> obtenerUsuarios(){
        List<Departamento> productos = this.departamentoService.listarDepartamentos();
        logger.info("Usuarios obtenidos: ");
        productos.forEach( usuario -> logger.info(usuario.toString()));
        return productos;
    }
}
