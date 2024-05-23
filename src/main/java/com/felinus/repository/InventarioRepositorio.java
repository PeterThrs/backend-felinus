package com.felinus.repository;

import com.felinus.models.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventarioRepositorio extends JpaRepository<Inventario, Integer> {
}
