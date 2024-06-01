package com.felinus.repository;

import com.felinus.models.MovimientoInventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimientoInventarioRepositorio extends JpaRepository<MovimientoInventario, String> {
}
