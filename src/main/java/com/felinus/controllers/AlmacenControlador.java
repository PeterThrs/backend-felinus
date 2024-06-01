package com.felinus.controllers;

import com.felinus.exceptions.RecursoNoEncontradoException;
import com.felinus.models.Inventario;
import com.felinus.service.InventarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class AlmacenControlador {

    private static final Logger logger = LoggerFactory.getLogger(AlmacenControlador.class);


    @Autowired
    private InventarioService inventarioService;

    //ENDPOINTS EMPLEADOS
    @GetMapping("/materiales")
    public List<Inventario> obtenerMateriales(){
        List<Inventario> almacen = inventarioService.listarAlmacen();
        logger.info("Materiales obtenidos: ");
        almacen.forEach( material -> logger.info(material.toString()));
        return almacen;
    }

    @GetMapping("/materiales/{codigo}")
    public ResponseEntity<Inventario> obtenerMaterialPorId(@PathVariable String codigo){
        Inventario material = this.inventarioService.buscarMaterialPorId(codigo);
        if(material != null) {
            return ResponseEntity.ok(material);
        } else {
            throw new RecursoNoEncontradoException("No se encontro el id: " + codigo);
        }
    }

    @PostMapping(path = "/materiales")
    public ResponseEntity<Inventario> agregarMaterial(@RequestBody Inventario material){
        logger.info("Material a agregar: " + material);
        return ResponseEntity.ok(this.inventarioService.guardarMaterial(material));
    }

    @PutMapping(path = "/materiales/{codigo}")
    public ResponseEntity<Inventario> actualizarMaterial(@PathVariable String codigo, @RequestBody Inventario materialRecibido){
        Inventario material = this.inventarioService.buscarMaterialPorId(codigo);
        if(material == null){
            throw new RecursoNoEncontradoException("No se encontro el id: " + codigo);
        }
        material.setNombre(materialRecibido.getNombre());
        material.setDescripcion(materialRecibido.getDescripcion());
        material.setUnidadMedida(materialRecibido.getUnidadMedida());
        material.setCantidad(materialRecibido.getCantidad());

        this.inventarioService.guardarMaterial(material);
        return ResponseEntity.ok(material);
    }

    @DeleteMapping("/materiales/{codigo}")
    public ResponseEntity<Map<String, Boolean>> eliminarMaterial(@PathVariable String codigo){
        Inventario material = inventarioService.buscarMaterialPorId(codigo);
        if(material == null){
            throw new RecursoNoEncontradoException("No se encontro el id: " + codigo);
        }
        this.inventarioService.eliminarMaterialPorId(material.getCodigo());
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }

}
