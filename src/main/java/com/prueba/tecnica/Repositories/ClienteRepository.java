package com.prueba.tecnica.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prueba.tecnica.Model.Cliente;

public interface ClienteRepository  extends JpaRepository<Cliente,Long>{
    
}
