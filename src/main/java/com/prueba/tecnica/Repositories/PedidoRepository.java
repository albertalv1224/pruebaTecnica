package com.prueba.tecnica.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.prueba.tecnica.Model.Cliente;
import com.prueba.tecnica.Model.Pedidos;

public interface PedidoRepository  extends JpaRepository <Pedidos, Long>{
    @Modifying
    @Transactional
    @Query("DELETE FROM Pedidos p WHERE p.idcliente = :cliente")
    void deleteByIdcliente(@Param("cliente") Cliente cliente);
    List<Pedidos> findByIdcliente(Cliente cliente);
}
