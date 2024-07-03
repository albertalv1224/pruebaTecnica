package com.prueba.tecnica.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.tecnica.Model.Articulo;
import com.prueba.tecnica.Model.Cliente;
import com.prueba.tecnica.Service.ArticuloService;
import com.prueba.tecnica.Service.PartidasPedidoService;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;




@Controller
@RequestMapping("/articulos")
public class ArticuloController {


    private ArticuloService articuloService;
   

    public ArticuloController(ArticuloService articuloService){
        this.articuloService = articuloService;
    }   
   
    @GetMapping("/listado")
    public String mostrarArticulos(Model model) {
        List<Articulo> articulos = articuloService.buscarArticulos();
        model.addAttribute("articulos", articulos);
        return "Vistas/ListaArticulos";
    }
    
    @GetMapping("/formulario")
    public String verFormulario(Model model) {
        model.addAttribute("articulo", new Articulo());
        return "Vistas/FormularioArticulo";
    }

    @GetMapping("/editar/{id}")
    public String formularioEdicion(Model model, @PathVariable Long id) {
        Articulo articulo = articuloService.buscarArticulo(id);
        model.addAttribute("articulo", articulo);
        return "Vistas/FormularioEditarArticulo";
    }

    @PostMapping("/editado/{id}")
    public String editarArticulo(@ModelAttribute Articulo articulo, @PathVariable Long id) {
        articulo.setIdArticulo(id);
        articuloService.guardarArticulo(articulo);
        return "redirect:/articulos/listado";
    }
    
    @PostMapping("/guardar")
    public String guardarArticulo(@Validated @ModelAttribute Articulo articulo) {
        articuloService.guardarArticulo(articulo);
        return "redirect:/articulos/listado";
    }
    
    @PostMapping("/borrar/{id}")
    public String borrarArticulo(@PathVariable Long id) {
        articuloService.borrarArticulo(id);
        return "redirect:/articulos/listado";
    }
    

}
