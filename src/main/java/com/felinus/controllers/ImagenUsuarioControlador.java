package com.felinus.controllers;

import com.felinus.exceptions.RecursoNoEncontradoException;
import com.felinus.models.Cliente;
import com.felinus.models.Empleado;
import com.felinus.service.ClienteService;
import com.felinus.service.EmpleadoService;
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
public class ImagenUsuarioControlador {

    private static final Logger logger = LoggerFactory.getLogger(AlmacenControlador.class);

    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private ClienteService clienteService;

    // agregar img a empleado
    @PutMapping(path = "/img-empleado/{curp}")
    public ResponseEntity<Empleado> agregarImgEmpleado(
            @PathVariable String curp,
            HttpServletRequest request,
            @RequestParam("image") MultipartFile file
    ) throws IOException, SerialException, SQLException
    {

        Empleado empleado = this.empleadoService.buscarEmpleadoPorId(curp);
        if(empleado == null){
            throw new RecursoNoEncontradoException("No se encontro el id: " + curp);
        }

        byte[] bytes = file.getBytes();
        Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);

        empleado.setImgUsuario(blob);

        this.empleadoService.guardarEmpleado(empleado);
        return ResponseEntity.ok(empleado);

    }

    // obtner img de empleados
    @GetMapping("/img-empleado/{curp}")
    public ResponseEntity<byte[]> obtenerImgEmpleado(
            @PathVariable String curp) throws IOException, SQLException
    {
        logger.info("Entramos al metodo par ala imagen: ");
        Empleado empleado = empleadoService.buscarEmpleadoPorId(curp);
        byte [] imageBytes = null;

        imageBytes = empleado.getImgUsuario().getBytes(1,(int) empleado.getImgUsuario().length());
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
    }

    // agregar img a empleado
    @PutMapping(path = "/img-cliente/{curp}")
    public ResponseEntity<Cliente> agregarImgCliente(
            @PathVariable String curp,
            HttpServletRequest request,
            @RequestParam("image") MultipartFile file
    ) throws IOException, SerialException, SQLException
    {

        Cliente cliente = this.clienteService.buscarClientePorId(curp);
        if(cliente == null){
            throw new RecursoNoEncontradoException("No se encontro el id: " + curp);
        }

        byte[] bytes = file.getBytes();
        Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);

        cliente.setImgUsuario(blob);

        this.clienteService.guardarCliente(cliente);
        return ResponseEntity.ok(cliente);

    }

    // obtner img de empleados
    @GetMapping("/img-cliente/{curp}")
    public ResponseEntity<byte[]> obtenerImgCliente(
            @PathVariable String curp) throws IOException, SQLException
    {
        logger.info("Entramos al metodo par ala imagen: ");
        Cliente cliente = clienteService.buscarClientePorId(curp);
        byte [] imageBytes = null;

        imageBytes = cliente.getImgUsuario().getBytes(1,(int) cliente.getImgUsuario().length());
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
    }

}
