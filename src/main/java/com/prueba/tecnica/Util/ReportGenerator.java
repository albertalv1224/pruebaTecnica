package com.prueba.tecnica.Util;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.prueba.tecnica.Model.PartidaPedidoDTO;
import com.prueba.tecnica.Model.PartidasPedido;
import com.prueba.tecnica.Repositories.PartidaPedidoRepository;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class ReportGenerator {
   
    public byte[] exportPdf(JasperPrint jasperPrint) throws JRException {
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
    
    public JasperPrint getReport(List<PartidaPedidoDTO> partidas) throws FileNotFoundException, JRException {
        Map<String, Object> params = new HashMap<>();
        params.put("partidasData", new JRBeanCollectionDataSource(partidas));

        JasperPrint report = JasperFillManager.fillReport(
                JasperCompileManager.compileReport(ResourceUtils.getFile("classpath:Invoice.jrxml").getAbsolutePath()),
                params,
                new JREmptyDataSource());

        return report;
    }
}
