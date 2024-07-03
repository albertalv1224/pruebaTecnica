package com.prueba.tecnica.Controller;

import java.util.List;

import org.springframework.data.jpa.domain.JpaSort.Path;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.prueba.tecnica.Model.Articulo;
import com.prueba.tecnica.Model.Cliente;
import com.prueba.tecnica.Service.ClienteService;
import com.prueba.tecnica.Service.PedidoService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;





@Controller
@RequestMapping("/cliente")
@Transactional
public class ClienteController {
    
    private final ClienteService clienteService;
    private final PedidoService pedidoService;

    public ClienteController(ClienteService clienteService, PedidoService pedidoService){
        this.clienteService = clienteService;
        this.pedidoService = pedidoService;
    }   

    @GetMapping("/listado")
    public String mostrarClientes(Model model) { 
        List<Cliente> clientes = clienteService.buscarClientes();
        model.addAttribute("clientes",clientes);
        return "Vistas/ListaClientes";
    }
    
    @GetMapping("/formulario")
    public String formularioCliente(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "Vistas/FormularioCliente";
    }
    
    @GetMapping("/editar/{id}")
    public String formularioEdicion(Model model, @PathVariable Long id) {
        Cliente cliente = clienteService.buscarCliente(id);
        model.addAttribute("cliente", cliente);
        return "Vistas/FormularioEditarCliente";
    }
    
    @PostMapping("/guardar")
    public String guardarCliente(@ModelAttribute Cliente cliente) {
        clienteService.guardarCliente(cliente);
        return "redirect:/cliente/listado";
    }

    @PostMapping("/borrar/{id}")
    public String borrarCliente(@PathVariable Long id) {
        Cliente cliente = clienteService.buscarClientePorId(id);
        pedidoService.eliminarPedidosPorCliente(cliente);
        clienteService.BorrarCliente(cliente.getIdCliente());
        return "redirect:/cliente/listado";
    }
    
    @PostMapping("/editado/{id}")
    public String editarCliente(@ModelAttribute Cliente cliente, @PathVariable Long id) {
        cliente.setIdCliente(id);
        clienteService.guardarCliente(cliente);
        return "redirect:/cliente/listado";
    }
    
}
