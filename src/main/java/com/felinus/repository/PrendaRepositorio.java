package com.felinus.repository;

import com.felinus.models.Prenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrendaRepositorio extends JpaRepository<Prenda, Integer> {
}
