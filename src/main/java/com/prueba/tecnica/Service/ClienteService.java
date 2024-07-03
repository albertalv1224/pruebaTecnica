package com.prueba.tecnica.Service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prueba.tecnica.Model.Cliente;
import com.prueba.tecnica.Repositories.ClienteRepository;
import com.prueba.tecnica.Repositories.PedidoRepository;

@Service
public class ClienteService {
    
    private final ClienteRepository clienteRepository;
    private final PedidoService pedidoService;

    public ClienteService(ClienteRepository clienteRepository, PedidoService pedidoService){
        this.clienteRepository = clienteRepository;
        this.pedidoService = pedidoService;
    }

    public String guardarCliente(Cliente cliente){
        clienteRepository.save(cliente);
        return "Cliente guardado exitosamente";
    }
    @Transactional
    public void BorrarCliente(Long id){
        Cliente cliente = clienteRepository.findById(id).orElse(null);
        pedidoService.eliminarPedidosPorCliente(cliente);
        clienteRepository.deleteById(id);
    }

    public List<Cliente> buscarClientes(){
        return clienteRepository.findAll();
    }

    public Cliente buscarCliente(Long id){
        return clienteRepository.findById(id).orElseThrow(null);
    }

    public Cliente buscarClientePorId(Long id) {
        return clienteRepository.findById(id).orElseThrow(null);
        
    }
}
