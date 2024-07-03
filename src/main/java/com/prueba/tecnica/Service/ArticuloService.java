package com.prueba.tecnica.Service;


import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prueba.tecnica.Model.Articulo;
import com.prueba.tecnica.Repositories.ArticuloRepository;



@Service
public class ArticuloService {

    private final ArticuloRepository articuloRepository;
    private final PartidasPedidoService partidasPedidoService;

    public ArticuloService(ArticuloRepository articuloRepository, PartidasPedidoService partidasPedidoService){
        this.articuloRepository = articuloRepository;
        this.partidasPedidoService = partidasPedidoService;
    }

    public String guardarArticulo (Articulo articulo){
        articuloRepository.save(articulo);
        return "Artículo guardado exitosamente";
    }

    public List<Articulo> buscarArticulos(){
        return articuloRepository.findAll();
    }

    public Articulo buscarArticulo(Long id){
        return articuloRepository.findById(id).orElse(null);
    }

    @Transactional
    public void borrarArticulo(Long id){
        Articulo articulo = articuloRepository.findById(id).orElse(null);
        partidasPedidoService.eliminarPartidasPorArticulo(articulo);
        articuloRepository.deleteById(id);
    }
}
