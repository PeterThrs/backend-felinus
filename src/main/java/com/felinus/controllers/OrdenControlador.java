package com.felinus.controllers;

import com.felinus.exceptions.RecursoNoEncontradoException;
import com.felinus.models.Cliente;
import com.felinus.models.Empleado;
import com.felinus.models.Orden;
import com.felinus.models.Prenda;
import com.felinus.service.ClienteService;
import com.felinus.service.EmpleadoService;
import com.felinus.service.OrdenService;
import com.felinus.service.PrendaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
//http:/localhost:8080/felinus-app
@RequestMapping("felinus-app")
//configuramos para que angular pueda realizar peticiones a nuestro backend
@CrossOrigin(value="http://localhost:4200")
public class OrdenControlador {

    private static final Logger logger = LoggerFactory.getLogger(OrdenControlador.class);

    @Autowired
    private OrdenService ordenService;

    @Autowired
    private PrendaService prendaService;

    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private ClienteService clienteService;


    //ENDPOINTS EMPLEADOS
    @GetMapping("/ordenes")
    public List<Orden> obtenerOrdenes(){
        List<Orden> ordenes = ordenService.listarOrdenes();
        logger.info("Ordenes obtenidos: ");
        ordenes.forEach( orden -> logger.info(orden.toString()));
        return ordenes;
    }

    @GetMapping("/ordenes/{id}")
    public ResponseEntity<Orden> obtenerOrdenPorId(@PathVariable int id){
        Orden orden = this.ordenService.buscarOrdenPorId(id);
        if(orden != null) {
            return ResponseEntity.ok(orden);
        } else {
            throw new RecursoNoEncontradoException("No se encontro el id: " + id);
        }
    }

    @PostMapping(path = "/ordenes")
    public ResponseEntity<Orden> agregarOrden(@RequestBody Orden orden){
        logger.info("Orden a agregar: " + orden);
        Prenda prenda = this.prendaService.buscarPrendaPorId(orden.getPrenda().getIdPrenda());
        Empleado empleado = this.empleadoService.buscarEmpleadoPorId(orden.getEmpleado().getCurp());
        Cliente cliente = this.clienteService.buscarClientePorId(orden.getCliente().getCurp());

        if(prenda == null && empleado == null && cliente == null){
            throw new RecursoNoEncontradoException("No existe (cliente, empleado o prenda): ");
        }

        Orden nuevaOrden = new Orden();
        nuevaOrden.setCliente(cliente);
        nuevaOrden.setEmpleado(empleado);
        nuevaOrden.setPrenda(prenda);
        nuevaOrden.setFechaInicio(orden.getFechaInicio());
        nuevaOrden.setFechaEntrega(orden.getFechaEntrega());
        nuevaOrden.setEtapa(orden.getEtapa());
        nuevaOrden.setEstado(orden.getEstado());
        nuevaOrden.setTotal(orden.getTotal());
        nuevaOrden.setAnticipo(orden.getAnticipo());

        return ResponseEntity.ok(this.ordenService.guardarOrden(nuevaOrden));

    }

    @PutMapping(path = "/ordenes/{id}")
    public ResponseEntity<Orden> actualizarEmpleado(@PathVariable int id, @RequestBody Orden ordenRecibida){
        Orden orden = this.ordenService.buscarOrdenPorId(id);
        if(orden == null){
            throw new RecursoNoEncontradoException("No se encontro el id: " + id);
        }

        Prenda prenda = this.prendaService.buscarPrendaPorId(ordenRecibida.getPrenda().getIdPrenda());
        Empleado empleado = this.empleadoService.buscarEmpleadoPorId(ordenRecibida.getEmpleado().getCurp());
        Cliente cliente = this.clienteService.buscarClientePorId(ordenRecibida.getCliente().getCurp());

        if(prenda == null && empleado == null && cliente == null){
            throw new RecursoNoEncontradoException("No existe (cliente, empleado o prenda): ");
        }

        orden.setPrenda(prenda);
        orden.setEmpleado(empleado);
        orden.setCliente(cliente);

        orden.setFechaInicio(ordenRecibida.getFechaInicio());
        orden.setFechaEntrega(ordenRecibida.getFechaEntrega());
        orden.setEtapa(ordenRecibida.getEtapa());
        orden.setEstado(ordenRecibida.getEstado());
        orden.setTotal(ordenRecibida.getTotal());
        orden.setAnticipo(ordenRecibida.getAnticipo());

        this.ordenService.guardarOrden(orden);
        return ResponseEntity.ok(orden);
    }

    @DeleteMapping("/ordenes/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarEmpleado(@PathVariable int id){
        Orden orden = ordenService.buscarOrdenPorId(id);
        if(orden == null){
            throw new RecursoNoEncontradoException("No se encontro el id: " + id);
        }
        this.ordenService.eliminarOrdenPorId(orden.getIdOrden());
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }



}
