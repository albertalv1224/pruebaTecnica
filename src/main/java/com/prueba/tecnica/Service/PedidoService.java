package com.prueba.tecnica.Service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prueba.tecnica.Model.Cliente;
import com.prueba.tecnica.Model.Pedidos;
import com.prueba.tecnica.Repositories.PedidoRepository;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final PartidasPedidoService partidasPedidoService;

    public PedidoService(PedidoRepository pedidoRepository, PartidasPedidoService partidasPedidoService){
        this.pedidoRepository = pedidoRepository;
        this.partidasPedidoService = partidasPedidoService;
    }

    public Long guardarPedido(Pedidos pedido) {
        
        Pedidos pedidoGuardado = pedidoRepository.save(pedido);
       
        return pedidoGuardado.getIdPedido();
    }

    public List<Pedidos> listarPedidos(){
        return pedidoRepository.findAll();
    }
    
   
    public void eliminarPedidosPorCliente(Cliente cliente){
        
        pedidoRepository.deleteByIdcliente(cliente);
    }

    @Transactional
    public void eliminarPedido(Cliente cliente){
        List<Pedidos> pedidos = pedidoRepository.findByIdcliente(cliente);
        for (Pedidos pedido : pedidos) {
            partidasPedidoService.eliminarPartidasPorPedido(pedido);
        }
        pedidoRepository.deleteByIdcliente(cliente);
    }
}
