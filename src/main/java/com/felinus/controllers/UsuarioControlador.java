package com.felinus.controllers;

import com.felinus.exceptions.RecursoNoEncontradoException;
import com.felinus.models.*;
import com.felinus.service.*;
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
public class UsuarioControlador {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioControlador.class);

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private ClienteService clienteService;

    //ENDPOINTS USUARIOS
    @GetMapping("/usuarios")
    public List<Usuario> obtenerUsuarios(){
        List<Usuario> productos = usuarioServicio.listarUsuarios();
        logger.info("Usuarios obtenidos: ");
        productos.forEach( usuario -> logger.info(usuario.toString()));
        return productos;
    }

    //ENDPOINTS EMPLEADOS
    @GetMapping("/empleados")
    public List<Empleado> obtenerEmpleados(){
        List<Empleado> empleados = empleadoService.listarEmpleados();
        logger.info("Empleados obtenidos: ");
        empleados.forEach( empleado -> logger.info(empleado.toString()));
        return empleados;
    }

    @GetMapping("/empleados/{id}")
    public ResponseEntity<Empleado> obtenerEmpleadoPorId(@PathVariable int id){
        Empleado empleado = this.empleadoService.buscarEmpleadoPorId(id);
        if(empleado != null) {
            return ResponseEntity.ok(empleado);
        } else {
            throw new RecursoNoEncontradoException("No se encontro el id: " + id);
        }
    }

    @PostMapping(path = "/empleados", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Empleado agregarEmpleado(@RequestBody Empleado empleado){
        logger.info("Empleado A agregar: " + empleado);
        return this.empleadoService.guardarEmpleado(empleado);
    }

    @PutMapping(path = "/empleados/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable int id, @RequestBody Empleado empleadoRecibido){
        Empleado empleado = this.empleadoService.buscarEmpleadoPorId(id);
        if(empleado == null){
            throw new RecursoNoEncontradoException("No se encontro el id: " + id);
        }
        empleado.setNombre(empleadoRecibido.getNombre());
        empleado.setApPaterno(empleadoRecibido.getApPaterno());
        empleado.setApMaterno(empleadoRecibido.getApMaterno());
        empleado.setDomicilioActual(empleadoRecibido.getDomicilioActual());
        empleado.setTelefono(empleadoRecibido.getTelefono());
        empleado.setEmail(empleadoRecibido.getEmail());

        empleado.setUsuario(empleadoRecibido.getUsuario());
        empleado.setPassword(empleadoRecibido.getPassword());
        empleado.setFechaAlta(empleadoRecibido.getFechaAlta());
        empleado.setActivo(empleadoRecibido.getActivo());

        this.empleadoService.guardarEmpleado(empleado);
        return ResponseEntity.ok(empleado);
    }

    @DeleteMapping("/empleados/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarEmpleado(@PathVariable int id){
        Empleado empleado = empleadoService.buscarEmpleadoPorId(id);
        if(empleado == null){
            throw new RecursoNoEncontradoException("No se encontro el id: " + id);
        }
        this.empleadoService.eliminarEmpleadoPorId(empleado.getIdUsuario());
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }

    //ENDPOINTS PARA CLIENTES
    @GetMapping("/clientes")
    public List<Cliente> obtenerCliente(){
        List<Cliente> clientes = clienteService.listarClientes();
        logger.info("Clientes obtenidos: ");
        clientes.forEach( empleado -> logger.info(empleado.toString()));
        return clientes;
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity<Cliente> obtenerClientePorId(@PathVariable int id){
        Cliente cliente = this.clienteService.buscarClientePorId(id);
        if(cliente != null) {
            return ResponseEntity.ok(cliente);
        } else {
            throw new RecursoNoEncontradoException("No se encontro el id: " + id);
        }
    }

    @PostMapping(path = "/clientes", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Cliente agregarCliente(@RequestBody Cliente cliente){
        logger.info("Cliente a agregar: " + cliente);
        return this.clienteService.guardarCliente(cliente);
    }

    @PutMapping(path = "/clientes/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable int id, @RequestBody Cliente clienteRecibido){
        Cliente cliente = this.clienteService.buscarClientePorId(id);
        if(cliente == null){
            throw new RecursoNoEncontradoException("No se encontro el id: " + id);
        }
        cliente.setNombre(clienteRecibido.getNombre());
        cliente.setApPaterno(clienteRecibido.getApPaterno());
        cliente.setApMaterno(clienteRecibido.getApMaterno());
        cliente.setDomicilioActual(clienteRecibido.getDomicilioActual());
        cliente.setTelefono(clienteRecibido.getTelefono());
        cliente.setEmail(clienteRecibido.getEmail());

        cliente.setTotalCompras(clienteRecibido.getTotalCompras());
        cliente.setDireccionEntrega(clienteRecibido.getDireccionEntrega());

        this.clienteService.guardarCliente(cliente);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarCliente(@PathVariable int id){
        Cliente cliente = clienteService.buscarClientePorId(id);
        if(cliente == null){
            throw new RecursoNoEncontradoException("No se encontro el id: " + id);
        }
        this.clienteService.eliminarClientePorId(cliente.getIdUsuario());
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }

}
