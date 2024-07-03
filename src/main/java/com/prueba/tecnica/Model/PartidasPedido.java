package com.prueba.tecnica.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PartidasPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPartida;
    @ManyToOne
    @JoinColumn(name = "idPedido", nullable = false)
    private Pedidos idpedido;
    @ManyToOne
    @JoinColumn(name = "idArticulo", nullable = false)
    private Articulo idarticulo;
    private int cantidad;
}
