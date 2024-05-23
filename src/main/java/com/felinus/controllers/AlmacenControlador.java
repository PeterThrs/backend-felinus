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

    @GetMapping("/materiales/{id}")
    public ResponseEntity<Inventario> obtenerMaterialPorId(@PathVariable int id){
        Inventario material = this.inventarioService.buscarMaterialPorId(id);
        if(material != null) {
            return ResponseEntity.ok(material);
        } else {
            throw new RecursoNoEncontradoException("No se encontro el id: " + id);
        }
    }

    @PostMapping(path = "/materiales")
    public ResponseEntity<Inventario> agregarMaterial(@RequestBody Inventario material){
        logger.info("Material a agregar: " + material);
        return ResponseEntity.ok(this.inventarioService.guardarMaterial(material));
    }

    @PutMapping(path = "/materiales/{id}")
    public ResponseEntity<Inventario> actualizarMaterial(@PathVariable int id, @RequestBody Inventario materialRecibido){
        Inventario material = this.inventarioService.buscarMaterialPorId(id);
        if(material == null){
            throw new RecursoNoEncontradoException("No se encontro el id: " + id);
        }
        material.setNombre(materialRecibido.getNombre());
        material.setMetros(materialRecibido.getMetros());
        material.setDisponible(materialRecibido.isDisponible());

        this.inventarioService.guardarMaterial(material);
        return ResponseEntity.ok(material);
    }

    @DeleteMapping("/materiales/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarMaterial(@PathVariable int id){
        Inventario material = inventarioService.buscarMaterialPorId(id);
        if(material == null){
            throw new RecursoNoEncontradoException("No se encontro el id: " + id);
        }
        this.inventarioService.eliminarMaterialPorId(material.getIdMaterial());
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }

}
