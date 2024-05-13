package com.felinus;

import com.felinus.models.Empleado;
import com.felinus.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FelinusApplication {
	//implements CommandLineRunner

	@Autowired
	private EmpleadoService empleadoService;

	public static void main(String[] args) {
		SpringApplication.run(FelinusApplication.class, args);
	}


//	@Override
//	public void run(String... args) throws Exception {
//		Empleado empleado = new Empleado();
//		empleado.setNombre("Fatima");
//		empleado.setApPaterno("Segal");
//		empleado.setActivo(true);
//
//		empleadoService.guardarEmpleado(empleado);
//	}
}
