package com.prueba.tecnica.Model;

public class PartidaPedidoDTO {
    private Long idPartida;
    private Long idPedido;
    private Long idArticulo;
    private Integer cantidad;

  
    public PartidaPedidoDTO(Long idPartida, Long idPedido, Long idArticulo, Integer cantidad) {
        this.idPartida = idPartida;
        this.idPedido = idPedido;
        this.idArticulo = idArticulo;
        this.cantidad = cantidad;
    }

    public Long getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(Long idPartida) {
        this.idPartida = idPartida;
    }

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public Long getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(Long idArticulo) {
        this.idArticulo = idArticulo;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
