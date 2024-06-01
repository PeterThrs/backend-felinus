package com.felinus.controllers;

import com.felinus.exceptions.RecursoNoEncontradoException;
import com.felinus.models.Prenda;
import com.felinus.service.PrendaService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

@RestController
//http:/localhost:8080/felinus-app
@RequestMapping("felinus-app")
//configuramos para que angular pueda realizar peticiones a nuestro backend
@CrossOrigin(value="http://localhost:4200")
public class ImagenControlador {

    private static final Logger logger = LoggerFactory.getLogger(ImagenControlador.class);

    @Autowired
    private PrendaService prendaService;

    // agregar imagen a prendas
    @PutMapping(path = "/img-prenda/{id}")
    public ResponseEntity<Prenda> agregarLogoPrenda(
            @PathVariable int id,
            HttpServletRequest request,
            @RequestParam("image") MultipartFile file
    ) throws IOException, SerialException, SQLException
    {

        Prenda prenda = this.prendaService.buscarPrendaPorId(id);
        if(prenda == null){
            throw new RecursoNoEncontradoException("No se encontro el id: " + id);
        }

        byte[] bytes = file.getBytes();
        Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);

        prenda.setLogo(blob);

        this.prendaService.guardarPrenda(prenda);
        return ResponseEntity.ok(prenda);

    }

    // display image
    @GetMapping("/img-prenda/{id}")
    public ResponseEntity<byte[]> obtenerImagenPrenda(
            @PathVariable int id) throws IOException, SQLException
    {
        logger.info("Entramos al metodo par ala imagen: ");
        Prenda prenda = prendaService.buscarPrendaPorId(id);
        byte [] imageBytes = null;

        imageBytes = prenda.getLogo().getBytes(1,(int) prenda.getLogo().length());
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
    }



}
