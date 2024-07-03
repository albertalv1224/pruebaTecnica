package com.prueba.tecnica.Service;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.prueba.tecnica.Model.Articulo;
import com.prueba.tecnica.Model.PartidaPedidoDTO;
import com.prueba.tecnica.Model.PartidasPedido;
import com.prueba.tecnica.Model.Pedidos;
import com.prueba.tecnica.Repositories.PartidaPedidoRepository;
import com.prueba.tecnica.Util.ReportGenerator;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

@Service
public class PartidasPedidoService {

    private final PartidaPedidoRepository partidaPedidoRepository;
    private final ReportGenerator reportGenerator;

    public PartidasPedidoService(PartidaPedidoRepository partidaPedidoRepository, ReportGenerator reportGenerator){
        this.partidaPedidoRepository = partidaPedidoRepository;
        this.reportGenerator = reportGenerator;
    }
    public String guardarPedido(PartidasPedido partidasPedido){
        partidaPedidoRepository.save(partidasPedido);
        return "Pedido guardado exitosamente";
    }
    public void eliminarPartidasPorArticulo(Articulo articulo) {
        partidaPedidoRepository.deleteByIdarticulo(articulo);
    }

    public void eliminarPartidasPorPedido(Pedidos pedido){
        partidaPedidoRepository.deleteByIdpedido(pedido);
    }
    
    public byte[] generarReporte(Long idPedido) throws FileNotFoundException, JRException {
        List<PartidasPedido> partidas = partidaPedidoRepository.findAllByPedidoIdPedido(idPedido);
        List<PartidaPedidoDTO> partidasDTO = new ArrayList<>();
        for (PartidasPedido partida : partidas) {
            PartidaPedidoDTO dto = new PartidaPedidoDTO(
                partida.getIdPartida(),
                partida.getIdpedido().getIdPedido(),
                partida.getIdarticulo().getIdArticulo(),
                partida.getCantidad()
            );
            partidasDTO.add(dto);
        }
        JasperPrint jasperPrint = reportGenerator.getReport(partidasDTO);
        return reportGenerator.exportPdf(jasperPrint);
    }
}
