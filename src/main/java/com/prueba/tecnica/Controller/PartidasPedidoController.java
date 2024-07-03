package com.prueba.tecnica.Controller;

import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.prueba.tecnica.Model.Articulo;
import com.prueba.tecnica.Model.PartidasPedido;
import com.prueba.tecnica.Service.ArticuloService;
import com.prueba.tecnica.Service.PartidasPedidoService;

import jakarta.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JRException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
public class PartidasPedidoController {

    private final ArticuloService articuloService;
    private final PartidasPedidoService partidasPedidoService;

     public PartidasPedidoController(ArticuloService articuloService, PartidasPedidoService partidasPedidoService){
        this.articuloService = articuloService;
        this.partidasPedidoService = partidasPedidoService;
     }

     @GetMapping("/agregar-articulos")
     public String agregarArticulos(Model model, HttpSession session) {
         List<Articulo> articulos = articuloService.buscarArticulos();
         model.addAttribute("articulos", articulos);
         model.addAttribute("partidaspedido", new PartidasPedido());
 
         Long idPedido = (Long) session.getAttribute("idPedido");
         model.addAttribute("pedido", idPedido);
 
         return "Vistas/LlenarPedido";
     }

    @PostMapping("/guardarPedido")
    public String guardarPedido(@ModelAttribute PartidasPedido partidaspedido) {
        if(partidaspedido.getCantidad() > 0){
            partidasPedidoService.guardarPedido(partidaspedido);
        }
        
        return "redirect:/agregar-articulos";
    }
    
    @PostMapping("/finalizarPedido")
    public String finalizarPedido(HttpSession sesion) {
        sesion.invalidate();
        
        return "redirect:/pedido/listado";
    }
       
    @GetMapping("/exportar/{id}")
    public ResponseEntity<byte[]> exportar(@PathVariable Long id) throws JRException, FileNotFoundException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("Report", "Report.pdf");
        return ResponseEntity.ok().headers(headers).body(partidasPedidoService.generarReporte(id));
    }
    
}
