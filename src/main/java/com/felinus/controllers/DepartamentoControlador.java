package com.felinus.controllers;


import com.felinus.exceptions.RecursoNoEncontradoException;
import com.felinus.models.Departamento;
import com.felinus.models.Empleado;
import com.felinus.service.DepartamentoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//http:/localhost:8080/felinus-app
@RequestMapping("felinus-app")
//configuramos para que angular pueda realizar peticiones a nuestro backend
@CrossOrigin(value="http://localhost:4200")
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

    @GetMapping("/departamentos/{id}")
    public ResponseEntity<Departamento> obtenerDepartamentoPorId(@PathVariable int id){
        Departamento departamento = this.departamentoService.buscarDepartamentoPorId(id);
        if(departamento != null) {
            return ResponseEntity.ok(departamento);
        } else {
            throw new RecursoNoEncontradoException("No se encontro el id: " + id);
        }
    }

    @PostMapping(path = "/departamentos", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Departamento agregarDepartamento(@RequestBody Departamento departamento){
        logger.info("Departamento a agregar: " + departamento);
        return this.departamentoService.guardarDepartamento(departamento);
    }

    @PutMapping(path = "/departamentos/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Departamento> actualizarDepartamento(@PathVariable int id, @RequestBody Departamento deptoRecibido){
        Departamento departamento = this.departamentoService.buscarDepartamentoPorId(id);
        if(departamento == null){
            throw new RecursoNoEncontradoException("No se encontro el id: " + id);
        }
        departamento.setNombre(deptoRecibido.getNombre());
        departamento.setDescripcion(deptoRecibido.getDescripcion());

        this.departamentoService.guardarDepartamento(departamento);
        return ResponseEntity.ok(departamento);
    }

    @DeleteMapping("/departamentos/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarDepartamento(@PathVariable int id){
        Departamento departamento = this.departamentoService.buscarDepartamentoPorId(id);
        if(departamento == null){
            throw new RecursoNoEncontradoException("No se encontro el id: " + id);
        }
        this.departamentoService.eliminarDepartamentoPorId(departamento.getIdDepto());
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}
