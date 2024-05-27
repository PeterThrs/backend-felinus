package com.felinus.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.felinus.exceptions.RecursoNoEncontradoException;
import com.felinus.models.Prenda;
import com.felinus.service.PrendaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//http:/localhost:8080/felinus-app
@RequestMapping("felinus-app")
//configuramos para que angular pueda realizar peticiones a nuestro backend
@CrossOrigin(value="http://localhost:4200")
public class PrendaControlador {

    private static final Logger logger = LoggerFactory.getLogger(PrendaControlador.class);

    @Autowired
    private PrendaService prendaService;
    //ENDPOINTS EMPLEADOS
    @GetMapping("/prendas")
    public List<Prenda> obtenerPrendas(){
        List<Prenda> prendas = prendaService.listarPrendas();
        logger.info("Prendas obtenidos: ");
        prendas.forEach( prenda -> logger.info(prenda.toString()));
        return prendas;
    }

    @GetMapping("/prendas/{id}")
    public ResponseEntity<Prenda> obtenerPrendaPorId(@PathVariable int id){
        Prenda prenda = this.prendaService.buscarPrendaPorId(id);
        if(prenda != null) {
            return ResponseEntity.ok(prenda);
        } else {
            throw new RecursoNoEncontradoException("No se encontro el id: " + id);
        }
    }

//    @PostMapping(path = "/prendas", consumes = { "multipart/form-data" })
//    public ResponseEntity<Prenda> agregarPrenda(
//            @RequestParam("prenda") String prendaJson,
//            @RequestParam("logo") MultipartFile logo) throws IOException, SQLException {
//
//        logger.info("Prenda a agregar: " + prendaJson);
//        ObjectMapper objectMapper = new ObjectMapper();
//        Prenda prenda = objectMapper.readValue(prendaJson, Prenda.class);
//
//        if (!logo.isEmpty()) {
//            byte[] logoBytes = logo.getBytes();
//            Blob logoBlob = new SerialBlob(logoBytes);
//            prenda.setLogo(logoBlob);
//        }
//
//        return ResponseEntity.ok(this.prendaService.guardarPrenda(prenda));
//    }

    @PostMapping(path = "/prendas")
    public ResponseEntity<Prenda> agregarPrenda(@RequestBody Prenda prenda){
        logger.info("Prenda a agregar: " + prenda);
        return ResponseEntity.ok(this.prendaService.guardarPrenda(prenda));
    }

    @PutMapping(path = "/prendas/{id}")
    public ResponseEntity<Prenda> actualizarPrenda(@PathVariable int id, @RequestBody Prenda prendaRecibida){
        Prenda prenda = this.prendaService.buscarPrendaPorId(id);
        if(prenda == null){
            throw new RecursoNoEncontradoException("No se encontro el id: " + id);
        }
//        prenda.setOrden(prendaRecibida.getOrden());
        prenda.setTitulo(prendaRecibida.getTitulo());
        prenda.setDescripcion(prendaRecibida.getDescripcion());
        prenda.setCantidad(prendaRecibida.getCantidad());
        prenda.setPrecio(prendaRecibida.getPrecio());
        prenda.setObservaciones(prendaRecibida.getObservaciones());

        this.prendaService.guardarPrenda(prenda);
        return ResponseEntity.ok(prenda);
    }

//    @PutMapping(path = "/prendas/{id}", consumes = { "multipart/form-data" })
//    public ResponseEntity<Prenda> actualizarPrenda(
//            @PathVariable int id,
//            @RequestBody Prenda prendaRecibida) {
//
//        Prenda prenda = this.prendaService.buscarPrendaPorId(id);
//        if (prenda == null) {
//            throw new RecursoNoEncontradoException("No se encontro el id: " + id);
//        }
//
//        prenda.setTitulo(prendaRecibida.getTitulo());
//        prenda.setDescripcion(prendaRecibida.getDescripcion());
//        prenda.setCantidad(prendaRecibida.getCantidad());
//        prenda.setPrecio(prendaRecibida.getPrecio());
//        prenda.setObservaciones(prendaRecibida.getObservaciones());
//
////        if (!logo.isEmpty()) {
////            byte[] logoBytes = logo.getBytes();
////            Blob logoBlob = new SerialBlob(logoBytes);
////            prenda.setLogo(logoBlob);
////        }
//
//        this.prendaService.guardarPrenda(prenda);
//        return ResponseEntity.ok(prenda);
//    }

    @DeleteMapping("/prendas/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarEmpleado(@PathVariable int id){
        Prenda prenda = prendaService.buscarPrendaPorId(id);
        if(prenda == null){
            throw new RecursoNoEncontradoException("No se encontro el id: " + id);
        }
        this.prendaService.eliminarPrendaPorId(prenda.getIdPrenda());
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }

}
