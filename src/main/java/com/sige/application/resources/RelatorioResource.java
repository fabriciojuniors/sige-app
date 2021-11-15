package com.sige.application.resources;

import com.sige.application.enums.Relatorios;
import com.sige.application.exception.RelatorioNaoEncontradoException;
import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.websocket.server.PathParam;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@RestController()
@RequestMapping("/relatorio")
public class RelatorioResource {

    @Autowired
    DataSource dataSource;

    public JasperPrint export(Relatorios relatorio, Map<String, Object> parametros) throws JRException, FileNotFoundException, SQLException {
        InputStream jasperStream = this.getClass().getResourceAsStream(relatorio.getArquivo());
        JasperReport s = JasperCompileManager.compileReport(jasperStream);
        JasperPrint print = JasperFillManager.fillReport(s, parametros,  dataSource.getConnection());
        return print;
    }

    public void exportReport(OutputStream stream, Relatorios relatorio, Map<String, Object> parametros) throws JRException, FileNotFoundException, SQLException {
        JasperPrint print = export(relatorio, parametros);
        JasperExportManager.exportReportToPdfStream(print, stream);
    }

    @GetMapping(value = "/{relatorio}")
    public void rel(HttpServletResponse response, @PathVariable(name = "relatorio") String r, @PathParam("evento") Long evento) throws Exception {
        Relatorios relatorio = null;
        try{
            relatorio = Relatorios.valueOf(r.toUpperCase());
        }catch (Exception ex){
            throw new RelatorioNaoEncontradoException(r);
        }
        /*
            PARA BAIXAR O PDF DIRETAMENTE
            response.setContentType("application/x-download");
            response.setHeader("Content-Disposition", String.format("attachment; filename=\"Locais.pdf\""));
        */

        //PARA ABRIR O PDF NO NAVEGADOR
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename="+relatorio.getPdf());

        OutputStream outputStream = response.getOutputStream();
        if(relatorio.equals(Relatorios.EVENTOS)){
            Map<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("id_evento", evento);
            Logger.getLogger("PARAMETROS RELATÓRIO").info(evento+"");

            exportReport(outputStream, relatorio, parametros);
        }else{
            exportReport(outputStream, relatorio, null);
        }

    }

}
