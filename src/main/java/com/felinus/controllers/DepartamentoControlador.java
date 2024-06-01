package com.felinus.controllers;


import com.felinus.exceptions.RecursoNoEncontradoException;
import com.felinus.models.Departamento;
import com.felinus.models.Empleado;
import com.felinus.service.DepartamentoService;
import org.apache.coyote.Response;
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
    public List<Departamento> obtenerDepartamentos(){
        List<Departamento> departamentos = this.departamentoService.listarDepartamentos();
        logger.info("Departamentos obtenidos: ");
        departamentos.forEach( depto -> logger.info(depto.toString()));
        return departamentos;
    }

    @GetMapping("/departamentos/{nombre}")
    public ResponseEntity<Departamento> obtenerDepartamentoPorId(@PathVariable String nombre){
        Departamento departamento = this.departamentoService.buscarDepartamentoPorId(nombre);
        if(departamento != null) {
            return ResponseEntity.ok(departamento);
        } else {
            throw new RecursoNoEncontradoException("No se encontro el depto: " + nombre );
        }
    }

    @PostMapping(path = "/departamentos")
    public ResponseEntity<Departamento> agregarDepartamento(@RequestBody Departamento departamento){
        logger.info("Departamento a agregar: " + departamento);
        return ResponseEntity.ok(this.departamentoService.guardarDepartamento(departamento));
    }

    @PutMapping(path = "/departamentos/{nombre}")
    public ResponseEntity<Departamento> actualizarDepartamento(@PathVariable String nombre, @RequestBody Departamento deptoRecibido){
        Departamento departamento = this.departamentoService.buscarDepartamentoPorId(nombre);
        if(departamento == null){
            throw new RecursoNoEncontradoException("No se encontro el id: " + nombre);
        }
        departamento.setDescripcion(deptoRecibido.getDescripcion());

        this.departamentoService.guardarDepartamento(departamento);
        return ResponseEntity.ok(departamento);
    }

    @DeleteMapping("/departamentos/{nombre}")
    public ResponseEntity<Map<String, Boolean>> eliminarDepartamento(@PathVariable String nombre){
        Departamento departamento = this.departamentoService.buscarDepartamentoPorId(nombre);
        if(departamento == null){
            throw new RecursoNoEncontradoException("No se encontro el id: " + nombre);
        }
        this.departamentoService.eliminarDepartamentoPorId(departamento.getNombre());
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}
