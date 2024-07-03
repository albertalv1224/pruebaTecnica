package com.prueba.tecnica.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.prueba.tecnica.Model.Articulo;
import com.prueba.tecnica.Model.Cliente;
import com.prueba.tecnica.Model.Pedidos;
import com.prueba.tecnica.Service.ArticuloService;
import com.prueba.tecnica.Service.ClienteService;
import com.prueba.tecnica.Service.PedidoService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping("pedido")
public class PedidoController {

    private final ClienteService clienteService;
    private final PedidoService pedidoService;
   

    public PedidoController(ClienteService clienteService, PedidoService pedidoService){
        this.clienteService = clienteService;
        this.pedidoService = pedidoService;
    
    }
    

    @GetMapping("/listado")
    public String mostrarPedidos(Model model) {
        List<Pedidos> pedidos = pedidoService.listarPedidos();
        model.addAttribute("pedidos", pedidos);
        return "Vistas/ListaPedidos";
    }
    
    
    @GetMapping("/crear")
    public String mostrarFormularioPedido(Model model) {
        List<Cliente> clientes = clienteService.buscarClientes();
        model.addAttribute("clientes", clientes);
        model.addAttribute("pedidos", new Pedidos()); 
        return "Vistas/CrearPedido";
    }

    @PostMapping("/guardar")
    public String guardarPedido(@ModelAttribute Pedidos pedido, HttpSession sesion) {
        Long idPedido = pedidoService.guardarPedido(pedido);
        sesion.setAttribute("idPedido", idPedido);
        System.out.println(pedido.getIdPedido());
        return "redirect:/agregar-articulos";
    }
    


    
}
