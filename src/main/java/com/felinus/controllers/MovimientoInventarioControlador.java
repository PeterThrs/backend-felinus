package com.felinus.controllers;

import com.felinus.exceptions.DepartamentoNoValidoException;
import com.felinus.exceptions.RecursoNoEncontradoException;
import com.felinus.models.Departamento;
import com.felinus.models.Empleado;
import com.felinus.models.Inventario;
import com.felinus.models.MovimientoInventario;
import com.felinus.service.EmpleadoService;
import com.felinus.service.InventarioService;
import com.felinus.service.MovimientoInventarioService;
import com.felinus.utils.Generador;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("felinus-app")
@CrossOrigin(value="http://localhost:4200")
public class MovimientoInventarioControlador {

    private static final Logger logger = LoggerFactory.getLogger(MovimientoInventarioControlador.class);

    @Autowired
    private MovimientoInventarioService movimientoInventarioService;

    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private InventarioService inventarioService;

    //ENDPOINTS EMPLEADOS
    @GetMapping("/movimientos")
    public List<MovimientoInventario> obtenerMovimientos(){
        List<MovimientoInventario> movimientos = movimientoInventarioService.listarMovimientos();
        logger.info("Movimientos obtenidos: ");
        movimientos.forEach( empleado -> logger.info(empleado.toString()));
        return movimientos;
    }

    @GetMapping("/movimientos/{clave}")
    public ResponseEntity<MovimientoInventario> obtenerMovimientoPorId(@PathVariable String clave){
        MovimientoInventario movimiento = this.movimientoInventarioService.buscarMovimientoPorId(clave);
        if(movimiento != null) {
            return ResponseEntity.ok(movimiento);
        } else {
            throw new RecursoNoEncontradoException("No se encontro el id: " + clave);
        }
    }

    @PostMapping(path = "/movimientos")
    public ResponseEntity<MovimientoInventario> agregarMovimiento(@RequestBody MovimientoInventario movimiento){
        logger.info("Movimiento a agregar: " + movimiento);

        Empleado empleado = this.empleadoService.buscarEmpleadoPorId(movimiento.getEmpleado().getCurp());
        Inventario material = this.inventarioService.buscarMaterialPorId(movimiento.getInventario().getCodigo());


        if(empleado != null && material != null){
            movimiento.setEmpleado(empleado);
            movimiento.setInventario(material);
            String clave = movimiento.getEmpleado().getCurp() + "_" +
                    movimiento.getInventario().getCodigo()  + "_" +
                    Generador.generateAlphanumeric();
            movimiento.setClave(clave);
            return ResponseEntity.ok(this.movimientoInventarioService.guardarMovimiento(movimiento));
        }

        throw new DepartamentoNoValidoException("El empleado o material no existe" + movimiento.getInventario().getCodigo());

    }

    @PutMapping(path = "/movimientos/{clave}")
    public ResponseEntity<MovimientoInventario> actualizarMovimiento(@PathVariable String clave, @RequestBody MovimientoInventario movimientoRecibido){
        MovimientoInventario movimiento = this.movimientoInventarioService.buscarMovimientoPorId(clave);
        if(movimiento == null){
            throw new RecursoNoEncontradoException("No se encontro el id: " + clave);
        }
        Empleado empleado = this.empleadoService.buscarEmpleadoPorId(movimientoRecibido.getEmpleado().getCurp());
        Inventario material = this.inventarioService.buscarMaterialPorId(movimientoRecibido.getInventario().getCodigo());


        if(empleado != null && material != null){
            movimiento.setEmpleado(empleado);
            movimiento.setInventario(material);
        }

        movimiento.setFechaMov(movimientoRecibido.getFechaMov());
        movimiento.setTipoMov(movimientoRecibido.getTipoMov());
        movimiento.setCantidadMov(movimientoRecibido.getCantidadMov());
        movimiento.setRazon(movimientoRecibido.getRazon());

        this.movimientoInventarioService.guardarMovimiento(movimiento);
        return ResponseEntity.ok(movimiento);
    }

    @DeleteMapping("/movimientos/{clave}")
    public ResponseEntity<Map<String, Boolean>> eliminarEmpleado(@PathVariable String clave){
        MovimientoInventario movimiento = this.movimientoInventarioService.buscarMovimientoPorId(clave);
        if(movimiento == null){
            throw new RecursoNoEncontradoException("No se encontro el id: " + clave);
        }
        this.movimientoInventarioService.eliminarMovimientoPorId(movimiento.getClave());
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }

}
