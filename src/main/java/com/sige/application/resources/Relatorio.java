package com.sige.application.resources;

import com.sige.application.enums.Relatorios;
import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Map;

@RestController()
@RequestMapping("/relatorio")
public class Relatorio {

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

    @GetMapping(value = "/locais")
    public void rel(HttpServletResponse response) throws IOException, JRException, SQLException {
        /*
            PARA BAIXAR O PDF DIRETAMENTE
            response.setContentType("application/x-download");
            response.setHeader("Content-Disposition", String.format("attachment; filename=\"Locais.pdf\""));
        */

        //PARA ABRIR O PDF NO NAVEGADOR
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=Locais.pdf");

        OutputStream outputStream = response.getOutputStream();
        exportReport(outputStream, Relatorios.LOCAIS, null);
    }

}
