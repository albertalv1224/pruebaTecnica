package com.prueba.tecnica.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.prueba.tecnica.Model.Articulo;
import com.prueba.tecnica.Model.Cliente;
import com.prueba.tecnica.Service.ArticuloService;
import com.prueba.tecnica.Service.ClienteService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    private final ArticuloService articuloService;
    private final ClienteService clienteService;

    public IndexController(ArticuloService articuloService, ClienteService clienteService){
        this.clienteService = clienteService;
        this.articuloService = articuloService;
    }
    @GetMapping("/")
    public String mostrarArticulos(Model model, HttpServletRequest request) {
        List<Articulo> articulos = articuloService.buscarArticulos();
        model.addAttribute("articulos", articulos);
        model.addAttribute("ruta", request.getRequestURI());
        return "Vistas/Index";
    }

}
