package com.prueba.tecnica.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prueba.tecnica.Model.Articulo;

public interface ArticuloRepository extends JpaRepository<Articulo, Long> {
    
}
