package com.felinus.controllers;

import com.felinus.exceptions.DepartamentoNoValidoException;
import com.felinus.exceptions.RecursoNoEncontradoException;
import com.felinus.models.*;
import com.felinus.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.*;

@RestController
//http:/localhost:8080/felinus-app
@RequestMapping("felinus-app")
//configuramos para que angular pueda realizar peticiones a nuestro backend
@CrossOrigin(value="http://localhost:4200")
public class UsuarioControlador {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioControlador.class);

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private DepartamentoService departamentoService;

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

    @GetMapping("/empleados/{curp}")
    public ResponseEntity<Empleado> obtenerEmpleadoPorId(@PathVariable String curp){
        Empleado empleado = this.empleadoService.buscarEmpleadoPorId(curp);
        if(empleado != null) {
            return ResponseEntity.ok(empleado);
        } else {
            throw new RecursoNoEncontradoException("No se encontro el id: " + curp);
        }
    }

    @PostMapping(path = "/empleados")
    public ResponseEntity<Empleado> agregarEmpleado(@RequestBody Empleado empleado){
        logger.info("Empleado A agregar: " + empleado);

        if(empleado.getCurp().isEmpty()){
            throw new RecursoNoEncontradoException("Es necesario indicar una curp" + empleado.getCurp());
        }

        Departamento depto = this.departamentoService.buscarDepartamentoPorId(empleado.getDepartamento().getNombre());

        if(depto != null){
            empleado.setDepartamento(depto);
            return ResponseEntity.ok(this.empleadoService.guardarEmpleado(empleado));
        }

        throw new DepartamentoNoValidoException("El departamento no existe en la BD" + empleado.getDepartamento().getNombre());

    }

    @PutMapping(path = "/empleados/{curp}")
    public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable String curp, @RequestBody Empleado empleadoRecibido){
        Empleado empleado = this.empleadoService.buscarEmpleadoPorId(curp);
        if(empleado == null){
            throw new RecursoNoEncontradoException("No se encontro el id: " + curp);
        }
        empleado.setNombre(empleadoRecibido.getNombre());
        empleado.setApPaterno(empleadoRecibido.getApPaterno());
        empleado.setApMaterno(empleadoRecibido.getApMaterno());
        empleado.setDomicilio(empleadoRecibido.getDomicilio());
        empleado.setTelefono(empleadoRecibido.getTelefono());
        empleado.setEmail(empleadoRecibido.getEmail());

        empleado.setUsuario(empleadoRecibido.getUsuario());
        empleado.setPassword(empleadoRecibido.getPassword());
        empleado.setFechaAlta(empleadoRecibido.getFechaAlta());
        empleado.setActivo(empleadoRecibido.getActivo());

        Departamento depto = this.departamentoService.buscarDepartamentoPorId(empleadoRecibido.getDepartamento().getNombre());

        if(depto != null){
            empleado.setDepartamento(depto);
        }

        this.empleadoService.guardarEmpleado(empleado);
        return ResponseEntity.ok(empleado);
    }

    @DeleteMapping("/empleados/{curp}")
    public ResponseEntity<Map<String, Boolean>> eliminarEmpleado(@PathVariable String curp){
        Empleado empleado = empleadoService.buscarEmpleadoPorId(curp);
        if(empleado == null){
            throw new RecursoNoEncontradoException("No se encontro el id: " + curp);
        }
        this.empleadoService.eliminarEmpleadoPorId(empleado.getCurp());
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

    @GetMapping("/clientes/{curp}")
    public ResponseEntity<Cliente> obtenerClientePorId(@PathVariable String curp){
        Cliente cliente = this.clienteService.buscarClientePorId(curp);
        if(cliente != null) {
            return ResponseEntity.ok(cliente);
        } else {
            throw new RecursoNoEncontradoException("No se encontro el id: " + curp);
        }
    }

    @PostMapping(path = "/clientes")
    public Cliente agregarCliente(@RequestBody Cliente cliente){
        logger.info("Cliente a agregar: " + cliente);
        return this.clienteService.guardarCliente(cliente);
    }

    @PutMapping(path = "/clientes/{curp}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable String curp, @RequestBody Cliente clienteRecibido){
        Cliente cliente = this.clienteService.buscarClientePorId(curp);
        if(cliente == null){
            throw new RecursoNoEncontradoException("No se encontro el id: " + curp);
        }
        cliente.setNombre(clienteRecibido.getNombre());
        cliente.setApPaterno(clienteRecibido.getApPaterno());
        cliente.setApMaterno(clienteRecibido.getApMaterno());
        cliente.setDomicilio(clienteRecibido.getDomicilio());
        cliente.setTelefono(clienteRecibido.getTelefono());
        cliente.setEmail(clienteRecibido.getEmail());
        cliente.setDireccionEntrega(clienteRecibido.getDireccionEntrega());

        this.clienteService.guardarCliente(cliente);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/clientes/{curp}")
    public ResponseEntity<Map<String, Boolean>> eliminarCliente(@PathVariable String curp){
        Cliente cliente = clienteService.buscarClientePorId(curp);
        if(cliente == null){
            throw new RecursoNoEncontradoException("No se encontro el id: " + curp);
        }
        this.clienteService.eliminarClientePorId(cliente.getCurp());
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }

}
