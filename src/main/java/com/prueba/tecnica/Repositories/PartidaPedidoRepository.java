package com.prueba.tecnica.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.prueba.tecnica.Model.Articulo;
import com.prueba.tecnica.Model.Cliente;
import com.prueba.tecnica.Model.PartidasPedido;
import com.prueba.tecnica.Model.Pedidos;

public interface PartidaPedidoRepository extends JpaRepository<PartidasPedido, Long>{
    @Query(value = "SELECT pp.id_partida, p.id_pedido, a.id_articulo, pp.cantidad " +
                   "FROM partidas_pedido pp " +
                   "JOIN pedidos p ON pp.id_pedido = p.id_pedido " +
                   "JOIN articulo a ON pp.id_articulo = a.id_articulo " +
                   "WHERE p.id_pedido = :idPedido", nativeQuery = true)
    List<PartidasPedido> findAllByPedidoIdPedido(Long idPedido);
   
    void deleteByIdarticulo(Articulo articulo);
    void deleteByIdpedido(Pedidos pedido);
}
